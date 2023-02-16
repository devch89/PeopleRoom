package com.example.myfirsttask.data.remote

import com.example.myfirsttask.data.model.people.PeopleModel
import com.example.myfirsttask.data.model.room.RoomModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiDetails {

    @GET(ApiReference.PEOPLE)
    suspend fun getPeopleList(): Response<PeopleModel>

    @GET(ApiReference.ROOM)
    suspend fun getRoomList(): Response<RoomModel>
}