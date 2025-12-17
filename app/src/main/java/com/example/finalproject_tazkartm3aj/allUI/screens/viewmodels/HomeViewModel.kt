
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.finalproject_tazkartm3aj.repository.scheduleRep.ScheduleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val repository: ScheduleRepository) : ViewModel() {
    private val staticSubjects = listOf(
        SubjectUI(id = 1, name = "كيمياء", color = Color(0xFFE8F5E9), iconRes = com.example.finalproject_tazkartm3aj.R.drawable.chemistry),
        SubjectUI(id = 2, name = "فيزياء", color = Color(0xFFFFF3E0), iconRes = com.example.finalproject_tazkartm3aj.R.drawable.physics),
        SubjectUI(id = 3, name = "رياضيات", color = Color(0xFFE3F2FD), iconRes = com.example.finalproject_tazkartm3aj.R.drawable.math),
        SubjectUI(id = 4, name = "أحياء", color = Color(0xFFF1F8E9), iconRes = com.example.finalproject_tazkartm3aj.R.drawable.dna),
        SubjectUI(id = 5, name = "عربية", color = Color(0xFFFFEBEE), iconRes = com.example.finalproject_tazkartm3aj.R.drawable.book),
        SubjectUI(id = 6, name = "إنجليزية", color = Color(0xFFF3E5F5), iconRes = com.example.finalproject_tazkartm3aj.R.drawable.eng)
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