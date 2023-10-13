package com.app.calc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.lang.NullPointerException


class MainActivity : AppCompatActivity() {

    private var inp : TextView? = null
    var isDotClicked:Boolean = false
    var n1 :String? = null
    var n2:String? = null
    var op:String?=null
    var isOpClicked:Boolean = false
    var isCal:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor=ContextCompat.getColor(this,R.color.darktoolbar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inp = findViewById(R.id.top)
    }

    fun onDigit(view: View){
        isCal=false
        if(inp?.text.toString() !="Infinity"){
            inp?.append((view as Button).text)
        }

    }
    fun onClr(view:View){
        inp?.text="0"
        isDotClicked=false
        isOpClicked=false
        isCal = false
        n1="0"
        n2="0"
        op=null
    }
    fun onDot(view:View){
        if(!isDotClicked){
            inp?.append((view as Button).text)
            isDotClicked=true
        }
    }
    fun onOp(view: View){
        if(!isOpClicked && inp?.text?.isNotEmpty() == true){
            isDotClicked=false
            isOpClicked=true
            isCal = true
            n1 = inp?.text.toString()
            op = (view as Button).text.toString()
            inp?.append(op)
        }

    }
    fun onEql(view: View) {
        isDotClicked = true
        isOpClicked = false

        n2 = inp?.text.toString()
        if(!isCal){
            isCal=true
            if (op != null) {
                val indexOp = n2!!.lastIndexOf("$op")
                n2 = n2!!.substring(indexOp + 1)
                val num1 = n1?.toDouble()
                val num2 = n2?.toDouble()
                val ans = calAns(num1, num2, op)
                if (ans != null) {
                    if (ans.isFinite() ) {
                        inp?.text = ans.toString()
                    } else {
                        inp?.text = "Infinity"
                    }
                } else {
                    inp?.text = "0"
                }
            } else {
                inp?.text = "0"
            }
        }

    }

    fun calAns(number1:Double?,number2:Double?,optr:String?):Double?{
        return when(optr)
        {
            "+" -> number1!! + number2!!
            "-" -> number1!! - number2!!
            "*" -> number1!! * number2!!
            "/" -> number1!! / number2!!
            else -> throw IllegalArgumentException("$op")
        }
    }
}