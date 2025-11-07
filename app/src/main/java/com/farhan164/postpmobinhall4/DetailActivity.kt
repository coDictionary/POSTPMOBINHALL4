package com.farhan164.postpmobinhall4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.farhan164.postpmobinhall4.R
import com.farhan164.postpmobinhall4.databinding.ActivityDetailBinding
import com.farhan164.postpmobinhall4.model.AppExecutor
import com.farhan164.postpmobinhall4.model.DatabaseWarga

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var appExecutors: AppExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appExecutors = AppExecutor()

        val wargaId = intent.getIntExtra("warga_id", -1)
        if (wargaId != -1) {
            appExecutors.diskIO.execute {
                val dao = DatabaseWarga.getDatabase(this@DetailActivity).wargaDao()
                val selectedWarga = dao.getAllWarga().value?.find { it.id == wargaId }

                runOnUiThread {
                    if (selectedWarga != null) {
                        binding.apply {
                            etNama.setText(selectedWarga.nama)
                            etNik.setText(selectedWarga.nik)
                            etKabupaten.setText(selectedWarga.kabupaten)
                            etKecamatan.setText(selectedWarga.kecamatan)
                            etDesa.setText(selectedWarga.desa)
                            etRt.setText(selectedWarga.rt)
                            etRw.setText(selectedWarga.rw)
                            etJenisKelamin.setText(selectedWarga.jenisKelamin)
                            etStatus.setText(selectedWarga.statusPernikahan)
                        }
                    }
                }

                binding.btnDelete.setOnClickListener {
                    appExecutors.diskIO.execute {
                        if (selectedWarga != null) {
                            dao.deleteAll()
                        }
                        finish()
                    }
                }
            }
        }
    }
}