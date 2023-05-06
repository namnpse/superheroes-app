package com.namnp.heroes.presentation.screens.login

import android.content.Context
import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import com.namnp.heroes.domain.model.*
import com.namnp.heroes.navigation.Screen
import com.namnp.heroes.ui.theme.Purple500
import com.namnp.heroes.ui.theme.statusBarColor
import com.namnp.heroes.ui.theme.welcomeScreenBackgroundColor
import com.namnp.heroes.util.DarkThemTextFieldColors
import com.namnp.heroes.util.Utils.Companion.showMessage

//@Preview(showBackground = true)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Box {
        BackgroundCard(navController)
        LoginCard(
            onLogin = { email, password ->
                viewModel.signInWithEmailAndPassword(email, password)
            }
        )
        SignIn(
            showErrorMessage = { errorMessage ->
                showMessage(context, errorMessage)
            },
            context = context,
        )
    }

}

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (errorMessage: String?) -> Unit,
    context: Context,
) {
    when(val signInResponse = viewModel.signInResponse) {
        is Response.Loading -> ProgressBar(context)
        is Response.Success -> Unit
        is Response.Failure -> signInResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showErrorMessage(e.message)
            }
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
        withStyle(SpanStyle(
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )) {
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
//                    colorFilter = ColorFilter.tint(Color.White)
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
//                        println("Clicked on ${span.item}")
                        navController.navigate(Screen.SignUpScreen.route)
                    }
            })
        }
    }
}


@Composable
fun LoginCard(
    onLogin: (String, String) -> Unit,
) {
    val emailState = remember { mutableStateOf(TextFieldValue("namnpse@gmail.com")) }
    val passState = remember { mutableStateOf(TextFieldValue("")) }
    val textFieldColors: TextFieldColors = if(isSystemInDarkTheme())
        DarkThemTextFieldColors()
    else TextFieldDefaults.outlinedTextFieldColors()
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.padding(vertical = 32.dp))
            Button(
                onClick = {
                    onLogin(emailState.value.text, passState.value.text)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = modifier,
                contentPadding = PaddingValues(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple500)
            ) {
                Text(text = stringResource(R.string.log_in), style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp))
            }
        }
    }
}