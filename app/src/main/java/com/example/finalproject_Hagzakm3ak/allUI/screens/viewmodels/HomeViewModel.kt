
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.finalproject_Hagzakm3ak.repository.scheduleRep.ScheduleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val repository: ScheduleRepository) : ViewModel() {
    private val staticSubjects = listOf(
        SubjectUI(id = 1, name = "Chemistry", color = Color(0xFFE8F5E9), iconRes = com.example.finalproject_Hagzakm3ak.R.drawable.chemistry),
        SubjectUI(id = 2, name = "Physics", color = Color(0xFFFFF3E0), iconRes = com.example.finalproject_Hagzakm3ak.R.drawable.physics),
        SubjectUI(id = 3, name = "Math", color = Color(0xFFE3F2FD), iconRes = com.example.finalproject_Hagzakm3ak.R.drawable.math),
        SubjectUI(id = 4, name = "Biology", color = Color(0xFFF1F8E9), iconRes = com.example.finalproject_Hagzakm3ak.R.drawable.dna),
        SubjectUI(id = 5, name = "Arabic", color = Color(0xFFFFEBEE), iconRes = com.example.finalproject_Hagzakm3ak.R.drawable.book),
        SubjectUI(id = 6, name = "English", color = Color(0xFFF3E5F5), iconRes = com.example.finalproject_Hagzakm3ak.R.drawable.eng)
    )
    private val _subjectsState = MutableStateFlow(staticSubjects)
    val subjectsState: StateFlow<List<SubjectUI>> = _subjectsState.asStateFlow()

    companion object {
        fun provideFactory(repository: ScheduleRepository): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(repository) as T
            }
        }
    }
}