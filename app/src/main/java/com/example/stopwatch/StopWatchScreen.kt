package com.example.stopwatch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stopwatch.ui.theme.SecondaryDark
import com.example.stopwatch.ui.theme.StopWatchTheme

@Composable
fun StopWatchScreen(
    stopWatchViewModel: StopWatchViewModel
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(24.dp)
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.Center)
                .advancedShadow(
                    color = Color.White,
                    shadowBlurRadius = 16.dp,
                    offsetY = (-2).dp
                )
        ) {
            Column {

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally)
                        .padding(24.dp)
                ) {
                    Text(
                        text = if(stopWatchViewModel.minutes.value<10){
                            "0${stopWatchViewModel.minutes.value}"
                        }
                        else{
                            stopWatchViewModel.minutes.value.toString()
                        },
                        style = TextStyle(
                            fontSize = 48.sp,
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = ":",
                        style = TextStyle(
                            fontSize = 48.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = if(stopWatchViewModel.seconds.value<10){
                            "0${stopWatchViewModel.seconds.value}"
                        }
                        else{
                            stopWatchViewModel.seconds.value.toString()
                        },
                        style = TextStyle(
                            fontSize = 48.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = ".",
                        style = TextStyle(
                            fontSize = 48.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                    )
                    Text(
                        text = if(stopWatchViewModel.milliseconds.value<10){
                            "0${stopWatchViewModel.milliseconds.value}"
                        }
                        else{
                            stopWatchViewModel.milliseconds.value.toString()
                        },
                        style = TextStyle(
                            fontSize = 48.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(4.dp)
                    )
                }

                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                ) {

                    Button(
                        onClick = {stopWatchViewModel.onEvent(StopWatchEvent.onStart)},
                        modifier = Modifier.weight(1F)
                            .padding(4.dp)
                    ) {
                        Text("Start")
                    }

                    Button(
                        onClick = {stopWatchViewModel.onEvent(StopWatchEvent.onStop)},
                        modifier = Modifier.weight(1F)
                            .padding(4.dp)
                    ) {
                        Text("Stop")
                    }

                    Button(
                        onClick = {stopWatchViewModel.onEvent(StopWatchEvent.onReset)},
                        modifier = Modifier.weight(1F)
                            .padding(4.dp)
                    ) {
                        Text("Reset")
                    }

                }

            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun StopWatchScreenPreview() {
    StopWatchTheme {
        StopWatchScreen(StopWatchViewModel())
    }
}


fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 1f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}