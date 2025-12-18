# Mete'nin Eğitici Dünyası

Android uygulaması - 5 yaşındaki çocuklar için eğitici oyunlar ve içerik.

## Proje Yapısı

Bu Android Studio projesi aşağıdaki bileşenleri içerir:

### Klasör Yapısı
```
YEN-/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/metecocuk/      # Kotlin kaynak dosyaları
│   │       ├── res/                      # Android kaynakları (layout, drawable, values)
│   │       ├── assets/                   # HTML ve web içeriği
│   │       │   └── index.html           # Ana eğitici web içeriği
│   │       └── AndroidManifest.xml      # Uygulama manifest dosyası
│   ├── build.gradle                     # App modülü Gradle yapılandırması
│   └── proguard-rules.pro              # ProGuard kuralları
├── gradle/                              # Gradle wrapper dosyaları
├── build.gradle                         # Proje seviyesi Gradle yapılandırması
├── settings.gradle                      # Gradle ayarları
└── gradle.properties                    # Gradle özellikleri
```

### Ana Bileşenler

#### Activities
- **SplashActivity**: Başlangıç splash ekranı
- **MainActivity**: Ana uygulama ekranı - oyunlar ve navigasyon
- **WebViewActivity**: HTML içeriğini gösteren WebView ekranı

#### Özellikler
- HTML tabanlı eğitici içerik (assets/index.html)
- Renkli ve çocuk dostu arayüz
- Text-to-Speech (TTS) desteği
- Bottom navigation ile kolay gezinme
- Oyunlar için RecyclerView grid layout

## Android Studio'da Kurulum

1. Android Studio'yu açın
2. "Open an existing project" seçeneğini tıklayın
3. Bu projenin kök klasörünü seçin
4. Gradle senkronizasyonunun tamamlanmasını bekleyin
5. Uygulamayı çalıştırın (Run > Run 'app')

## Gereksinimler

- Android Studio Arctic Fox veya üzeri
- Android SDK 34
- Minimum Android sürümü: API 24 (Android 7.0)
- Target Android sürümü: API 34

## Notlar

- `local.properties` dosyası otomatik oluşturulacaktır (SDK yolunu içerir)
- İlk build işlemi Gradle bağımlılıklarını indireceği için zaman alabilir
- WebView içeriği `app/src/main/assets/index.html` dosyasından yüklenir

## Geliştirici

Mehmet - Çocuklar için eğitici Android uygulaması
