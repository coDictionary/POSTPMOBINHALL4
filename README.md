# Proyek Post Test Pemrograman Mobile - CRUD Data Kependudukan

Ini adalah proyek aplikasi Android yang dibuat sebagai bagian dari Laporan Praktikum (Post Test) Pemrograman MobileAplikasi ini mengimplementasikan fungsionalitas **CRUD (Create, Read, Update, Delete)** untuk mengelola **"Data Kependudukan"** atau "Warga".

Aplikasi ini menggunakan database lokal **Room** dari Android Jetpack untuk menyimpan data secara persisten.

## 📸 Tampilan Aplikasi

Aplikasi ini terdiri dari dua layar utama:

1.  **MainActivity:** Menampilkan daftar semua data "Warga" yang telah tersimpan (kemungkinan besar dalam `RecyclerView`).
2.  **DetailActivity:** Sebuah formulir detail untuk memasukkan atau mengedit data "Warga" , termasuk Nama, NIK, Alamat, Jenis Kelamin, Status Perkawinan, dll.


## 🚀 Fitur Utama

* **Create:** Menyimpan data warga baru melalui formulir di `DetailActivity`.
* **Read:** Menampilkan daftar semua warga di `MainActivity` dan memuat data warga spesifik di `DetailActivity`.
* **Update:** (Tersirat) Mengedit data warga yang sudah ada melalui `DetailActivity`.
* **Delete:** Menghapus data yang sudah diinputkan.
* **Validasi:** Memberikan notifikasi *Toast* jika pengguna mencoba menyimpan data dengan kolom yang masih kosong[cite: 88].
* **Reset:** Tombol untuk mengosongkan formulir.

## 🛠️ Teknologi & Arsitektur

* **Bahasa:** Kotlin
* **Database:** Android Room (Lokal Database)
* **Komponen UI:** Activity (`MainActivity`, `DetailActivity`), EditText, RadioButton, Spinner.
* **Arsitektur:**
    * **`Warga.kt` (Model/Entity):** Mendefinisikan struktur data untuk tabel "Warga" di database
    * **`WargaDAO.kt` (DAO):** *Interface* yang berisi fungsi-fungsi untuk mengakses database (seperti `insert`, `delete`, `getAllWarga`)
    * **`DatabaseWarga.kt` (Database):** Kelas utama pemegang database Room
    * **`MainActivity.kt` (View/Controller):** Aktivitas untuk menampilkan daftar warga
    * **`DetailActivity.kt` (View/Controller):** Aktivitas yang berfungsi sebagai formulir untuk input, update, dan melihat detail data warga
    * **`AppExecutor.kt` (Utility):** Kelas utilitas untuk mengelola *threading* operasi database agar tidak berjalan di UI thread
