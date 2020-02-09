package com.example.postservice1

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.properties.Delegates

class AsyncHttp : AsyncTask<String, Int, Boolean> {

    var urlConnection : HttpURLConnection by Delegates.notNull<HttpURLConnection>()
    var flg = false

    var name: String = ""
    var value: Double = 0.0

    constructor(_name: String, _value: Double) {
        name = _name
        value = _value
    }
    override fun doInBackground(vararg contents: String?): Boolean { //バックグラウンド処理

        var urlinput: String = "http://10.206.0.18/upload/post.php"
        try { //例外処理

            var url: URL = URL(urlinput)
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "POST"
            urlConnection.doOutput = true

            var postDataSample: String = "name=" + name + "&text=" + value
            var out: OutputStream = urlConnection.outputStream
            out.write(postDataSample.toByteArray())
            out.flush()
            out.close()
            Log.d("test", postDataSample)
            urlConnection.inputStream
            flg = true

        }catch (e: MalformedURLException){
            e.printStackTrace()

        }catch (e: IOException){
            e.printStackTrace()
        }

        return flg
    }

}