package com.farhan164.postpmobinhall4

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.farhan164.postpmobinhall4.databinding.ActivityMainBinding
import com.farhan164.postpmobinhall4.model.DatabaseWarga
import com.farhan164.postpmobinhall4.model.Warga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseWarga

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DatabaseWarga.getDatabase(this)

        val statusOptions = arrayOf("Belum Menikah", "Menikah", "Cerai")
        val spinnerAdapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            statusOptions
        )
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spStatus.adapter = spinnerAdapter

        binding.btnSimpan.setOnClickListener {
            simpanData()
        }

        binding.btnReset.setOnClickListener {
            hapusSemuaData()
        }

        tampilkanData()
    }

    private fun simpanData() {
        val nik = binding.etNIK.text.toString().trim()
        val nama = binding.etNama.text.toString().trim()
        val kabupaten = binding.etKabupaten.text.toString().trim()
        val rt = binding.etRT.text.toString().trim()
        val rw = binding.etRW.text.toString().trim()
        val desa = binding.etDesa.text.toString().trim()
        val kecamatan = binding.etKecamatan.text.toString().trim()

        val selectedGenderId = binding.rgGender.checkedRadioButtonId
        val jenisKelamin = if (selectedGenderId != -1) {
            selectedGenderId.toString()
        } else {
            ""
        }

        val statusPernikahan = binding.spStatus.selectedItem?.toString() ?: ""

        if (nik.isEmpty() || nama.isEmpty() || kabupaten.isEmpty() ||
            rt.isEmpty() || rw.isEmpty() || desa.isEmpty() || kecamatan.isEmpty() ||
            jenisKelamin.isEmpty() || statusPernikahan.isEmpty()
        ) {
            Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        val warga = Warga(
            nama = nama,
            nik = nik,
            kabupaten = kabupaten,
            kecamatan = kecamatan,
            desa = desa,
            rt = rt,
            rw = rw,
            jenisKelamin = jenisKelamin,
            statusPernikahan = statusPernikahan
        )

        lifecycleScope.launch(Dispatchers.IO) {
            database.wargaDao().insert(warga)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
                clearForm()
                tampilkanData()
            }
        }
    }

    private fun hapusSemuaData() {
        lifecycleScope.launch(Dispatchers.IO) {
            database.wargaDao().deleteAll()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Semua data berhasil dihapus!", Toast.LENGTH_SHORT).show()
                clearForm()
                tampilkanData()
            }
        }
    }

    private fun tampilkanData() {
        database.wargaDao().getAllWarga().observe(this, Observer { wargaList ->
            val stringBuilder = StringBuilder()
            for (w in wargaList) {
                stringBuilder.append("Nama: ${w.nama}\n")
                stringBuilder.append("NIK: ${w.nik}\n")
                stringBuilder.append("Alamat: ${w.desa}, ${w.kecamatan}, ${w.kabupaten} (RT ${w.rt}/RW ${w.rw})\n")
                stringBuilder.append("Jenis Kelamin: ${w.jenisKelamin}\n")
                stringBuilder.append("Status: ${w.statusPernikahan}\n\n")
            }

            // Kalau belum ada data, tampilkan pesan
            if (wargaList.isEmpty()) {
                binding.tvDaftarWarga.text = "Belum ada data warga yang tersimpan."
            } else {
                binding.tvDaftarWarga.text = stringBuilder.toString()
            }
        })
    }

    private fun clearForm() {
        binding.etNIK.text.clear()
        binding.etNama.text.clear()
        binding.etKabupaten.text.clear()
        binding.etRT.text.clear()
        binding.etRW.text.clear()
        binding.etDesa.text.clear()
        binding.etKecamatan.text.clear()
        binding.rgGender.clearCheck()
        binding.spStatus.setSelection(0)
        binding.etNIK.requestFocus()
    }
}