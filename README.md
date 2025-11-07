# Proyek Post Test Pemrograman Mobile - CRUD Data Kependudukan

[cite_start]Ini adalah proyek aplikasi Android yang dibuat sebagai bagian dari Laporan Praktikum (Post Test) Pemrograman Mobile[cite: 1, 64]. [cite_start]Aplikasi ini mengimplementasikan fungsionalitas **CRUD (Create, Read, Update, Delete)** untuk mengelola **"Data Kependudukan"** atau "Warga"[cite: 72].

[cite_start]Aplikasi ini menggunakan database lokal **Room** dari Android Jetpack untuk menyimpan data secara persisten[cite: 72].

## 📸 Tampilan Aplikasi

Aplikasi ini terdiri dari dua layar utama:

1.  [cite_start]**MainActivity:** Menampilkan daftar semua data "Warga" yang telah tersimpan (kemungkinan besar dalam `RecyclerView`)[cite: 83].
2.  [cite_start]**DetailActivity:** Sebuah formulir detail untuk memasukkan atau mengedit data "Warga" [cite: 85][cite_start], termasuk Nama, NIK, Alamat, Jenis Kelamin, Status Perkawinan, dll. [cite: 73, 98-105].

*(Disarankan untuk menambahkan screenshot aplikasi Anda di sini)*

`[Tambahkan screenshot formulir DetailActivity di sini]`

## 🚀 Fitur Utama

* [cite_start]**Create:** Menyimpan data warga baru melalui formulir di `DetailActivity`[cite: 87, 95].
* [cite_start]**Read:** Menampilkan daftar semua warga di `MainActivity` [cite: 83] [cite_start]dan memuat data warga spesifik di `DetailActivity`[cite: 87].
* **Update:** (Tersirat) Mengedit data warga yang sudah ada melalui `DetailActivity`.
* [cite_start]**Delete:** Menghapus data yang sudah diinputkan[cite: 108].
* [cite_start]**Validasi:** Memberikan notifikasi *Toast* jika pengguna mencoba menyimpan data dengan kolom yang masih kosong[cite: 88].
* [cite_start]**Reset:** Tombol untuk mengosongkan formulir[cite: 90].

## 🛠️ Teknologi & Arsitektur

* **Bahasa:** Kotlin
* [cite_start]**Database:** Android Room (Lokal Database) [cite: 72]
* [cite_start]**Komponen UI:** Activity (`MainActivity`, `DetailActivity`)[cite: 82, 84], EditText, RadioButton, Spinner.
* **Arsitektur:**
    * [cite_start]**`Warga.kt` (Model/Entity):** Mendefinisikan struktur data untuk tabel "Warga" di database[cite: 71, 72].
    * [cite_start]**`WargaDAO.kt` (DAO):** *Interface* yang berisi fungsi-fungsi untuk mengakses database (seperti `insert`, `delete`, `getAllWarga`)[cite: 74, 75, 76].
    * [cite_start]**`DatabaseWarga.kt` (Database):** Kelas utama pemegang database Room[cite: 77, 78].
    * [cite_start]**`MainActivity.kt` (View/Controller):** Aktivitas untuk menampilkan daftar warga[cite: 82, 83].
    * [cite_start]**`DetailActivity.kt` (View/Controller):** Aktivitas yang berfungsi sebagai formulir untuk input, update, dan melihat detail data warga[cite: 84, 85, 87].
    * [cite_start]**`AppExecutor.kt` (Utility):** Kelas utilitas untuk mengelola *threading* operasi database agar tidak berjalan di UI thread[cite: 79, 80].
