package com.example.indivassignment3q5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.indivassignment3q5.ui.theme.IndivAssignment3Q5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndivAssignment3Q5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginForm()
                }
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    // State for input fields, to hold and react to user input
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // State for validation, to track and display errors
    var usernameError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    // Context is needed for Android-specific operations like showing a Toast
    val context = LocalContext.current

    // Column arranges the children vertically; centering provides a standard form layout
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp)) // Provides visual separation

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = false // Clears error on input change for better UX
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            isError = usernameError, // To visually indicate if there's an error
            supportingText = { // To display a message below the field, typically for errors
                if (usernameError) {
                    Text(
                        "Username cannot be empty",
                        color = MaterialTheme.colorScheme.error // Uses theme's error color
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false // UX: clear error when user starts correcting
            },
            label = { Text("Password") },
            // Hides password characters for security
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError, // To visually signal a password validation error
            supportingText = { // To provide specific feedback about password errors
                if (passwordError) {
                    Text(
                        "Password cannot be empty",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Determine error states based on current input values
                usernameError = username.isBlank()
                passwordError = password.isBlank()

                // Proceeds only if there are no validation errors
                if (!usernameError && !passwordError) {
                    Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    IndivAssignment3Q5Theme {
        Surface {
            LoginForm()
        }
    }
}
