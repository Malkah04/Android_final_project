package com.example.finalproject_Hagzakm3ak

import com.example.finalproject_Hagzakm3ak.allUI.addSchedule.AddScheduleVM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AddScheduleVMTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: AddScheduleVM
    private lateinit var fakeDao: FakeScheduleDao

    @Before
    fun setup() {
        fakeDao = FakeScheduleDao()
        viewModel = AddScheduleVM(fakeDao)
    }

    @Test
    fun addScheduleIfValid_setsSuccessState() = runTest {
        viewModel.addScheduleIfValid(
            subject = "Math",
            day = "Sunday",
            time = "10 AM",
            centerId = "1",
            teacherId = "2",
            imageUri = null
        )

        assertEquals("success", viewModel.state.value)
        assertEquals(false, viewModel.isProcessing.value)
    }
}
