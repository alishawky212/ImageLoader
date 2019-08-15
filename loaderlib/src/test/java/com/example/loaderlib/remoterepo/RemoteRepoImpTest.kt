package com.example.loaderlib.remoterepo

import com.example.loaderlib.memorycacheing.CacheRepo
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.MockitoAnnotations
import java.lang.Exception
import java.net.MalformedURLException

class RemoteRepoImpTest {

    @Mock
    lateinit var remoteRepo:RemoteRepo

    lateinit var remoteRepoImp: RemoteRepoImp

    @Mock
    private lateinit var cacheRepo: CacheRepo

    private val exception = ExpectedException.none()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        remoteRepoImp = RemoteRepoImp(cacheRepo)
    }

    @Test
    fun getRemoteStreamError() {
        var response:ByteArray? = null
        try {
            response = remoteRepo.getRemoteStream("")
        }catch (e:Exception){
            exception.expect(MalformedURLException::class.java)
        } finally {
            assertNull(response)
        }
    }
}