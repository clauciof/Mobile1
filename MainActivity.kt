package com.clauceta.appmobile1

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.futebol.*
import java.util.*

class MainActivity: AppCompatActivity() {
    /*variaveis para contagem de pontos */
    var pontos1 = 0
    var pontos2 = 0
    var time1selecionado = false
    var time2selecionado = false
    var comecou = false

    /*variaveis para o CountDown*/
    lateinit var contadorTimer: CountDownTimer
    var tempoCorrendo = false
    var tempoDejogo: Long = 600000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.futebol)

        botaoPlay.setOnClickListener(){
           tempoCorrendo = true
           comecou = true

            if(tempoCorrendo==true ) {
                contadorTimer = object : CountDownTimer(tempoDejogo, 1000) {
                    override fun onFinish() {
                        tempoCorrendo = false
                    }

                    override fun onTick(millisUntilFinished: Long) {
                        tempoDejogo = millisUntilFinished

                        //uptade Texview
                        var minutos: Long = (tempoDejogo / 1000) / 60
                        var segundos: Long = (tempoDejogo / 1000) % 60
                        var TempoRestanteFormatado: String = String.format(Locale.getDefault(), "%02d:%02d", minutos, segundos)
                        textviewDoTempo.setText(TempoRestanteFormatado)
                    }

                }.start()
            }
        }

        botaoTerminar.setOnClickListener(){
            terminarJogo()
            if(comecou)
                contadorTimer.cancel()

        }

        botaoPause.setOnClickListener(){
            tempoCorrendo = false
            if(comecou)
                contadorTimer.cancel()

        }

        botaoReset.setOnClickListener(){
            pontos1 = 0
            pontos2 = 0
            time1vitorias.setText("Vitorias: 0")
            time2vitorias.setText("Vitorias: 0")
            time1pontos.setText(pontos1.toString())
            time2pontos.setText(pontos2.toString())
            time1selecionado = false
            time2selecionado = false
            tempoCorrendo = false
            tempoDejogo = 600000
            if(comecou)
                contadorTimer.cancel()
            textviewDoTempo.setText("10:00")

        }

        time1pontos.setOnClickListener(){
            time1selecionado = true
            time2selecionado = false
            time1pontos.setBackgroundColor(Color.CYAN)
            time2pontos.setBackgroundColor(Color.rgb(220,220,220))
        }

        time2pontos.setOnClickListener(){
            time2selecionado = true
            time1selecionado = false
            time1pontos.setBackgroundColor(Color.rgb(220,220,220))
            time2pontos.setBackgroundColor(Color.CYAN)
        }

        adicionagol.setOnClickListener(){
            if(time1selecionado){
                time2selecionado = false
                pontos1++
                time1pontos.setText(pontos1.toString())
            }

            if(time2selecionado){
                time1selecionado = false
                pontos2++
                time2pontos.setText(pontos2.toString())
            }
        }
        decrementagol.setOnClickListener(){
            if(time1selecionado){
                time2selecionado = false
                pontos1--
                if(pontos1<0)
                    pontos1 = 0
                time1pontos.setText(pontos1.toString())
            }

            if(time2selecionado){
                time1selecionado = false
                pontos2--
                if(pontos2<0)
                    pontos2 = 0
                time2pontos.setText(pontos2.toString())
            }
        }
    }

    fun terminarJogo(){
        tempoCorrendo = false

        if(pontos1 > pontos2){
            time1vitorias.setText("Vitorias: 1")
        }

        if(pontos1 < pontos2){
            time2vitorias.setText("Vitorias: 1")
        }

        if(pontos1 == pontos2){

        }
    }
}
