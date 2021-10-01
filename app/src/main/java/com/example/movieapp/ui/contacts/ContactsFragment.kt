package com.example.movieapp.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.movieapp.databinding.FragmentContactsBinding
import com.example.movieapp.databinding.ProgressBarAndErrorMsgBinding
import com.example.movieapp.entities.Contact
import com.example.movieapp.entities.ScreenState
import com.example.movieapp.utils.sendSms
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : Fragment() {
    companion object {
        fun newInstance(message: String) =
            ContactsFragment().apply {
                arguments = ContactsFragmentArgs(message).toBundle()
            }
    }

    private val args: ContactsFragmentArgs by navArgs()

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private var _includeBinding: ProgressBarAndErrorMsgBinding? = null
    private val includeBinding get() = _includeBinding!!

    private val contactsViewModel: ContactsViewModel by viewModel()

    private val onItemClickListener by lazy {
        object : ContactsAdapter.OnItemClickListener {
            override fun onItemClick(contact: Contact) {
                contact.phoneNumber?.let {
                    requireContext().sendSms(it, args.message ?: "")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsViewModel.fetchContacts(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        _includeBinding = ProgressBarAndErrorMsgBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contactsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        contactsViewModel.contacts.observe(viewLifecycleOwner) { state ->
            when (state) {
                ScreenState.Loading -> {
                    binding.contactsRecyclerView.isVisible = false
                    includeBinding.progressBar.isVisible = true
                }

                is ScreenState.Success -> {
                    includeBinding.progressBar.isVisible = false

                    with(binding.contactsRecyclerView) {
                        isVisible = true
                        adapter = ContactsAdapter(state.data, onItemClickListener)
                    }
                }

                is ScreenState.Error -> {
                    binding.contactsRecyclerView.isVisible = false

                    with(includeBinding) {
                        progressBar.isVisible = false
                        errorMsg.isVisible = true
                        errorMsg.text = state.error.toString()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}