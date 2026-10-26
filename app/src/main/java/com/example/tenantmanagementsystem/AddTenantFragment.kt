package com.example.tenantmanagementsystem
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import com.example.tenantmanagementsystem.databinding.FragmentAddTenantBinding
    class AddTenantFragment : Fragment() {
        private val TAG = "AddTenantFragment"
        private var _binding: FragmentAddTenantBinding? = null
        private val binding get() = _binding!!
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
            return binding.root
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
// The SAVE button is added in Practical 3
            // saveButton ← android:id="@+id/saveButton" in fragment_add_tenant.xml
            binding.saveButton.setOnClickListener {
// Read the three EditTexts in fragment_add_tenant.xml
                val name = binding.tenantNameEditText.text.toString().trim()
                val phone = binding.phoneEditText.text.toString().trim()
                val rent = binding.rentEditText.text.toString().trim()
                if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
// A Fragment is not a Context, so ask for its Activity's
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                //Try it yourself part 2 lab 7
                val digitsOnly = phone.filter { it.isDigit() }

                if (digitsOnly.length < 10) {
                    binding.phoneEditText.error = "Enter a valid phone number"
                    return@setOnClickListener
                }
                val tenant = Tenant(name, phone, rent)
// Add to the shared list (the Dashboard and View Tenants read it)
                TenantRepository.addTenant(tenant)
// SAFE ARGS: this function was generated from the <action> in
// nav_graph.xml. Because View Tenants has a <argument> of type
// Tenant, the function REQUIRES a Tenant: it won't compile without one
                val action = AddTenantFragmentDirections
                    .actionAddTenantFragmentToViewTenantsFragment(tenant)
// Ask the NavController to follow the action
                findNavController().navigate(action)
            }
        }
        //Try it yourself lab 7
       // override fun onDestroyView() {
          //  super.onDestroyView()
           // _binding = null
       // }
        override fun onDestroyView() {
            Log.d(TAG, "onDestroyView: Fragment view destroyed")
            super.onDestroyView()
            _binding = null
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            Log.d(TAG, "onAttach: Fragment attached to Activity")
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d(TAG, "onCreate: Fragment created")
        }

        override fun onStart() {
            super.onStart()
            Log.d(TAG, "onStart: Fragment is becoming visible")
        }

        override fun onResume() {
            super.onResume()
            Log.d(TAG, "onResume: Fragment is ready for interaction")
        }

        override fun onPause() {
            super.onPause()
            Log.d(TAG, "onPause: Fragment is losing focus")
        }

        override fun onStop() {
            super.onStop()
            Log.d(TAG, "onStop: Fragment is no longer visible")
        }


        override fun onDetach() {
            super.onDetach()
            Log.d(TAG, "onDetach: Fragment detached from Activity")
        }
    }
