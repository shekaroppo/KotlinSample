package com.shekar.kotlinsample.tuts

import android.app.Notification
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shekar.kotlinsample.R

class TestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_test)
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    try {
    } catch (e: Throwable) {
    Log.e("TAG", e.message ?: "Error message")
    }
    val clickMeButton1 = findViewById<Button>(R.id.button1)
    val clickMeButton2 = findViewById<Button>(R.id.button2)
    clickMeButton1.showToastOnClick("1")
    clickMeButton2.showToastOnClick("2")
//    button3.showToastOnClick("3")
//    button4.showToastOnClick("4")
    val n = notification(this) {
      setContentTitle("Hi")
      setSubText("Hello")
    }
  }

  //Extension Function Expression
  inline fun notification(context: Context, func: Notification.Builder.() -> Unit): Notification {
    val builder = Notification.Builder(context)
    builder.func()
    return builder.build()
  }

  //Extension Function
  fun Button.showToastOnClick(s: String) {
    this.setOnClickListener {
      android.widget.Toast.makeText(this@TestActivity, "Button$s clicked", android.widget.Toast.LENGTH_LONG).show()
    }
  }
}
