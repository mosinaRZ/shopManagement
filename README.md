# 🛒 Shop Management

نرم‌افزار مدرن اندروید برای مدیریت یکپارچه فروشگاه، صدور فاکتور، انبارداری و ثبت حساب دفتری مشتریان با معماری **Offline-First Clean Architecture**.

## ✨ ویژگی‌های کلیدی

- 📦 **مدیریت انبار و موجودی**
  - ثبت کالاها
  - ثبت قیمت خرید و فروش
  - آلارم کسری موجودی کالاها

- 🧾 **صدور سریع فاکتور فروش و خرید**
  - ثبت آنی فاکتور
  - محاسبه خودکار تخفیف
  - محاسبه مانده حساب

- 👥 **حساب دفتری مشتریان و تأمین‌کنندگان**
  - پیگیری بدهی‌ها
  - پیگیری طلب‌ها
  - بررسی وضعیت تسویه

- 📊 **داشبورد و گزارشات مالی**
  - محاسبه سود و زیان روزانه و ماهانه
  - ثبت هزینه‌های جاری

- 💾 **معماری Offline-First**
  - کارکرد پایدار بدون اینترنت
  - استفاده از Room Database

- 🎨 **طراحی مدرن Neumorphism**
  - پیاده‌سازی با Jetpack Compose و Material 3
  - رابط کاربری راست‌چین (RTL)

## 🏗 معماری و فناوری‌ها

این پروژه بر اساس اصول **Clean Architecture** و الگوی **MVVM** پیاده‌سازی شده است.

### Tech Stack

| بخش | فناوری |
|---|---|
| UI Toolkit | Jetpack Compose & Material 3 |
| Dependency Injection | Dagger Hilt |
| Local Persistence | Room Database & DataStore Preferences |
| Asynchronous & Reactive | Kotlin Coroutines & Flow |
| Navigation | Jetpack Navigation Compose |
| Design Pattern | Offline-First Repository Pattern |

### 📁 ساختار پروژه

```text
ir.hamedan.shopmanagement
├── app/       # MainActivity, Navigation Root
├── core/      # Database, Utils, Preferences, State Management
├── data/      # Local DAOs, Entities, Mappers, Repositories
├── domain/    # Pure Kotlin Models, Repository Interfaces, UseCases
├── feature/   # Presentation layer (Compose Screens, Sections, ViewModels)
└── di/        # Hilt Dependency Injection Modules
```

## 🚀 نحوه اجرا

### 1. کلون کردن ریپازیتوری

```bash
git clone https://github.com/mosinaRZ/shopManagement.git
```

### 2. باز کردن پروژه

پروژه را در **Android Studio (Ladybug یا بالاتر)** باز کنید.

### 3. همگام‌سازی پروژه

گزینه **Sync Project with Gradle Files** را اجرا کنید.

### 4. اجرای برنامه

پروژه را روی یک دستگاه واقعی یا شبیه‌ساز با حداقل نسخه **Android 8.0 (API 26)** اجرا کنید.

## 📄 مجوز

این پروژه تحت مجوز **MIT** منتشر شده است.

برای جزئیات کامل، فایل [`LICENSE`](LICENSE) را مشاهده کنید.
