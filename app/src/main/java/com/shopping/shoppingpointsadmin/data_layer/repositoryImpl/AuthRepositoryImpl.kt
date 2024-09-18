import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shopping.shoppingpointsadmin.common.containts.ADMIN
import com.shopping.shoppingpointsadmin.domain_layer.models.Admin
import com.shopping.shoppingpointsadmin.domain_layer.repository.AuthRepository
import com.shopping.shoppingpointsadmin.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override fun loginUser(email: String, password: String): Flow<ResultState<AuthResult>> =
        callbackFlow {
            try {
                trySend(ResultState.Loading)
                val result = auth.signInWithEmailAndPassword(email, password)
                result.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(ResultState.Success(data = task.result))
                        Log.d("LoginUser", "loginUser: Success")
                    } else {
                        trySend(
                            ResultState.Error(
                                task.exception?.localizedMessage ?: "Unknown error"
                            )
                        )
                        Log.d("LoginUser", "loginUser Failed: ${task.exception}")
                    }
                }
            } catch (e: Exception) {
                trySend(ResultState.Error(e.localizedMessage ?: "Unknown error"))
                Log.d("LoginUser", "loginUser: Failed ${e.localizedMessage}")
            }

            awaitClose { close() }
        }

    override fun registerUser(email: String, password: String): Flow<ResultState<AuthResult>> =
        callbackFlow {
            try {
                trySend(ResultState.Loading)
                val result = auth.createUserWithEmailAndPassword(email, password)
                result.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(ResultState.Success(task.result))
                        Log.d("RegisterUser", "registerUser: Success")
                    } else {
                        trySend(
                            ResultState.Error(
                                task.exception?.localizedMessage ?: "Unknown error"
                            )
                        )
                        Log.d("RegisterUser", "registerUser Failed: ${task.exception}")
                    }
                }
            } catch (e: Exception) {
                trySend(ResultState.Error(e.localizedMessage ?: "Unknown error"))
                Log.d("RegisterUser", "registerUser: Failed ${e.localizedMessage}")
            }

            awaitClose { close() }
        }

    override fun saveAdminDetails(admin: Admin): Flow<ResultState<String>> = callbackFlow {
        try {
            firestore.collection(ADMIN).document(admin.name.plus(UUID.randomUUID().toString()))
                .set(admin).addOnCompleteListener {
                    trySend(ResultState.Loading)
                    if (it.isSuccessful) {
                        trySend(ResultState.Success("Product Added Successfully!"))
                    } else {
                        trySend(
                            ResultState.Error(
                                it.exception?.localizedMessage ?: "Unknown error"
                            )
                        )
                    }
                }
        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage ?: "Unknown error")
        }
        awaitClose{
            close()
        }
    }
}
