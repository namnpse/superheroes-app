package com.namnp.heroes.presentation.screens.login

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.namnp.heroes.R
import com.namnp.heroes.navigation.Screen

//@Preview(showBackground = true)
@Composable
fun LoginScreen(
    navController: NavHostController,
    authenViewModel: AuthenViewModel = hiltViewModel()
) {

    Box {
        BackgroundCard(navController)
        LoginCard()
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
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = (-30).dp)
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
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
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
fun LoginCard() {
    val emailState = remember { mutableStateOf(TextFieldValue("namnpse@gmail.com")) }
    val passState = remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        color = Color.White, modifier = Modifier
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
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = emailState.value,
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
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                modifier = modifier,
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = stringResource(R.string.log_in))
            }
        }
    }
}