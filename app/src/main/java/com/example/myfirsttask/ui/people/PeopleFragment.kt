package com.example.myfirsttask.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirsttask.R
import com.example.myfirsttask.data.model.people.PeopleModel
import com.example.myfirsttask.databinding.FragmentPeopleBinding
import com.example.myfirsttask.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPeopleBinding.inflate(inflater, container, false)


        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.Loading -> {
                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
                is ResponseType.Success -> {
                    initViews(it.data)
                }
                is ResponseType.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getPeopleList()

        return binding.root
    }

    private fun initViews(data: PeopleModel?) {

        parentFragmentManager.commit {
            replace(R.id.details_fragment, DetailsFragment())
        }

        data?.let {
            binding.rvPeople.layoutManager = LinearLayoutManager(context)
            binding.rvPeople.adapter = PeopleAdapter(it) { clickedEmployee ->
                viewModel.getEmployee(clickedEmployee)
                // Toast.makeText(context, "${it.firstName} clicked", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}