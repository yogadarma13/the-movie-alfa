package com.yogadarma.core.data

import com.yogadarma.core.data.source.local.LocalDataSource
import com.yogadarma.core.data.source.remote.RemoteDataSource
import com.yogadarma.core.domain.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

}
