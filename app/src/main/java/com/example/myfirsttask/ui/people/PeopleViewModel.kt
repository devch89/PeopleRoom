package com.example.myfirsttask.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirsttask.data.model.people.PeopleModel
import com.example.myfirsttask.data.model.people.PeopleModelItemModel
import com.example.myfirsttask.data.repository.Repository
import com.example.myfirsttask.util.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _clickedEmployee = MutableLiveData<PeopleModelItemModel>()
    val clickedEmployee: LiveData<PeopleModelItemModel> = _clickedEmployee

    private val _result = MutableLiveData<ResponseType<PeopleModel>>()
    val result: LiveData<ResponseType<PeopleModel>> = _result

    fun getPeopleList() {
        viewModelScope.launch {
            _result.postValue(ResponseType.Loading())
            val response = repository.getPeopleList()
            if (response.isSuccessful) {
                _result.postValue(ResponseType.Success(response.body()!!))
            } else {
                _result.postValue(ResponseType.Error(response.message()))
            }
        }
    }

    fun getEmployee(clickedEmployee: PeopleModelItemModel) {
        _clickedEmployee.postValue(clickedEmployee)
    }
}