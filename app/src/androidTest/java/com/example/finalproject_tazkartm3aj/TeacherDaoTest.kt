package com.example.finalproject_tazkartm3aj

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.finalproject_tazkartm3aj.database.dDatabase
import com.example.finalproject_tazkartm3aj.database.TeacherDatabaseDao
import com.example.finalproject_tazkartm3aj.model.Teacher
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TeacherDaoTest {

    private lateinit var db: dDatabase
    private lateinit var teacherDao: TeacherDatabaseDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, dDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        teacherDao = db.teacherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndSearchTeacher() = runBlocking {
        val teacher1 = Teacher(
            name = "Mr. Ali",
            phone = "01012345678",
            subject = "Math"
        )

        val teacher2 = Teacher(
            name = "Mr. Hassan",
            phone = "01122334455",
            subject = "Physics"
        )

        teacherDao.addTeacher(teacher1)
        teacherDao.addTeacher(teacher2)

        val searchResults = teacherDao.searchByTeacherName("Ali").first()

        assertEquals(1, searchResults.size)
        assertEquals("Mr. Ali", searchResults[0].name)
        assertEquals("Math", searchResults[0].subject)
    }
}