package com.example.finalproject_Hagzakm3ak

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.example.finalproject_Hagzakm3ak.allUI.login.LoginScreen
import com.example.finalproject_Hagzakm3ak.allUI.login.LoginViewModel
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun makeViewModel(): LoginViewModel {
        val repo = FakeOfflineStudentRepository()
        return LoginViewModel(repo)
    }

    @Test
    fun register_button_triggersCallback() {
        var registerClicked = false
        val vm = makeViewModel()

        composeTestRule.setContent {
            LoginScreen(
                onRegisterClick = { registerClicked = true },
                onLoginSuccess = {},
                viewModel = vm
            )
        }

        composeTestRule.onNodeWithText("Don’t have an account? Register").performClick()
        assert(registerClicked)
    }

}