package com.example.myfirsttask.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirsttask.data.model.room.RoomModel
import com.example.myfirsttask.data.repository.Repository
import com.example.myfirsttask.util.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

//    var currentTime : Long = 0

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    private val _result = MutableLiveData<ResponseType<RoomModel>>()
    val result: LiveData<ResponseType<RoomModel>> = _result

    fun getRoomList() {
        viewModelScope.launch {
            _result.postValue(ResponseType.Loading())
            val response = repository.getRoomList()
            if (response.isSuccessful) {
                _result.postValue(ResponseType.Success(response.body()!!))
            } else {
                _result.postValue(ResponseType.Error(response.message()))
            }
        }
    }


}