package hu.bme.aut.publictransport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import hu.bme.aut.publictransport.databinding.ActivityDetailsBinding
import hu.bme.aut.publictransport.databinding.ActivityListBinding
import hu.bme.aut.publictransport.databinding.ActivityLoginBinding
import java.util.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val KEY_TRANSPORT_TYPE = "KEY_TRANSPORT_TYPE"
    }

    private lateinit var binding: ActivityDetailsBinding

    private fun getTypeString(transportType: Int): String {
        return when (transportType) {
            ListActivity.TYPE_BUS -> "Bus pass"
            ListActivity.TYPE_TRAIN -> "Train pass"
            ListActivity.TYPE_BIKE -> "Bike pass"
            ListActivity.TYPE_BOAT -> "Boat pass"
            else -> "Unknown pass type"
        }
    }

    private fun getDateFrom(picker: DatePicker): String? {
        return String.format(
            Locale.getDefault(), "%04d.%02d.%02d.",
            picker.year, picker.month + 1, picker.dayOfMonth
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transportType = this.intent.getIntExtra(KEY_TRANSPORT_TYPE, -1)

        binding.tvTicketType.text = getTypeString(transportType)

        binding.btnPurchase.setOnClickListener {
            val typeString = getTypeString(transportType)
            val dateString = getDateFrom(binding.dpStartDate) + " - " + getDateFrom(binding.dpEndDate)

            val intent = Intent(this, PassActivity::class.java)
            intent.putExtra(PassActivity.KEY_TYPE_STRING, typeString)
            intent.putExtra(PassActivity.KEY_DATE_STRING, dateString)
            startActivity(intent)
        }
    }
}