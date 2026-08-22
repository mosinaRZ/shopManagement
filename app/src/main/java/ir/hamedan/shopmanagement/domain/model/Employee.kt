package ir.hamedan.shopmanagement.domain.model

data class Employee(
    val id: Long = 0L,
    val name: String,
    val role: String,
    val phone: String,
    val salary: Double,
    val hireDate: Long = System.currentTimeMillis()
)