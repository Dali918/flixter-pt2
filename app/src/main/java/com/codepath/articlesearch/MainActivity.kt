package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.BuildConfig.API_KEY
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.BuildConfig
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException


fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val PERSON_API_KEY  = API_KEY
private const val PERSON_SEARCH_URL = "https://api.themoviedb.org/3/person/popular?api_key=${PERSON_API_KEY}&language=en-US&page=1"

class MainActivity : AppCompatActivity() {
    private lateinit var peopleRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val people = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        peopleRecyclerView = findViewById(R.id.people_rv)
        // TODO: Set up ArticleAdapter with articles
        //create person adapter
        val peopleAdapter = PeopleAdapter(this, people)
        peopleRecyclerView .adapter = peopleAdapter
        peopleRecyclerView .layoutManager = GridLayoutManager(this,2).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            peopleRecyclerView .addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client.get(PERSON_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch popular people: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully retrieved popular people: $json")
                try {
                    // TODO: Create the parsedJSON
                    val parsedJson = createJson().decodeFromString(
                        SearchPopularPeopleResponse.serializer(),
                        json.jsonObject.toString())
//
                    // TODO: Do something with the returned json (contains article information)
                    parsedJson.people?.let { list ->
                        people.addAll(list)
                    }

                    // TODO: Save the articles and reload the screen
                    peopleAdapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }
}