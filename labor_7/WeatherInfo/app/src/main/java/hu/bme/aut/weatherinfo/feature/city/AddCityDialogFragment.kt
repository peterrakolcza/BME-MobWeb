import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.weatherinfo.R
import hu.bme.aut.weatherinfo.databinding.DialogNewCityBinding

class AddCityDialogFragment : AppCompatDialogFragment() {

    private lateinit var binding: DialogNewCityBinding
    private lateinit var listener: AddCityDialogListener

    interface AddCityDialogListener {
        fun onCityAdded(city: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = DialogNewCityBinding.inflate(LayoutInflater.from(context))

        try {
            listener = if (targetFragment != null) {
                targetFragment as AddCityDialogListener
            } else {
                activity as AddCityDialogListener
            }
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_city)
            .setView(binding.root)
            .setPositiveButton(R.string.ok) { _, _ ->
                listener.onCityAdded(
                    binding.NewCityDialogEditText.text.toString()
                )
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}
