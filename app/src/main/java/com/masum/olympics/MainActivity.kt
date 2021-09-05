package com.masum.olympics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.masum.olympics.databinding.ActivityMainBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button.setOnClickListener {
            if (binding.editText.text.toString() == "") {
                Toast.makeText(this, "please input year range", Toast.LENGTH_LONG).show()
            }
            var dateRange = binding.editText.text.toString()
            try {
                var dateArray = dateRange.split(",")
                calculateEvent(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]))
            }
            catch (e :Exception){
                Toast.makeText(this, "please Enter valid year range", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun calculateEvent(startDate: Int, endDate: Int) {

        var i = startDate + 1
        var totalEvent = 0
        var relaxStart = 0

        while (i <= endDate) {
            totalEvent++
            val c = Calendar.getInstance()
            val strDate = "23/7/$i"
            try {
                val date1 = SimpleDateFormat("dd/MM/yyyy").parse(strDate)
                c.time = date1
                val dayOfWeek = c[Calendar.DAY_OF_WEEK]
                val dayWeekText =
                    SimpleDateFormat("EEEE").format(date1)
                when (dayOfWeek) {
                    Constant.THURSDAY -> relaxStart++
                    Constant.FRIDAY -> relaxStart++
                    Constant.SATURDAY -> relaxStart++
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            i += 4
        }


        binding.tvTotalEvent.setText("Total Event:" + totalEvent)
        binding.tvTotalRelax.setText("Total Relax Start:" + relaxStart)

    }
}