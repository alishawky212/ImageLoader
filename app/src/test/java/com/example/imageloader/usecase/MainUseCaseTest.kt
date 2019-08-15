package com.example.imageloader.usecase

import com.example.imageloader.createItem
import com.example.imageloader.repository.DataRepo
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainUseCaseTest {

    lateinit var mainUseCase: MainUseCase

    @Mock
    lateinit var dataRepo: DataRepo

    private val items = listOf(createItem())

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainUseCase = MainUseCase(dataRepo)
    }

    @Test
    fun getRemoteData() {
        Mockito.`when`(dataRepo.getItemsList()).thenReturn(Single.just(items))

        val test = mainUseCase.getRemoteData().test()

        verify(dataRepo).getItemsList()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)

    }
}