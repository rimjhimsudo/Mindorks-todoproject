package com.example.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.todoapp.R
import com.example.todoapp.adapter.BlogAdapter
import com.example.todoapp.model.JsonResponse
import kotlinx.android.synthetic.main.activity_blog.*
import org.json.JSONArray

class BlogActivity : AppCompatActivity() {
    lateinit var recyclervwblogs : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindview()
        getBlogs()

    }
    /*https://jiafei427.wordpress.com/2016/04/28/connect-to-server-with-self-signed-certificate-in-android-retrofit-or-okhttp/
    com.androidnetworking.error.ANError: javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
    https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted*/

    private fun getBlogs() {
        AndroidNetworking.get("http://www.mocky.io/v2/5926ce9d11000096006ccb30")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(JsonResponse::class.java,object :ParsedRequestListener<JsonResponse>{
                    override fun onResponse(response: JsonResponse?) {
                        setupreculervw(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("ERRORJSON",""+anError?.localizedMessage)
                    }

                })
    }

    private fun bindview() {
        //recylervwblog=findViewById(R.id.recylervwblog)
        recyclervwblogs=findViewById(R.id.recylervwblog)
    }
    private fun setupreculervw(response :JsonResponse?){
        val blogadapter=BlogAdapter(response!!.data)
        val linearLayoutManager=LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        recyclervwblogs.layoutManager=linearLayoutManager
        recyclervwblogs.adapter=blogadapter

    }

}
