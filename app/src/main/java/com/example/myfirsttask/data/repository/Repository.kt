package com.example.myfirsttask.data.repository

import com.example.myfirsttask.data.model.people.PeopleModel
import com.example.myfirsttask.data.model.room.RoomModel
import retrofit2.Response

interface Repository {

    suspend fun getPeopleList() : Response<PeopleModel>

    suspend fun getRoomList() : Response<RoomModel>

}