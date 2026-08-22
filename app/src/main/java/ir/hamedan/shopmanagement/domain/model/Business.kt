package ir.hamedan.shopmanagement.domain.model

data class Business(
    val name: String = "فروشگاه من",
    val ownerName: String = "",
    val phone: String = "",
    val address: String = "",
    val currency: String = "تومان"
)