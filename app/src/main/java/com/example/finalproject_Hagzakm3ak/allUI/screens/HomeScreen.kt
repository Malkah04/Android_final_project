import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalproject_Hagzakm3ak.database.dDatabase
import com.example.finalproject_Hagzakm3ak.repository.scheduleRep.OfflineScheduleRepository

data class SubjectUI(
    val id: Int,
    val name: String,
    val color: Color,
    val iconRes: Int = com.example.finalproject_Hagzakm3ak.R.drawable.ic_launcher_foreground
)

@Composable
fun HomeScreen(navController: NavController) {
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
fun SubjectsSection(subjects: List<SubjectUI>, onSubjectSelected: (SubjectUI) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF003366))
                .padding(18.dp)
        ) {
            Text(
                text = "Subjects",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(subjects) { subject ->
                SubjectCard(
                    subject = subject,
                    onSubjectClick = { onSubjectSelected(subject) }
                )
            }
        }
    }
}

@Composable
fun SubjectCard(subject: SubjectUI, onSubjectClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { onSubjectClick(subject.id) }
            .padding(6.dp),

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
            Image(
                painter = painterResource(id = subject.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = subject.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF424242)
                )
            )
        }
    }
}