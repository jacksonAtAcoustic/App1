package com.example.events

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.CompoundButton.*
import android.widget.SeekBar.*
import android.widget.TextView.*
import android.text.TextWatcher

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface.*

// network avaliability
fun isNetworkAvailable(context: Context): Boolean
{
  var result = false
  (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      result = isCapableNetwork(this,this.activeNetwork)
    } else {
      val networkInfos = this.allNetworks
      for (tempNetworkInfo in networkInfos)
      {
        if(isCapableNetwork(this,tempNetworkInfo))
          result =  true
      }
    }
  }
  return result
}

// is this network good?
fun isCapableNetwork(cm: ConnectivityManager,network: Network?): Boolean{
  cm.getNetworkCapabilities(network)?.also {
    if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
      return true
    } else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
      return true
    }
  }
  return false
}

// handler class, event handling
class Handler : View.OnClickListener, OnCheckedChangeListener, OnSeekBarChangeListener,
  OnEditorActionListener,TextWatcher, DialogInterface.OnClickListener
{
  private var sliderLabel : TextView? = null
  override fun onClick(v: View?)
  {
    //Get the text of the specific button
    var text = (v as Button).getText()
  }

  override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean)
  {
    // direct button access
    var text = buttonView?.getText()
  }

  // do I need this?
  override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean)
  {
    var slider = MainActivity.getInstance().findViewById<TextView>(R.id.freq)
    var text = progress.toString()
    slider.setText(text)
  }

  // do I need this?
  override fun onStartTrackingTouch(seekBar: SeekBar?)
  {
    println("start")
  }

  // do I need this?
  override fun onStopTrackingTouch(seekBar: SeekBar?)
  {
    println("stop")
  }

  //Invoked when pressing the return key
  override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean
  {
    println(v?.getText())
    var im = MainActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    im.hideSoftInputFromWindow(v?.getWindowToken(),0)
    return true
  }

  override fun afterTextChanged(s: Editable?)
  {
    println("after " + s)
  }
  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
  {
    println("before: " + s)
  }
  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
  {
    println("on " + s)
  }

  override fun onClick(dialog: DialogInterface?, which: Int)
  {
    if (which == DialogInterface.BUTTON_NEGATIVE)
    {
      println("negative")
    }
    else if (which == DialogInterface.BUTTON_POSITIVE)
    {
      println("positive")
    }
  }
}

class MainActivity : AppCompatActivity()
{
  companion object
  {
    private var instance : MainActivity? = null

    public fun getInstance() : MainActivity
    {
      // makes sure this instance is not null (NPE)
      return instance!!
    }
  }

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    instance = this

    //Check for Internet
    var res = isNetworkAvailable(this)
    println(res)

    //Instantiate Handler
    var handler = Handler()

    // Keep this for now
    val dialogBuilder = AlertDialog.Builder(this)
    dialogBuilder.setMessage("Not Connected To Internet")
    dialogBuilder.setPositiveButton("OK",handler)
    dialogBuilder.setNegativeButton("Cancel", handler)
    val alert1 = dialogBuilder.create()
    alert1.setTitle("No Internet")
    alert1.show()

    //Register Handler with the Buttons
    var start = findViewById<Button>(R.id.start)
    var pause = findViewById<Button>(R.id.pause)
    var band = findViewById<CompoundButton>(R.id.band)
    var slider = findViewById<SeekBar>(R.id.seekBar)
    var dataEntry = findViewById<EditText>(R.id.dataEntry)
    start.setOnClickListener(handler)
    pause.setOnClickListener(handler)
    band.setOnCheckedChangeListener(handler)
    band.setOnClickListener(handler)
    slider.setOnSeekBarChangeListener(handler)
    dataEntry.setOnEditorActionListener(handler)
    dataEntry.addTextChangedListener(handler)
  }
}