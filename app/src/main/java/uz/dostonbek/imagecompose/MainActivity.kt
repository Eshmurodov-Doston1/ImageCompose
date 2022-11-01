package uz.dostonbek.imagecompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import uz.dostonbek.imagecompose.items.EmployeeItem
import uz.dostonbek.imagecompose.ui.theme.ImageComposeTheme
import uz.dostonbek.imagecompose.vm.imageVm.ImageViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val imageViewModel:ImageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserList(viewModel = imageViewModel, context = this)
                }
            }
        }
    }
}

@Composable
fun UserList(modifier: Modifier = Modifier, viewModel:ImageViewModel, context: Context) {
    ImageListPageing(modifier, userList = viewModel.images, context)
}

@Composable
fun ImageListPageing(modifier: Modifier, userList: Flow<PagingData<uz.dostonbek.imagecompose.models.Result>>, context: Context) {
    val userListItems: LazyPagingItems<uz.dostonbek.imagecompose.models.Result> = userList.collectAsLazyPagingItems()
    LazyColumn {
        items(userListItems) { item ->
            item.let {
                EmployeeItem(empData = it!!, onClick = {
                    Toast.makeText(context, item.toString(),   Toast.LENGTH_SHORT).show()
                })
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}
