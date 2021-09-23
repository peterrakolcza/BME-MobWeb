package hu.bme.aut.publictransport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.publictransport.databinding.ActivityListBinding
import hu.bme.aut.publictransport.databinding.ActivityLoginBinding

class ListActivity : AppCompatActivity() {
    companion object {
        const val TYPE_BUS = 1
        const val TYPE_TRAIN = 2
        const val TYPE_BIKE = 3
        const val TYPE_BOAT = 4
    }

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBus.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_TRANSPORT_TYPE, TYPE_BUS)
            startActivity(intent)
        }

        binding.btnBike.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_TRANSPORT_TYPE, TYPE_BIKE)
            startActivity(intent)
        }

        binding.btnTrain.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_TRANSPORT_TYPE, TYPE_TRAIN)
            startActivity(intent)
        }

        binding.btnBoat.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_TRANSPORT_TYPE, TYPE_BOAT)
            startActivity(intent)
        }
    }
}