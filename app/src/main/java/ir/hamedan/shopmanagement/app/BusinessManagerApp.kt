package ir.hamedan.shopmanagement.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * نقطه‌ی ورود Hilt به گراف وابستگی‌ها.
 * توجه: چون RepositoryModule و DatabaseModule هر دو Hilt-based هستن
 * (@Module @InstallIn(SingletonComponent::class))، دیگه نیازی به AppContainer
 * دستی نیست؛ Hilt خودش تمام وابستگی‌ها (AppDatabase, Repository ها, ...)
 * رو در زمان کامپایل تولید و مدیریت می‌کنه.
 *
 * حتماً در AndroidManifest.xml این کلاس ست بشه:
 *   <application android:name=".app.BusinessManagerApp" ... >
 */
@HiltAndroidApp
class BusinessManagerApp : Application()