package com.example.findmyip

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private  lateinit var apiService: APIService
    private lateinit var  viewModel: MainViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        viewModel= MainViewModel(apiService)
    }

    @Test
    fun fetchIPInfo_scucess()= runBlocking {
        val ipInfo=IPInfo("136.226.253.83","136.226.253.0/24", city = "Mumbai", region = "Maharashtra")
        when(apiService.getInfo()).thenReturn(ipInfo)

        viewModel.fetchIPInfo()

        assert((viewModel.ipInfo.value==ipInfo))
        assert((viewModel.loading.value==false))
        assert((viewModel.error.value==null))
    }

    @Test
    fun fetchIPInfo_failure()= runBlocking {
        val errorMessage="Error fetching"
        when(apiService.getInfo()).thenThrow(RuntimeException(errorMessage))

        viewModel.fetchIPInfo()

        assert((viewModel.ipInfo.value==null))
        assert((viewModel.loading.value==false))
        assert((viewModel.error.value==errorMessage))
    }
}