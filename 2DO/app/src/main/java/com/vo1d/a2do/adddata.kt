package com.vo1d.a2do

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class adddata : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adddata)
        val uname = intent.getStringExtra("uname")
    }
    fun adddataindb(view: View){
        try {
            val database=openOrCreateDatabase("todo", MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS userdata(username VARCHAR,task VARCHAR);");
            val a=findViewById<EditText>(R.id.task)
            var uname=intent.getStringExtra("uname").toString()
            val task=a.text.toString()
            var id=0
            val result =
                database.rawQuery("SELECT * FROM userdata", null)
            if (result.moveToLast()) {
                id=result.getString(0).toInt()+1
            }
            if(task.isEmpty()){
                Toast.makeText(this,"Enter Task!", Toast.LENGTH_LONG).show()
            }else{
                    database.execSQL("INSERT INTO userdata VALUES('$id','$uname','$task');");
                    Toast.makeText(this,"Task Added Successfully", Toast.LENGTH_LONG).show()
                    a.setText("")
                val i=Intent(this,home::class.java)
                i.putExtra("uname",uname)
                startActivity(i)
                finish();
            }
        }
        catch (e:Exception)
        {
            Toast.makeText(this,"Failed!", Toast.LENGTH_LONG).show()
        }
    }

}