package com.productivity.nudge.ui.composable.general

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    checkedTrackColor: Color = Color.Black,
    uncheckedTrackColor: Color = Color.LightGray,
    thumbColor: Color = Color.White,
    width: androidx.compose.ui.unit.Dp = 51.dp,
    height: androidx.compose.ui.unit.Dp = 31.dp
) {
    val thumbOffset by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        label = "thumbOffset"
    )

    val interactionSource = remember { MutableInteractionSource() }

    // Calculate proportional values based on height
    val cornerRadius = height / 2
    val padding = height * 0.065f
    val thumbSize = height - (padding * 2)
    val thumbTravel = width - height

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(if (checked) checkedTrackColor else uncheckedTrackColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onCheckedChange?.invoke(!checked)
            }
            .padding(padding),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset(x = (thumbTravel * thumbOffset))
                .clip(CircleShape)
                .background(thumbColor)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CustomSwitchPreview() {
    var checked by rememberSaveable { mutableStateOf(true) }
    CustomSwitch(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}
