package com.example.stopwatch_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity(), View.OnClickListener{

    //메인 뷰에 생성된 뷰들..(텍스트,버튼뷰 등) 초기화를 위한 변수 선언
    private lateinit var btn_start : Button //선언 후 non-null로 (late)초기화 할 것임
    private lateinit var btn_refresh : Button
    private lateinit var tv_minute : TextView
    private lateinit var tv_second : TextView
    private lateinit var tv_millisecond : TextView

    private var isRunning = false

    private var timer : Timer? = null

    private var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //변수 초기화
        btn_start = findViewById(R.id.btn_start)
        btn_refresh = findViewById(R.id.btn_refresh)
        tv_minute = findViewById(R.id.tv_minute)
        tv_second = findViewById(R.id.tv_second)
        tv_millisecond = findViewById(R.id.tv_millisecond)

        //버튼에 리스너 설정
        btn_start.setOnClickListener(this) // this means 방금 구현한 "OnClickListener"를 버튼과 연동
        btn_refresh.setOnClickListener(this)


    }

    //onclicklistener 필수로 구현되어야 하는 메소드
    override fun onClick(view: View?) {

        //화면에서 눌린 버튼의 id가 무엇인가?
        when(view?.id){
            R.id.btn_start -> { //start버튼이 눌렸을 때
              if(isRunning){//작동중이면
                  pause()//멈춘다
              }else{//작동중이 아니면
                  start()//재시작한다
              }
            }
            R.id.btn_refresh -> { // 초기화 버튼이 눌렸을 때
                refresh()//초기화한다
            }
        }
    }

    //시작
    fun start(){
        btn_start.text = getString(R.string.btn_pause)
        btn_start.setBackgroundColor(getColor(R.color.btn_pause))
        isRunning = true

        //kotlin 제공 timer함수 : 일정한 주기로 반복되는 동작을 수행할 경우
        //항상 백그라운드 스레드에서 실행된다 -> 백그라운드 스레드에서는 뷰를 변경할 수 없음!
        timer = timer(period = 10) {
            //1000ms = 1s
            //즉, 0.01초마다 time++
            time++

            val milli_second = time % 100
            val second = (time % 6000) / 100
            val minute = time / 6000


            //main thread에서 처리하도록
            runOnUiThread {
                tv_millisecond.text = if(milli_second < 10) ".0${milli_second}" else ".${milli_second}"
                tv_second.text = if(second< 10) ":0${second}" else ":${second}"
                tv_minute.text = "${minute}"
            }

        }
    }

    //잠시멈춤
    fun pause(){

        btn_start.text = getString(R.string.btn_start)
        btn_start.setBackgroundColor(getColor(R.color.btn_start))

        isRunning = false
        timer?.cancel()
    }

    //초기화
    fun refresh(){

        timer?.cancel()
        btn_start.text = getString(R.string.btn_start)
        btn_start.setBackgroundColor(getColor(R.color.btn_start))
        isRunning = false

        time = 0
        tv_millisecond.text = ",00"
        tv_second.text = ":00"
        tv_minute.text = "00"
    }


}