package com.vo1d.a2do

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.marginTop

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
            try {
                val database = openOrCreateDatabase("todo", MODE_PRIVATE, null)
                database.execSQL("CREATE TABLE IF NOT EXISTS userdata(id INT,username VARCHAR,task VARCHAR);");
                val uname = intent.getStringExtra("uname")
                Toast.makeText(this, uname, Toast.LENGTH_LONG).show()
                val result =
                    database.rawQuery("SELECT * FROM userdata WHERE username='$uname'", null)
                if (result.moveToFirst()) {
                    do {
                        val parent = findViewById<LinearLayout>(R.id.parent)
                        val child = LinearLayout(this)
                        val text = TextView(this)
                        val button = Button(this)
                        button.setText("DEL")
                        button.id=result.getString(0).toInt()
                        child.setPadding(0,10,0,0)
                        button.setBackgroundColor(Color.parseColor("#FFFF0000"))
                        button.setOnClickListener {
                            try{
                                val database = openOrCreateDatabase("todo", MODE_PRIVATE, null)
                                val id=button.getId().toString()
                                database.execSQL("DELETE FROM userdata WHERE id='$id'")
                                val i=Intent(this,home::class.java)
                                i.putExtra("uname",uname)
                                finish();
                                startActivity(i)
                            }catch (e:Exception){
                                Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
                            }
                        }
                        button.width=102
                        button.height=56
                        text.minWidth=306*3
                        text.maxWidth=306*3
                        text.minHeight=120
                        text.setBackgroundColor(Color.parseColor("#59FCFDFB"))
                        text.setTextColor(Color.parseColor("#000000"))
                        text.text = result.getString(2)
                        child.addView(text)
                        child.addView(button)
                        parent.addView(child)
                    } while (result.moveToNext())
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Failed!", Toast.LENGTH_LONG).show()
            }
    }
    fun addData(view: View){
        val i=Intent(this,adddata::class.java)
        val uname=intent.getStringExtra("uname")
        i.putExtra("uname",uname)
        finish();
        startActivity(i)
    }
}