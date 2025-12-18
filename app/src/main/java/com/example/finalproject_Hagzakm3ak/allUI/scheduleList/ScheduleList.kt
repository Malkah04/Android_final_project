package com.example.finalproject_Hagzakm3ak.allUI.scheduleList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject_Hagzakm3ak.R
import com.example.finalproject_Hagzakm3ak.model.Schedule
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first

@Composable
fun ScheduleScreen(
    ScheduleVM: ScheduleListVM,
    isAdmin: Boolean = false,
    onEditClick: (Int) -> Unit ,
    onClickDetails: (Int) -> Unit,
    subject :String =""

) {
    val schedules  by if (subject.isNotEmpty()) {
        ScheduleVM.Search(subject).collectAsState(initial = emptyList())
    } else {
        ScheduleVM.scheduleList.collectAsState()
    }


    ScheduleList(
        schedules = schedules,
        scheduleListVM = ScheduleVM,
        isAdmin = isAdmin,
        onEditClick = onEditClick,
        onClickDetails = onClickDetails
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ScheduleList(
    schedules: List<Schedule>,
    scheduleListVM: ScheduleListVM,
    modifier: Modifier = Modifier,
    isAdmin: Boolean,
    onEditClick: (Int) -> Unit,
    onClickDetails: (Int) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    if (schedules.isEmpty()) {
        EmptyList()
        return
    }

    val visibleState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(animationSpec = spring(dampingRatio = DampingRatioLowBouncy)),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Column {
            Search(scheduleListVM)
            LazyColumn(contentPadding = contentPadding) {
                itemsIndexed(schedules) { index, schedule ->
                    TeacherListItem(
                        schedule = schedule,
                        scheduleListVM = scheduleListVM,
                        isAdmin = isAdmin,
                        onClickDetails = { onClickDetails(schedule._id) },
                        onEditClick = { onEditClick(schedule._id) },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = StiffnessVeryLow,
                                        dampingRatio = DampingRatioLowBouncy
                                    ),
                                    initialOffsetY = { it * (index + 1) }
                                )
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun TeacherListItem(
    schedule: Schedule,
    scheduleListVM: ScheduleListVM,
    isAdmin: Boolean,
    onEditClick: () -> Unit,
    onClickDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    var teacherName by remember { mutableStateOf("Loading...") }
    var centerName by remember { mutableStateOf("Loading...") }

    LaunchedEffect(schedule) {
        teacherName = scheduleListVM.getTeacherNameById(schedule.teacherId)
        centerName = scheduleListVM.getCenterNameById(schedule.centerId)
    }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        if (isAdmin) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { onEditClick() },
                    colors = buttonColors(containerColor = Color(0xFFF1970E))
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { scheduleListVM.deleteSchedule(schedule._id) },
                    colors = buttonColors(containerColor = Color.Red)
                ) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                    Spacer(Modifier.width(4.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Image section
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.android_superhero1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Info section
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = schedule.subject,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Day: ${schedule.day} | Time: ${schedule.time}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Teacher: $teacherName",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Center: $centerName",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Button(
                    onClick = { onClickDetails() },
                    colors = buttonColors(containerColor = Color(0xFFF1970E)),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(text = "See details", color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}
@Composable
fun EmptyList(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Image(
                painter = painterResource(R.drawable.nodatafound),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun Search(scheduleListVM: ScheduleListVM){
    var query by remember { mutableStateOf("") }

    LaunchedEffect(query) {
        snapshotFlow { query }
            .debounce(300)
            .collect { q->
                scheduleListVM.scheduleList.value =scheduleListVM.Search(q).first()
            }
    }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        label = { Text("Search by Teacher, Center, Subject, or Location") },
        modifier = Modifier.fillMaxWidth(),
    )
}


