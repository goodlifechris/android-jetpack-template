package app.tendo.ui
/**
 * Created by goodlife on 26,August,2021
 */

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import app.tendo.Config.USER_NAME
import app.tendo.R
import app.tendo.data.local.dataStore.DataManager
import app.tendo.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    @Inject
    lateinit var dataManager: DataManager
    private var _binding: FragmentFirstBinding? = null
    private var name:String = ""
    private var no:Int = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        subscribeToSharedPrefferenceObserver()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {

            GlobalScope.launch {
                dataManager.putString(USER_NAME,"James")
            }

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToSharedPrefferenceObserver() {

        dataManager.userNameFlow.asLiveData().observe(viewLifecycleOwner) {
            name = it
            if (name.isNotEmpty()){
                textview_first.visibility = View.VISIBLE
                textview_first.text = "Name: $name"
            }
        }
    }
}