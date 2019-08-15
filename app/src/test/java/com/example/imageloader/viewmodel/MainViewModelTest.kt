package com.example.imageloader.viewmodel

import com.example.imageloader.LiveDataTestUtil
import com.example.imageloader.createItem
import com.example.imageloader.models.Item
import com.example.imageloader.models.ItemState
import com.example.imageloader.repository.DataRepo
import com.example.imageloader.usecase.MainUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.spy


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel

    @Mock
    lateinit var dataRepo: DataRepo

    lateinit var useCase: MainUseCase

    private val items = listOf(createItem())

    private val itemState = ItemState.DataState(items)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = spy(MainUseCase(dataRepo))
        viewModel = MainViewModel(useCase,Schedulers.trampoline())
    }

    @Test
    fun getItemsList() {
        Mockito.`when`(useCase.getRemoteData()).thenReturn(Single.just(items))

        viewModel.getItemsList()

        assertEquals(itemState,LiveDataTestUtil.getValue(viewModel.getItemsLiveData()))
    }
}