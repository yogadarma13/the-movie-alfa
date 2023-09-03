package com.yogadarma.core.data.source.local

import com.yogadarma.core.data.source.local.room.dao.MovieDao
import com.yogadarma.core.data.source.local.room.dao.RemoteKeysDao
import com.yogadarma.core.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocalDataSourceTest {

    @Mock
    private lateinit var movieDao: MovieDao

    @Mock
    private lateinit var remoteKeysDao: RemoteKeysDao

    private lateinit var localDataSourceImpl: LocalDataSourceImpl

    @Before
    fun setup() {
        localDataSourceImpl = LocalDataSourceImpl(movieDao, remoteKeysDao)
    }

    @Test
    fun `verify function insertMovies`() = runTest {
        val dummyData = DummyData.generateMoviesListEntity()
        localDataSourceImpl.insertMovies(dummyData)

        Mockito.verify(movieDao).insertMovies(dummyData)
    }

    @Test
    fun `verify function insertMovieDetail`() = runTest {
        localDataSourceImpl.insertMovieDetail("1234", "20222-06-22", "Action")

        Mockito.verify(movieDao).insertMovieDetail("1234", "20222-06-22", "Action")
    }

    @Test
    fun `verify function getMoviesData`() = runTest {
        localDataSourceImpl.getMoviesData()

        Mockito.verify(movieDao).getMovies()
    }

    @Test
    fun `verify function getMovieDetail and return MovieEntity data`() = runTest {
        val dummyData = DummyData.generateMovieEntity()
        `when`(movieDao.getMovieDetail("615656")).thenReturn(dummyData)

        val actualResult = localDataSourceImpl.getMovieDetail("615656")

        assertEquals(dummyData, actualResult)
        assertEquals(dummyData.id, actualResult.id)
        assertEquals(dummyData.title, actualResult.title)
        assertEquals(dummyData.poster, actualResult.poster)
        assertEquals(dummyData.overview, actualResult.overview)
        assertEquals(dummyData.releaseDate, actualResult.releaseDate)
        assertEquals(dummyData.voteAverage, actualResult.voteAverage)
        assertEquals(dummyData.genres, actualResult.genres)

        Mockito.verify(movieDao).getMovieDetail("615656")
    }

    @Test
    fun `verify function deleteAllMovies`() = runTest {
        localDataSourceImpl.deleteAllMovies()

        Mockito.verify(movieDao).deleteAll()
    }

    @Test
    fun `verify function insertAllKeys`() = runTest {
        val dummyData = DummyData.generateRemoteKeysEntityList()
        localDataSourceImpl.insertAllKeys(dummyData)

        Mockito.verify(remoteKeysDao).insertAll(dummyData)
    }

    @Test
    fun `verify function getRemoteKeysById and return RemoteKeysEntity data`() = runTest {
        val dummyData = DummyData.generateRemoteKeysEntityList()[0]
        `when`(remoteKeysDao.getRemoteKeysById("key1")).thenReturn(dummyData)

        val actualResult = localDataSourceImpl.getRemoteKeysById("key1")
        assertEquals(dummyData, actualResult)
        assertEquals(dummyData.id, actualResult?.id)
        assertEquals(dummyData.prevKey, actualResult?.prevKey)
        assertEquals(dummyData.nextKey, actualResult?.nextKey)

        Mockito.verify(remoteKeysDao).getRemoteKeysById("key1")
    }

    @Test
    fun `verify function deleteRemoteKeys`() = runTest {
        localDataSourceImpl.deleteRemoteKeys()

        Mockito.verify(remoteKeysDao).deleteRemoteKeys()
    }
}