package com.codepath.articlesearch

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var profileRecyclerView: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        val person = intent.getSerializableExtra(PERSON_EXTRA) as Person
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val  banner = findViewById<TextView>(R.id.banner)

        profileRecyclerView = findViewById(R.id.profile_rv)
        profileRecyclerView.layoutManager = LinearLayoutManager(this)
        val profileAdapter = ProfileAdapter(this,person.known_for)
        profileRecyclerView.adapter = profileAdapter

        banner.text = "${person.name} seen in:"
    }
}