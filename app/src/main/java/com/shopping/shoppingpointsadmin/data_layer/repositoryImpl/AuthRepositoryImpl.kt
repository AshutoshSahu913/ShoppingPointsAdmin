import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.shopping.shoppingpointsadmin.common.containts.ADMIN
import com.shopping.shoppingpointsadmin.domain_layer.models.Admin
import com.shopping.shoppingpointsadmin.domain_layer.repository.AuthRepository
import com.shopping.shoppingpointsadmin.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun loginUser(email: String, password: String): Flow<ResultState<AuthResult>> =
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

    override suspend fun registerUser(
        email: String,
        password: String
    ): Flow<ResultState<AuthResult>> =
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

    override suspend fun saveAdminDetails(admin: Admin): Flow<ResultState<String>> = callbackFlow {
        try {
            trySend(ResultState.Loading)
            firestore.collection(ADMIN).document(admin.adminId.toString())
                .set(admin).addOnCompleteListener {
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
            trySend(ResultState.Error(e.localizedMessage ?: "Unknown error"))
        }
        awaitClose {
            close()
        }
    }

    override suspend fun signOut(): Flow<ResultState<String>> = callbackFlow {
        try {
            trySend(ResultState.Loading)
            auth.signOut()
            trySend(ResultState.Success("sign out Successfully!"))
        } catch (e: Exception) {
            trySend(ResultState.Error(e.localizedMessage ?: "Unknown error"))
            Log.d("SignOut", "signOut: ${e.localizedMessage}")
        }
        awaitClose {
            close()
        }
    }

    override suspend fun getCurrentUser(): Flow<ResultState<FirebaseUser?>> = callbackFlow {
        try {
            trySend(ResultState.Loading)
            val currentUser = auth.currentUser
            trySend(ResultState.Success(currentUser))
        } catch (e: Exception) {
            trySend(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
        }
        awaitClose {
            close()
        }

    }

    override suspend fun getAdminDetails(adminId: String): Flow<ResultState<Admin>> = callbackFlow {
        try {
            trySend(ResultState.Loading)

            // Fetch a specific admin document using its ID
            val documentReference = firestore.collection(ADMIN).document(adminId)

            documentReference.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        // Deserialize the document into an Admin object
                        val admin = documentSnapshot.toObject(Admin::class.java)
                        admin?.let {
                            trySend(ResultState.Success(it)) // Send success result
                        } ?: run {
                            trySend(ResultState.Error("Admin data is null!"))
                        }
                    } else {
                        trySend(ResultState.Error("Admin with ID $adminId not found!"))
                    }
                }
                .addOnFailureListener { exception ->
                    trySend(
                        ResultState.Error(
                            exception.localizedMessage ?: "Error fetching admin data!"
                        )
                    )
                }

        } catch (e: Exception) {
            trySend(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
        }

        // Close the flow properly when the coroutine is cancelled
        awaitClose {
            close()
        }
    }

    override suspend fun forgetPassword(email: String): Flow<ResultState<String>> = callbackFlow {
        try {
            trySend(ResultState.Loading)
            val result = auth.sendPasswordResetEmail(email)
            if (result.isSuccessful) {
                Log.d("ForgetPassword", "forgetPassword: ${result.result}")
                trySend(ResultState.Success("Password Reset Successfully!"))
            } else {
                trySend(ResultState.Error("Password Reset Failed!"))
                Log.d("ForgetPassword", "forgetPassword: ${result.exception}")
            }
        } catch (e: Exception) {
            trySend(ResultState.Error(e.localizedMessage ?: "Unknown Error"))
            Log.d("ForgetPassword", "forgetPassword: ${e.localizedMessage}")
        }
        awaitClose {
            close()
        }
    }


}
