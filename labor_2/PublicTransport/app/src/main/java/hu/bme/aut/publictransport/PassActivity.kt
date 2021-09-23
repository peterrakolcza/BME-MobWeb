package hu.bme.aut.publictransport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import hu.bme.aut.publictransport.databinding.ActivityListBinding
import hu.bme.aut.publictransport.databinding.ActivityPassBinding
import java.util.*

class PassActivity : AppCompatActivity() {
    companion object {
        const val KEY_DATE_STRING = "KEY_DATE_STRING"
        const val KEY_TYPE_STRING = "KEY_TYPE_STRING"
    }

    private lateinit var binding: ActivityPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)

        binding = ActivityPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTicketType.text = intent.getStringExtra(KEY_TYPE_STRING)
        binding.tvDates.text = intent.getStringExtra(KEY_DATE_STRING)
    }
}