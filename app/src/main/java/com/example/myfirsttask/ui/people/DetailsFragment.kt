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
import com.example.myfirsttask.databinding.DetailsFragmentBinding
import com.example.myfirsttask.databinding.FragmentPeopleBinding
import com.example.myfirsttask.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        viewModel.clickedEmployee.observe(viewLifecycleOwner) {
            binding.apply {
                tvName.text = "${it.firstName} ${it.lastName}"
                tvEmail.text = "${it.email}"
                tvFavoriteColor.text = "Fun Fact: My favorite color is " + "${it.favouriteColor}."
            }
//            when (it) {
//                is ResponseType.Loading -> {
//                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
//                }
//                is ResponseType.Success -> {
//                    initViews(it.data)
//                }
//                is ResponseType.Error -> {
//                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//                }
//            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}