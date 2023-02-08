package com.inkrodriguez.rickandmortyapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.inkrodriguez.rickandmortyapi.api.myData
import com.inkrodriguez.rickandmortyapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://rickandmortyapi.com/api/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMyData()

    }

    private fun getMyData() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object: Callback<myData> {
                override fun onResponse(call: Call<myData>, response: Response<myData>) {
                    val responseBody = response.body()!!

                    responseBody.results.forEach {
                        var recyclerView = binding.recyclerView
                        var adapter = Adapter(responseBody.results)
                        recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<myData>, t: Throwable) {
                    Toast.makeText(applicationContext, "$t", Toast.LENGTH_LONG).show()
                } })
    }
}
