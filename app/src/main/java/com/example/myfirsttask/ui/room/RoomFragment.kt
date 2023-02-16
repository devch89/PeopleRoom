package com.example.myfirsttask.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirsttask.data.model.room.RoomModel
import com.example.myfirsttask.databinding.FragmentRoomBinding
import com.example.myfirsttask.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        viewModel.getRoomList()

        return root
    }

    private fun initViews(data: RoomModel?) {
        data?.let {
            binding.rvRoom.layoutManager = LinearLayoutManager(context)
            binding.rvRoom.adapter = RoomAdapter(
                it
            ) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}