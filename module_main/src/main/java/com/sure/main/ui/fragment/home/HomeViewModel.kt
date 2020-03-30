package com.sure.main.ui.fragment.home

import androidx.lifecycle.*
import com.sure.network.data.Result
import com.sure.network.data.api.model.Home
import com.sure.network.data.home.HomeRepository
import com.sure.network.onError
import kotlinx.coroutines.launch

/**
 * author pisa
 * date  2020/3/22
 * version 1.0
 * effect :
 */
class HomeViewModel(val homeRepository: HomeRepository = HomeRepository()) : ViewModel() {
    public val homeData = MutableLiveData<List<Home>>()
    public val content = homeData.map { it.toString() }
    public val count = MutableLiveData<Int>(0)
    private var page: Int = 0;
    fun loadData() = viewModelScope.launch {
        //        说明ViewModel没有随着Activity销毁
        try {
            count.value = count.value?.plus(1)
            if (homeData.value == null) {
                val result = homeRepository.loadStories(page)
                if (result is Result.Success) {
                    homeData.postValue(result.data.datas)
                }
            }
        } catch (e: Exception) {
            e.onError()?.printStackTrace()
        }
    }

    suspend fun loadData2() {
        try {

        } catch (e: Exception) {
            e.onError()?.printStackTrace()
        }
    }

    fun loadData2(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycleScope.launch {
            try {
                count.value = count.value?.plus(1)
                if (homeData.value == null) {
                    val result = homeRepository.loadStories(page)
                    if (result is Result.Success) {
                        homeData.postValue(result.data.datas)
                    }
                }
            } catch (e: Exception) {
                e.onError()?.printStackTrace()
            }
        }
    }
}