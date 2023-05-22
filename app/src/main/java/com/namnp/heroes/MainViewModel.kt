package com.namnp.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namnp.heroes.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.namnp.heroes.domain.repository.AuthRepository
import com.namnp.heroes.ui.theme.Theme
import com.namnp.heroes.ui.theme.ThemeState
import com.namnp.heroes.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repo: AuthRepository
): ViewModel() {

    private val _themeState: MutableStateFlow<ThemeState> = MutableStateFlow(ThemeState(theme = Theme.Light))
    val themeState: StateFlow<ThemeState> = _themeState

    init {
        getTheme()
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    private fun getTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            val isDarkTheme = useCases.getDataStoreValueUseCase(
                key = Constants.PREFERENCES_KEY_DARK_THEME
            ).stateIn(viewModelScope).value
            _themeState.value = ThemeState(theme = if(isDarkTheme == "true") Theme.Dark else Theme.Light)
        }
    }
}