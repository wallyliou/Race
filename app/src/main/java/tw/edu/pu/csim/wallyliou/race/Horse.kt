package tw.edu.pu.csim.wallyliou.race

class Horse(n:Int) {
    var HorseX =0
    var HorseY =100+320 * n
    var HorseNo =0

    fun Run(){
        //馬圖片
        HorseNo++
        if(HorseNo>3){
            HorseNo=0
        }

        HorseX += (10..30).random()

    }
}