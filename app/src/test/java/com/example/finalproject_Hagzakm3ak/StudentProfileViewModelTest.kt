package com.example.finalproject_Hagzakm3ak

import com.example.finalproject_Hagzakm3ak.allUI.screens.viewmodels.StudentProfileViewModel
import com.example.finalproject_Hagzakm3ak.model.Student
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class StudentProfileViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: StudentProfileViewModel
    private lateinit var fakeDao: FakeStudentDao

    @Before
    fun setup() {
        fakeDao = FakeStudentDao()
        viewModel = StudentProfileViewModel(fakeDao)
    }

    @Test
    fun loadStudentByEmail_updatesStudentState() = runTest {
        val student = Student(
            name = "user0",
            email = "test@test.com",
            phone = "01162346578",
            password = "1111111",
            year = "1"
        )
        fakeDao.updateInformationOfStudent(student)

        viewModel.loadStudentByEmail("test@test.com")

        advanceUntilIdle()

        assertEquals(student, viewModel.student.value)
    }
}