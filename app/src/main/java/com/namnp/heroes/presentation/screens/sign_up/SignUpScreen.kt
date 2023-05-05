package com.namnp.heroes.presentation.screens.sign_up

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.presentation.screens.login.AuthenViewModel
import com.namnp.heroes.ui.theme.*
import com.namnp.heroes.util.DarkThemTextFieldColors

//@Preview(showBackground = true)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(
    navController: NavHostController,
    authenViewModel: AuthenViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    val textFieldColors: TextFieldColors = if(isSystemInDarkTheme())
        DarkThemTextFieldColors()
    else TextFieldDefaults.outlinedTextFieldColors()

    val emailState = remember { mutableStateOf(TextFieldValue("namnpse@gmail.com")) }
    val nicknameState = remember { mutableStateOf(TextFieldValue("Nam Jr")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }
    val confirmPasswordState = remember { mutableStateOf(TextFieldValue("")) }
    val bioState = remember { mutableStateOf(TextFieldValue("Mobile Developer")) }

    val signin = stringResource(R.string.log_in)
    val signinText = buildAnnotatedString {
        withStyle(
            SpanStyle(color = Color.LightGray, fontSize = 14.sp)
        ) {
            append("${stringResource(R.string.already_have_an_account)} ")
        }
        withStyle(SpanStyle(
            color = Purple500,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )) {
            pushStringAnnotation(tag = signin, annotation = signin)
            append(signin)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
//                    Text(text = "TopAppBar")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "Back",
//                            tint = Purple500,
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
                contentColor = Color.White,
                elevation = 12.dp
            )
        },
        backgroundColor = MaterialTheme.colors.welcomeScreenBackgroundColor,
        content = {
            Column(
                modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                Spacer(modifier = Modifier.padding(16.dp))
                OutlinedTextField(
                    value = emailState.value,
                    colors = textFieldColors,
                    onValueChange = {
                        emailState.value = it
                    },
                    label = {
                        Text(text = stringResource(R.string.email))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            contentDescription = "Email"
                        )
                    },
                    modifier = modifier
                )

                Spacer(modifier = Modifier.padding(6.dp))
                OutlinedTextField(
                    value = passwordState.value,
                    colors = textFieldColors,
                    onValueChange = {
                        passwordState.value = it
                    },
                    label = {
                        Text(text = stringResource(R.string.password))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            contentDescription = "Password"
                        )
                    },
                    modifier = modifier,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.padding(6.dp))
                OutlinedTextField(
                    value = confirmPasswordState.value,
                    colors = textFieldColors,
                    onValueChange = {
                        confirmPasswordState.value = it
                    },
                    label = {
                        Text(text = stringResource(R.string.confirm_password))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            contentDescription = "Password"
                        )
                    },
                    modifier = modifier,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.padding(6.dp))
                OutlinedTextField(
                    value = nicknameState.value,
                    colors = textFieldColors,
                    onValueChange = {
                        nicknameState.value = it
                    },
                    label = {
                        Text(text = stringResource(R.string.nickname))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Nickname"
                        )
                    },
                    modifier = modifier
                )

                Spacer(modifier = Modifier.padding(6.dp))
                OutlinedTextField(
                    value = bioState.value,
                    colors = textFieldColors,
                    onValueChange = {
                        bioState.value = it
                    },
                    label = {
                        Text(text = stringResource(R.string.bio))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Description,
                            contentDescription = "Description"
                        )
                    },
                    modifier = modifier
                )

                Spacer(modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(1f))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(16.dp),
                    modifier = modifier,
                    contentPadding = PaddingValues(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Purple500)
                ) {
                    Text(text = stringResource(R.string.create_an_account), style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp))
                }

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                ClickableText(text = signinText, onClick = { offset ->
                    signinText.getStringAnnotations(offset, offset)
                        .firstOrNull()?.let { span ->
                            navController.popBackStack()
                        }
                })
            }
        },
    )
}