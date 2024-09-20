package com.shopping.shoppingpointsadmin.domain_layer.models

data class Admin(
    val adminId:String?="",
    val name: String? = "",
    val email: String? = "",
    val phone: String? = "",
    val password: String? = "",
    val profileImg: String? = "",
    val location: String? = "",
    val companyName: String? = "",
    val socialMediaHandles: Map<String, String>? = mapOf(),
    val createdAt: Long = System.currentTimeMillis()
)
