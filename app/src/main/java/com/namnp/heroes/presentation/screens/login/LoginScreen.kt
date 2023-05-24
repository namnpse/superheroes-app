package com.namnp.heroes.presentation.screens.login

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.domain.model.*
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.ui.theme.Purple500
import com.namnp.heroes.ui.theme.statusBarColor
import com.namnp.heroes.ui.theme.welcomeScreenBackgroundColor
import com.namnp.heroes.util.DarkThemTextFieldColors
import kotlinx.coroutines.launch

//@Preview(showBackground = true)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`
    val coroutineScope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        scaffoldState = scaffoldState // attaching `scaffoldState` to the `Scaffold`
    ) {
        Box {
            BackgroundCard(navController)
            LoginCard(
                viewModel = viewModel,
                onLogin = { email, password ->
                    keyboard?.hide()
                    viewModel.signInWithEmailAndPassword(email, password)
                },
                context = context,
                showErrorMessage = { errorMessage ->
                    coroutineScope.launch { // using the `coroutineScope` to `launch` showing the snackbar
                        // taking the `snackbarHostState` from the attached `scaffoldState`
                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                            message = errorMessage ?: "",
                            actionLabel = "OK"
                        )
                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> Log.d("SnackbarLogin", "Dismissed")
                            SnackbarResult.ActionPerformed -> Log.d(
                                "SnackbarLogin",
                                "Snackbar's button clicked"
                            )
                        }
                    }
                },
                onLoginSuccess = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }
    }
}

@Composable
fun BackgroundCard(navController: NavHostController) {
    val signup = stringResource(R.string.sign_up)
    val signupText = buildAnnotatedString {
        withStyle(
            SpanStyle(color = Color.LightGray, fontSize = 14.sp)
        ) {
            append(stringResource(R.string.dont_have_an_account))
        }
        withStyle(
            SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        ) {
            pushStringAnnotation(tag = signup, annotation = signup)
            append(signup)
        }
    }
    Surface(
        color = Purple500,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = (-16).dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_fb),
                    contentDescription = stringResource(R.string.app_logo),
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = stringResource(R.string.app_logo),
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_twitter),
                    contentDescription = stringResource(R.string.app_logo),
                )

            }
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            ClickableText(text = signupText, onClick = { offset ->
                signupText.getStringAnnotations(offset, offset)
                    .firstOrNull()?.let { span ->
                        navController.navigate(Screen.SignUpScreen.route)
                    }
            })
        }
    }
}


@Composable
fun LoginCard(
    viewModel: SignInViewModel,
    onLogin: (String, String) -> Unit,
    context: Context,
    showErrorMessage: (errorMessage: String?) -> Unit,
    onLoginSuccess: () -> Unit,
) {
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    val passState = remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val textFieldColors: TextFieldColors = if (MaterialTheme.colors.isLight)
        TextFieldDefaults.outlinedTextFieldColors()
    else DarkThemTextFieldColors()
    Surface(
        color = MaterialTheme.colors.welcomeScreenBackgroundColor, modifier = Modifier
            .height(600.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(60.dp)
            .copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
    ) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            Image(
                painter = painterResource(id = R.drawable.shield),
                contentDescription = stringResource(R.string.app_logo),
                colorFilter = ColorFilter.tint(Purple500)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = emailState.value,
                maxLines = 1,
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
                value = passState.value,
                maxLines = 1,
                colors = textFieldColors,
                onValueChange = {
                    passState.value = it
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 32.dp))
            val signInResponse = viewModel.signInResponse
            if (signInResponse is Response.Failure) {
                signInResponse.apply {
                    LaunchedEffect(error) {
                        println(error.message)
                        showErrorMessage(error.message)
                    }
                }
            }
            if(signInResponse is Response.Success && signInResponse.data == true) {
                LaunchedEffect(key1 = true) {
                    onLoginSuccess()
                }
            }
            if (signInResponse is Response.Loading) CircularProgressIndicator()
            else Button(
                onClick = {
                    onLogin(emailState.value.text, passState.value.text)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = modifier,
                contentPadding = PaddingValues(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple500)
            ) {
                Text(
                    text = stringResource(R.string.log_in),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                )
            }
        }
    }
}