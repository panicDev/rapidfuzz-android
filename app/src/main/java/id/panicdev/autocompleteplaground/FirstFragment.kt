package id.panicdev.autocompleteplaground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import id.panicdev.autocompleteplaground.data.AcData
import id.panicdev.autocompleteplaground.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val data = withContext(Dispatchers.IO) {
                val dataStr = Util.getJsonFromRaw(requireContext(), R.raw.data)
                dataStr.fromJson<AcData>()
            }

            val strBuilder = buildString {
                appendLine("Data Place Size : ${data.places.size}")
                appendLine("Data Resto Size : ${data.name.size}")
            }

            binding.textviewFirst.text = strBuilder

            val mAdapter1 = AutoCompleteAdapter(
                requireContext(),
                R.layout.item_dropdown,
                data.places.toMutableList(),
                binding.act1
            )
            binding.act1.setAdapter(mAdapter1)

            val mAdapter2 = AutoCompleteAdapter(
                requireContext(),
                R.layout.item_dropdown,
                data.name.toMutableList(),
                binding.act2
            )
            binding.act2.setAdapter(mAdapter2)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}