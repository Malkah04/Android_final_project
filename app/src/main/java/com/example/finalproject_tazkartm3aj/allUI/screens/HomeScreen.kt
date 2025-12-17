import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.finalproject_tazkartm3aj.database.dDatabase
import com.example.finalproject_tazkartm3aj.repository.scheduleRep.OfflineScheduleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SubjectUI(
    val id: Int,
    val name: String,
    val color: Color,
    val iconRes: Int = com.example.finalproject_tazkartm3aj.R.drawable.ic_launcher_foreground
)

@Composable
fun HomeScreen(navController: NavController){
    val context = LocalContext.current
    val db = dDatabase.getDatabase(context)
    val repo = OfflineScheduleRepository(db.scheduleDao())

     val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.provideFactory(repo)
    )

    val subjects by viewModel.subjectsState.collectAsState()
    SubjectsSection(
        subjects = subjects,
        onSubjectSelected = { subject ->
            val encodedSubject = java.net.URLEncoder.encode(subject.name, "UTF-8")
            navController.navigate("schedule/$encodedSubject")
        }
    )

}

@Composable
fun SubjectsSection(subjects: List<SubjectUI>,onSubjectSelected: (SubjectUI) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(" Subjects", fontWeight = FontWeight.Bold, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 1000.dp)
        ) {
            items(subjects) { subject ->
                SubjectCard(subject=subject,
                    onSubjectClick = { onSubjectSelected(subject) } )
            }
        }
    }
}

@Composable
fun SubjectCard(subject: SubjectUI,onSubjectClick: (Int) -> Unit  ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSubjectClick(subject.id) },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = subject.color
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            androidx.compose.foundation.Image(
                painter = painterResource(id = subject.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = subject.name,
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF424242)
                )
            )
        }
    }
}