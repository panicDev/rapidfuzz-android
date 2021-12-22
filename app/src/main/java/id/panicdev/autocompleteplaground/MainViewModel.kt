package id.panicdev.autocompleteplaground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.panicdev.rapidfuzz.RapidFuzzCached
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {

    var mLocList = mutableListOf<String>()
    var mMenuList = mutableListOf<String>()

    private val queryChannel = MutableStateFlow("")

    fun search(search: String) {
        queryChannel.tryEmit(search)
    }

    @FlowPreview
    val searchResult = queryChannel
        .debounce(200)
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()
        .mapLatest { q ->
            RapidFuzzCached
                .extractAll(q, mLocList, 70.0)
                .sortedByDescending { it.score }
                .map { it.string }
                .toList()
        }
        .asLiveData()
}