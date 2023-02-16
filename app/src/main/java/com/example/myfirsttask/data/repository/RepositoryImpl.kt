package com.example.myfirsttask.data.repository

import com.example.myfirsttask.data.model.people.PeopleModel
import com.example.myfirsttask.data.model.room.RoomModel
import com.example.myfirsttask.data.remote.ApiDetails
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiDetails: ApiDetails
) : Repository {

    override suspend fun getPeopleList(): Response<PeopleModel> {
        return apiDetails.getPeopleList()
    }

    override suspend fun getRoomList(): Response<RoomModel> {
        return apiDetails.getRoomList()
    }
}