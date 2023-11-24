package com.mo.kmp_hello_world.data.repositories

import com.mo.kmp_hello_world.data.api.BASE_URL
import com.mo.kmp_hello_world.data.api.KtorClient
import com.mo.kmp_hello_world.data.api.LAUNCHED_ROCKETS_ENDPOINT
import com.mo.kmp_hello_world.data.api.RequestState
import com.mo.kmp_hello_world.data.models.RocketLaunch
import com.mo.kmp_hello_world.utils.getDateFromUTC
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketsRepository {
    private suspend fun getDateOfLastSuccessfulLaunch(): List<RocketLaunch> {
        // GETTING a list of launched rockets from the endpoint launched
        val rockets: List<RocketLaunch> =
            KtorClient().httpClient.get("$BASE_URL$LAUNCHED_ROCKETS_ENDPOINT").body()
        //return mapped version of the list to get local dates not utc
        return rockets.map {
            it.copy(
                launchDate =  getDateFromUTC(it.launchDate)
            )
        }
    }

    fun getLastSuccessfulLaunchDate(): Flow<RequestState<List<RocketLaunch>>> = flow {
        emit(RequestState.Loading())
        try {
            emit(RequestState.Success(getDateOfLastSuccessfulLaunch()))
        } catch (e: Exception) {
            emit(RequestState.Error(e.message))
        }
    }
}