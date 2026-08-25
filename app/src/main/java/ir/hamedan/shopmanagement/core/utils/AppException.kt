package ir.hamedan.shopmanagement.core.utils

/**
 * سلسله‌مراتب خطاهای سفارشی اپ. تمام Repository/UseCase ها به‌جای پرتاب
 * Exception خام، از این‌ها استفاده می‌کنند تا لایه‌ی UI بتونه پیام مناسب
 * فارسی رو مستقیماً نمایش بده.
 */
sealed class AppException(message: String) : Exception(message) {

    /** خطای اعتبارسنجی ورودی کاربر (مثلاً تعداد منفی، فیلد خالی و ...) */
    class ValidationException(
        val field: String,
        val reason: String
    ) : AppException("$field: $reason")

    /** وقتی رکوردی با شناسه‌ی داده‌شده در دیتابیس پیدا نشود */
    class NotFoundException(
        val entityName: String,
        val id: Long
    ) : AppException("$entityName با شناسه‌ی $id یافت نشد")
}