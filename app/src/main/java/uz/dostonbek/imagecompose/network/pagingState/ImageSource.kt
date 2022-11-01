package uz.dostonbek.imagecompose.network.pagingState

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uz.dostonbek.imagecompose.network.ApiService
import java.io.IOException

class ImageSource (
    private val apiService: ApiService
        ): PagingSource<Int, uz.dostonbek.imagecompose.models.Result>() {
    
    override fun getRefreshKey(state: PagingState<Int, uz.dostonbek.imagecompose.models.Result>): Int? {
        return state.anchorPosition?.let { position->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>):            LoadResult<Int, uz.dostonbek.imagecompose.models.Result> {
        return try {
            val nextPage = params.key ?: 1
            val userList = apiService.getImage("animals",nextPage)
            if (nextPage>1){
                return LoadResult.Page(userList.results,nextPage-1,nextPage+1)
            }else{
                return LoadResult.Page(userList.results,null,nextPage+1)
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}