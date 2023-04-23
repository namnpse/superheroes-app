package com.namnp.heroes.presentation.components

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.namnp.heroes.R
import com.namnp.heroes.ui.theme.INFO_ICON_SIZE_LARGE

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LikeAnimatedButton(
    modifier: Modifier = Modifier
) {
    val selected = remember { mutableStateOf(false) }
    val isLiked = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 2f else 1f)

    Icon(
        modifier = modifier
            .size(INFO_ICON_SIZE_LARGE)
            .scale(scale.value)
//                .clickable {
//                           isLiked.value = !isLiked.value
//                }
            .pointerInteropFilter {
                if(it.action == MotionEvent.ACTION_DOWN)
                    isLiked.value = !isLiked.value
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selected.value = true
                    }

                    MotionEvent.ACTION_UP  -> {
                        selected.value = false
                    }
                }
                true
            }
        ,
        painter = painterResource(id = R.drawable.ic_heart_empty),
        contentDescription = stringResource(id = R.string.app_logo),
        tint = if(isLiked.value) Color.Red else Color.Gray
    )
}