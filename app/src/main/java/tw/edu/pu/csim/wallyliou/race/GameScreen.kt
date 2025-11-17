package tw.edu.pu.csim.wallyliou.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


@Composable

fun GameScreen(message: String,gameViewModel: GameViewModel) {

    //val imageBitmap = ImageBitmap.imageResource(R.drawable.horse0)
    val imageBitmaps = listOf(
        ImageBitmap.imageResource(R.drawable.horse0),
        ImageBitmap.imageResource(R.drawable.horse1),
        ImageBitmap.imageResource(R.drawable.horse2),
        ImageBitmap.imageResource(R.drawable.horse3)
    )
    val score2=gameViewModel.score


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
    ){

        Text(
            text="第${score2}馬獲勝",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp)
        )
        Canvas (modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume() // 告訴系統已經處理了這個事件
                    gameViewModel.MoveCircle( dragAmount.x, dragAmount.y)
                }
            }
        ) {
// 繪製圓形
            drawCircle(
                color = Color.Red,
                radius = 100f,
                center = Offset(gameViewModel.circleX, gameViewModel.circleY)
            )
            for(i in 0..2){
                drawImage(
                    image = imageBitmaps[gameViewModel.horses[i].HorseNo],
                    dstOffset = IntOffset(gameViewModel.horses[i].HorseX, gameViewModel.horses[i].HorseY),
                    dstSize = IntSize(300, 300)
                )
            }


        }

        Text(text = message+gameViewModel.screenWidthPx.toString()+
                "*"+gameViewModel.screenHeightPx.toString())

        Button(onClick = {gameViewModel.gameRunning=true
        gameViewModel.StartGame()}
                ,modifier = Modifier
                .align(Alignment.BottomCenter)
            .padding(top = 32.dp)
        ) {

            Text(text = "遊戲開始")
        }


        var user by remember { mutableStateOf("") }
        TextField(
            value = user,
            onValueChange = { user = it },
            placeholder = { Text("預測獲勝的馬:") },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        )
        Text("您預測的馬:$user",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp))
        Spacer(modifier = Modifier.size(10.dp))


    }

}