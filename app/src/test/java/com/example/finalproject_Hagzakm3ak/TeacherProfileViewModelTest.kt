package com.example.finalproject_Hagzakm3ak

import com.example.finalproject_Hagzakm3ak.allUI.screens.viewmodels.TeacherProfileViewModel
import com.example.finalproject_Hagzakm3ak.model.Teacher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TeacherProfileViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TeacherProfileViewModel
    private lateinit var fakeRepo: FakeTeacherRepository

    @Before
    fun setup() {
        fakeRepo = FakeTeacherRepository()
        viewModel = TeacherProfileViewModel(fakeRepo)
    }

    @Test
    fun loadTeacherById_updatesTeacherState() = runTest {
        val teacher = Teacher(
            name = "teacher0",
            phone = "01000000001",
            subject = "Math"
        )
        fakeRepo.updateInformationOfTeacher(teacher)

        viewModel.loadTeacherById(1)

        advanceUntilIdle()

        assertEquals(teacher, viewModel.teacher.value)
    }
}
