package com.namnp.heroes.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.namnp.heroes.R
import com.namnp.heroes.ui.theme.*

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(colorResource(id = R.color.colorPrimaryDark))
            .background(colorResource(id = R.color.white))
//            .wrapContentSize(Alignment.Center)
    ) {
        Row {
            Spacer(Modifier.weight(1f))
//            Text("c")
            Icon(
                modifier = Modifier.padding(16.dp),
//                painterResource(id = R.drawable.ic_bolt),
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit profile"
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier
//                    .size(INFO_ICON_SIZE_LARGE)
                    .padding(16.dp)
                    .weight(3f),
                painter = painterResource(id = R.drawable.shield),
                contentDescription = "Avatar",
//                tint = contentColor
            )
            Column(
                modifier = Modifier
                    .weight(7f),
            ) {
                Text(
                    text = "Nam Nguyen",
//                    color = contentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Bryan",
//                    color = contentColor,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fonts,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MEDIUM_PADDING, end = MEDIUM_PADDING)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
//                modifier = Modifier.padding(end = MEDIUM_PADDING),
                modifier = Modifier.size(PROFILE_ICON_SIZE),
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "(+84) 123456789",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING)
//                .padding(bottom = LARGE_PADDING)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
//                modifier = Modifier.padding(end = MEDIUM_PADDING),
                modifier = Modifier.size(PROFILE_ICON_SIZE),
                imageVector = Icons.Default.Email,
                contentDescription = "Email"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "namnpse@gmail.com",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
            )
        }
//        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = colorResource(id = R.color.ink100s), thickness = 1.dp, modifier = Modifier.padding(vertical = MEDIUM_PADDING))
//        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING)
//                .padding(bottom = LARGE_PADDING)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier.size(PROFILE_ICON_SIZE),
//                imageVector = Icons.Default.Email,
                painter = painterResource(id = R.drawable.ic_heart_empty),
                tint = colorResource(id = R.color.colorPrimaryDark),
                contentDescription = "Email"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Your favorites",
//                    color = contentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING)
//                .padding(bottom = LARGE_PADDING)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier.size(PROFILE_ICON_SIZE),
//                imageVector = Icons.Default.Email,
                painter = painterResource(id = R.drawable.ic_heart_empty),
                tint = colorResource(id = R.color.colorPrimaryDark),
                contentDescription = "Email"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Dark Theme",
//                    color = contentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING)
//                .padding(bottom = LARGE_PADDING)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier.size(PROFILE_ICON_SIZE),
//                imageVector = Icons.Default.Email,
                painter = painterResource(id = R.drawable.ic_heart_empty),
                tint = colorResource(id = R.color.colorPrimaryDark),
                contentDescription = "Email"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Settings",
//                    color = contentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts,
            )
        }
//        Text(
//            text = "Profile View",
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier.align(Alignment.CenterHorizontally),
//            textAlign = TextAlign.Center,
//            fontSize = 25.sp
//        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(vertical = MEDIUM_PADDING),
            onClick =  {
                 println("LOGOUT")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                contentColor = Color.White
            )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = MEDIUM_PADDING),
                text = "Log out",
                fontFamily = fonts,
                fontSize = MaterialTheme.typography.h6.fontSize,)
        }
    }
}