package com.kinan.tugas6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.kinan.tugas6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val keterangan = arrayOf("Hadir Tepat Waktu", "Sakit", "Terlambat", "Izin")
        var selectedTime = ""
        var selectedDate = ""

        with(binding){
            val adapterKeterangan = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                keterangan)
            spinner.adapter= adapterKeterangan

            spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        if(parent.getItemAtPosition(position).toString() == "Hadir Tepat Waktu"){
                            deskripsi.visibility = View.INVISIBLE
                        } else {
                            deskripsi.visibility = View.VISIBLE
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

            timePicker.setOnTimeChangedListener { _, hourofDay, minute ->
                selectedTime = "$hourofDay:$minute"
            }
            calendarView.setOnDateChangeListener{ _, year, month, dayOfMonth ->
                val bulan = arrayOf(
                    "Januari",
                    "Februari",
                    "Maret",
                    "April",
                    "Mei",
                    "Juni",
                    "Juli",
                    "Agustus",
                    "September",
                    "Oktober",
                    "November",
                    "Desember"
                )
                selectedDate = "$dayOfMonth ${bulan[month]} $year"
            }

            btnSubmit.setOnClickListener {
                Toast.makeText(this@MainActivity,"Presensi berhasil $selectedDate jam $selectedTime",Toast.LENGTH_SHORT).show()
            }
        }
    }
}