package uz.dostonbek.imagecompose.vm.imageVm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.dostonbek.imagecompose.network.ApiService
import uz.dostonbek.imagecompose.network.pagingState.ImageSource
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val apiService: ApiService
):ViewModel()  {
    val images: Flow<PagingData<uz.dostonbek.imagecompose.models.Result>> =
        Pager(PagingConfig(pageSize = 10)) { ImageSource(apiService)
    }.flow.cachedIn(viewModelScope)
}