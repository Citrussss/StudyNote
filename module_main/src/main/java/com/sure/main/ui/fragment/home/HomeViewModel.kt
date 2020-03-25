package com.sure.main.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sure.network.data.Result
import com.sure.network.data.api.model.Home
import com.sure.network.data.home.HomeRepository
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
        //说明ViewModel没有随着Activity销毁
        count.value = count.value?.plus(1)
        if (homeData.value == null) {
            val result = homeRepository.loadStories(page)
            if (result is Result.Success) {
                homeData.postValue(result.data.datas)
            }
        }
    }
}