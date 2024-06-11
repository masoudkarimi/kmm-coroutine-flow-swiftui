package ir.masoudkarimi.sample

import Greeting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _values = MutableStateFlow<List<String>>(listOf())
    val values: StateFlow<List<String>> get() = _values

    init {
        viewModelScope.launch {
            Greeting().countToTen().collect { phrase ->
                _values.update { it + phrase }
            }
        }
    }
}