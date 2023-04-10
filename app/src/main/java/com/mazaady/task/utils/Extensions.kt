package com.mazaady.task.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
lateinit var formattedDate:String

// function to convert data string to get day name
fun getDayName(dateStr: String) :String {
    try {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val targetFormat: DateFormat = SimpleDateFormat("EEEE")

        val date = formatter.parse(dateStr) // this never ends while debugging
        formattedDate = targetFormat.format(date) // 20120821

        Log.e("mDate", date.toString())
    } catch (e: Exception){
        Log.e("mDate",e.toString()) // this never gets called either
    }
    return formattedDate
}
// function to convert data string to get data in another format

fun getDate(dateStr: String) :String {
    try {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
        val targetFormat: DateFormat = SimpleDateFormat("EEEE, d MMM yyyy")

        val date = formatter.parse(dateStr)
        formattedDate = targetFormat.format(date)

        Log.e("mDate", date.toString())
    } catch (e: Exception){
        Log.e("mDate",e.toString())
    }
    return formattedDate
}
// function to convert data string to get time
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}
fun getTime(dateStr: String) :String {
    try {

        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
        val targetFormat: DateFormat = SimpleDateFormat("HH:mm aaa")

        val date = formatter.parse(dateStr)
        formattedDate = targetFormat.format(date)

        Log.e("mDate", date.toString())
    } catch (e: Exception){
        Log.e("mDate",e.toString())
    }
    return formattedDate
}
//extension function to observe search edittext
fun EditText.afterTextChangedFlow() : Flow<Editable?>
{
    return callbackFlow {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun afterTextChanged(p0: Editable?) {
                trySend(p0).isSuccess
            }

        }
        addTextChangedListener(watcher)
        awaitClose{removeTextChangedListener(watcher)}
    }
}

