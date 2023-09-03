package com.yogadarma.core.data

import com.yogadarma.core.data.source.Resource
import com.yogadarma.core.data.source.remote.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB()
        if (shouldFetch(dbSource)) {
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.value)
                    loadFromDB()?.let {
                        emit(Resource.Success(it))
                    }
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.error ?: Throwable()))
                }
            }
        } else {
            loadFromDB()?.let {
                emit(Resource.Success(it))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun loadFromDB(): ResultType?

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType?)

    fun asFlow(): Flow<Resource<ResultType>> = result
}