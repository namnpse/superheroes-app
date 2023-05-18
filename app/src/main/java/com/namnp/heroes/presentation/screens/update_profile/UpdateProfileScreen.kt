package com.namnp.heroes.presentation.screens.update_profile

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.namnp.heroes.R
import com.namnp.heroes.domain.model.Response
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.ui.theme.*
import com.namnp.heroes.util.DarkThemTextFieldColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

//@Preview(showBackground = true)
//@Composable
//fun UpdateProfileScreenPreview() {
//    val navController = rememberNavController()
//    UpdateProfileScreen(
//        navController = navController,
//    )
//}

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

    val phoneState = remember { mutableStateOf(TextFieldValue("0123456789")) }
    val nicknameState = remember { mutableStateOf(TextFieldValue("Nam Jr")) }
    val bioState = remember { mutableStateOf(TextFieldValue("Mobile Developer")) }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    println("selectedImageUri ${selectedImageUri?.path}")

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
//            selectedImageUri = uri
            viewModel.uploadAvatarImage(uri)
        }
    )

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
                val userAvatar = viewModel.avatarUrl.collectAsState().value
                println("userAvatar $userAvatar")
                val modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .error(R.drawable.avatar_placeholder)
//                    .data("${Constants.BASE_URL}/images/urashiki.jpg")
                        .data(userAvatar)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(160.dp)
                        .padding(
                            start = MEDIUM_PADDING,
                            end = MEDIUM_PADDING,
                            bottom = MEDIUM_PADDING
                        )
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(160.dp))
                        .clickable {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
//                    .background(Purple500),
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
                val updateUserInfoResponse = viewModel.updateUserInfoResponse
                if (updateUserInfoResponse is Response.Failure) {
                    updateUserInfoResponse.apply {
                        LaunchedEffect(error.message) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = error.message ?: "",
                            )
                        }
                    }
                }
                if (updateUserInfoResponse is Response.Success && updateUserInfoResponse.data == true) {
                    LaunchedEffect(updateUserInfoResponse.data) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Update successfully",
                        )
//                        delay(1.seconds)
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("update", true)
                        // Do something with the result.
                        navController.popBackStack()
                    }
                }
                if (updateUserInfoResponse is Response.Loading) CircularProgressIndicator()
                else Button(
                    onClick = {
                        keyboard?.hide()
                        viewModel.saveUserToFirestore(
                            nicknameState.value.text,
                            phoneState.value.text,
                            bioState.value.text
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
                Spacer(modifier = Modifier.padding(vertical = 0.dp))
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