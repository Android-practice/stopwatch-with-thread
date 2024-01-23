package com.example.stopwatch_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener{

    //메인 뷰에 생성된 뷰들..(텍스트,버튼뷰 등) 초기화를 위한 변수 선언
    private lateinit var btn_start : Button //선언 후 non-null로 (late)초기화 할 것임
    private lateinit var btn_refresh : Button
    private lateinit var tv_minute : TextView
    private lateinit var tv_second : TextView
    private lateinit var tv_millisecond : TextView

    private var isRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start = findViewById(R.id.btn_start)
        btn_refresh = findViewById(R.id.btn_refresh)
        tv_minute = findViewById(R.id.tv_minute)
        tv_second = findViewById(R.id.tv_second)
        tv_millisecond = findViewById(R.id.tv_millisecond)
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

    }

    //잠시멈춤
    fun pause(){

    }

    //초기화
    fun refresh(){

    }


}