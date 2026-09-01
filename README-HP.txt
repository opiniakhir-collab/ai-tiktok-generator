AI TIKTOK GENERATOR - ANDROID APK

Project Android siap dibuild menggunakan GitHub Actions dari HP.

FITUR:
- Pilih/upload foto produk
- Nama produk
- Pilihan gaya konten TikTok
- Jumlah scene
- Kirim foto + parameter ke backend AI
- Menampilkan hasil generator
- Instruksi otomatis: pertahankan bentuk, warna, ukuran, dan detail produk referensi

CATATAN:
APK ini membutuhkan URL backend AI. Jangan menaruh API key OpenAI/AI langsung di APK.

CARA BUILD DARI HP:
1. Buat repository GitHub baru.
2. Upload SEMUA isi folder project ini (isi folder AI_TikTok_Generator_HP), bukan file ZIP-nya.
3. Pastikan file .github/workflows/build-apk.yml ikut ter-upload.
4. Buka tab Actions.
5. Pilih "Build Android APK".
6. Tekan "Run workflow".
7. Tunggu sampai job selesai.
8. Buka hasil Artifacts bernama "AI-TikTok-Generator-debug".
9. Download app-debug.apk lalu install di HP Android.

KOMPATIBILITAS:
- minSdk 24 (Android 7.0+)
- targetSdk 35
- JDK 17
- Gradle 8.9
- Android Gradle Plugin 8.7.3
