package com.vo1d.a2do

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }
    fun onSignup(view: View){
        try {
            val database=openOrCreateDatabase("todo", MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS user(username VARCHAR,password VARCHAR);");
            val a=findViewById<EditText>(R.id.signin_uname)
            val b=findViewById<EditText>(R.id.signin_upass)
            var uname=a.text.toString()
            val pass=b.text.toString()
            if(uname.isEmpty() || pass.isEmpty()){
                Toast.makeText(this,"Enter Details!",Toast.LENGTH_LONG).show()
            }else{
                if(uname.length>5 && pass.length>8){
                    val result=database.rawQuery("SELECT * FROM user WHERE username='$uname'",null)
                    if(result.count>0){
                        Toast.makeText(this,"Username already exists",Toast.LENGTH_LONG).show()
                    }else{
                        database.execSQL("INSERT INTO user VALUES('$uname','$pass');");
                        val i= Intent(this,MainActivity::class.java)
                        Toast.makeText(this,"Account Created Please Login",Toast.LENGTH_LONG).show()
                        startActivity(i)
                    }
                }else{
                    if(uname.length<5)
                        Toast.makeText(this,"Username Should have at least 6 characters",Toast.LENGTH_LONG).show()
                    if(pass.length<5)
                        Toast.makeText(this,"Password Should have at least 6 characters",Toast.LENGTH_LONG).show()
                }
            }
        }
        catch (e:Exception)
        {
            Toast.makeText(this,"Failed!",Toast.LENGTH_LONG).show()
        }
    }

}