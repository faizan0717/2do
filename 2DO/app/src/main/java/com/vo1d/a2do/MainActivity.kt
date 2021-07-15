package com.vo1d.a2do

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onlogin(view: View){
        try {
            val a=findViewById<EditText>(R.id.login_uname)
            val b=findViewById<EditText>(R.id.login_upass)
            val uname=a.text
            val pass=b.text
            val database=openOrCreateDatabase("todo", MODE_PRIVATE,null)
            val result=database.rawQuery("SELECT * FROM user WHERE username='$uname' and password='$pass'",null)
        if(result.count>0){
            val i=Intent(this,home::class.java)
            i.putExtra("uname",a.text.toString())
            startActivity(i)
            Toast.makeText(this,"Login Success",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Username/Password Invalid",Toast.LENGTH_LONG).show()
        }
        }
        catch (e:Exception)
        {
            Toast.makeText(this,"Database not found",Toast.LENGTH_LONG).show()
        }
    }
    fun onclickcreate(view: View){
        val i=Intent(this,signup::class.java)
        startActivity(i)
    }
}