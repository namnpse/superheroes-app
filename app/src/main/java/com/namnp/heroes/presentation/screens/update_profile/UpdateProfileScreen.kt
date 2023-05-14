package com.namnp.heroes.presentation.screens.update_profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.ui.theme.*
import com.namnp.heroes.util.DarkThemTextFieldColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//@Preview(showBackground = true)
@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateProfileScreen(
    navController: NavHostController,
    viewModel: UpdateProfileViewModel = hiltViewModel()
) {

    val keyboard = LocalSoftwareKeyboardController.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    val textFieldColors: TextFieldColors = if (MaterialTheme.colors.isLight)
        TextFieldDefaults.outlinedTextFieldColors()
    else DarkThemTextFieldColors()

    val emailState = remember { mutableStateOf(TextFieldValue("nam12345@gmail.com")) }
    val phoneState = remember { mutableStateOf(TextFieldValue("0123456789")) }
    val nicknameState = remember { mutableStateOf(TextFieldValue("Nam Jr")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("123456")) }
    val confirmPasswordState = remember { mutableStateOf(TextFieldValue("123456")) }
    val bioState = remember { mutableStateOf(TextFieldValue("Mobile Developer")) }

    val signin = stringResource(R.string.log_in)
    val signinText = buildAnnotatedString {
        withStyle(
            SpanStyle(color = Color.LightGray, fontSize = 14.sp)
        ) {
            append("${stringResource(R.string.already_have_an_account)} ")
        }
        withStyle(
            SpanStyle(
                color = Purple500,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        ) {
            pushStringAnnotation(tag = signin, annotation = signin)
            append(signin)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "Back",
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
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
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
                    value = phoneState.value,
                    colors = textFieldColors,
                    onValueChange = {
                        phoneState.value = it
                    },
                    label = {
                        Text(text = stringResource(R.string.phone))
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Phone,
                            contentDescription = "Phone"
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

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .weight(1f)
                )
                val signUpResponse = viewModel.signUpResponse
                val saveUserResponse = viewModel.saveUserResponse
                if (signUpResponse is Response.Failure) {
                    signUpResponse.apply {
//                        showSnackBar(
//                            scaffoldState = scaffoldState,
//                            coroutineScope = coroutineScope,
//                            message = error.message
//                        )
                        LaunchedEffect(error.message) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = error.message ?: "",
                            )
                        }
                    }
                }

                if (saveUserResponse is Response.Failure) {
                    saveUserResponse.apply {
                        LaunchedEffect(error.message) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = error.message ?: "",
                            )
                        }
                    }
                }
                if (signUpResponse is Response.Success && signUpResponse.data != null && !viewModel.isSignUpSuccess) {
                    val userInfo = User(
                        id = signUpResponse.data.user?.uid,
                        nickName = nicknameState.value.text,
                        email = emailState.value.text,
                        phone = phoneState.value.text,
                        bio = bioState.value.text,
                    )
                    viewModel.saveUserToFirestore(userInfo)
                }
                if (saveUserResponse is Response.Success && saveUserResponse.data == true) {
                    LaunchedEffect(saveUserResponse.data) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Sign up successfully",
                        )
                        navController.popBackStack()
                    }
                }
                if (signUpResponse is Response.Loading || saveUserResponse is Response.Loading) CircularProgressIndicator()
                else Button(
                    onClick = {
                        keyboard?.hide()
                        viewModel.signUpWithEmailAndPassword(
                            emailState.value.text,
                            passwordState.value.text
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = modifier,
                    contentPadding = PaddingValues(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Purple500)
                ) {
                    Text(
                        text = stringResource(R.string.update),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
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

private fun showSnackBar(
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope,
    message: String?,
    label: String? = "",
) {
//    LaunchedEffect(key1 = true) {
//        scaffoldState.snackbarHostState.showSnackbar(
//            message = "Sign up successfully",
//        )
//    }
//    coroutineScope.launch {
//        scaffoldState.snackbarHostState.showSnackbar(
//            message = message ?: "",
//            actionLabel = label ?: "OK"
//        )
//    }
}