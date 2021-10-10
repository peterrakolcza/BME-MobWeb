package hu.bme.aut.androidwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isEmpty
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.androidwallet.databinding.ActivityMainBinding
import hu.bme.aut.androidwallet.databinding.SalaryRowBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rowBinding: SalaryRowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            // TODO: ide jön az eseménykezelő kód
            if (binding.salaryName.text.toString().isEmpty() || binding.salaryAmount.text.toString().isEmpty()) {
                val contextView = findViewById<View>(R.id.mainLinear)
                //Toast.makeText(this, R.string.warn_message, Toast.LENGTH_LONG).show()
                Snackbar.make(contextView, R.string.warn_message, Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            rowBinding = SalaryRowBinding.inflate(layoutInflater)

            rowBinding.salaryDirectionIcon.setImageResource(if (binding.expenseOrIncome.isChecked) R.drawable.expense else R.drawable.income)
            rowBinding.rowSalaryName.text = binding.salaryName.text.toString()
            rowBinding.rowSalaryAmount.text = binding.salaryAmount.text.toString()

            binding.listOfRows.addView(rowBinding.root)

            var sum = if (binding.sum.text.toString() != "") {
                binding.sum.text.toString().toInt()
            } else {
                0
            }

            when {
                binding.listOfRows.isEmpty() -> {
                    binding.sum.setText("")
                }
                binding.expenseOrIncome.text.toString() == "EXPENSE" -> {
                    sum -= binding.salaryAmount.text.toString().toInt()
                }
                else -> {
                    sum += binding.salaryAmount.text.toString().toInt()
                }
            }

            binding.sum.setText(sum.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all -> {
                binding.listOfRows.removeAllViews()
                binding.sum.setText("")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}