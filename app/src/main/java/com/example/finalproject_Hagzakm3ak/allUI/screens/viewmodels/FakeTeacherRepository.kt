package com.example.finalproject_Hagzakm3ak.allUI.screens.viewmodels

import com.example.finalproject_Hagzakm3ak.model.Teacher
import com.example.finalproject_Hagzakm3ak.repository.teacherRep.TeacherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeTeacherRepository : TeacherRepository {

    private val teachers = mutableListOf(
        Teacher(_id = 1, name = "Ahmed", phone = "0100000000", subject = "Math"),
        Teacher(_id = 2, name = "Sara", phone = "0111111111", subject = "Physics"),
        Teacher(_id = 3, name = "Sara", phone = "0111111111", subject = "Math"),
        Teacher(_id = 4, name = "Sara", phone = "0111111111", subject = "Physics"),
        Teacher(_id = 5, name = "Sara", phone = "0111111111", subject = "Physics"),
        Teacher(_id = 6, name = "Sara", phone = "0111111111", subject = "Physics"),
        Teacher(_id = 7, name = "Sara", phone = "0111111111", subject = "Physics")


    )

    override fun getAllTeacher(): Flow<List<Teacher>> = flowOf(teachers.toList())

    override fun getTeacherById(id: Int): Flow<Teacher> {
        val teacher = teachers.firstOrNull { it._id == id }
            ?: Teacher(_id = 0, name = "", phone = "", subject = "")
        return flowOf(teacher)
    }

    // NOTE: Non-suspend to match the interface
    override fun deleteAllTeacher() {
        teachers.clear()
    }

    override suspend fun addTeacher(teacher: Teacher) {
        val index = teachers.indexOfFirst { it._id == teacher._id }
        if (index == -1) {
            teachers.add(teacher)
        } else {
            teachers[index] = teacher
        }
    }

    override suspend fun updateInformationOfTeacher(teacher: Teacher) {
        val index = teachers.indexOfFirst { it._id == teacher._id }
        if (index != -1) {
            teachers[index] = teacher
        }
    }

    override suspend fun deleteTeacher(id: Int) {
        val index = teachers.indexOfFirst { it._id == id }
        if (index != -1) {
            teachers.removeAt(index)
        }
    }

    override fun searchByTeacherName(name: String): Flow<List<Teacher>> {
        val result = teachers.filter { it.name.contains(name, ignoreCase = true) }
        return flowOf(result)
    }

    override suspend fun isTeacherExist(name: String, subject: String): Boolean {
        return teachers.any {
            it.name.equals(name, ignoreCase = true) &&
                    it.subject.equals(subject, ignoreCase = true)
        }
    }
}