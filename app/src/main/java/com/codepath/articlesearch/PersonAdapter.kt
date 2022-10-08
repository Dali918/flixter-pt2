package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

const val PERSON_EXTRA = "PERSON_EXTRA"
private const val TAG = "PersonAdapter"

class PeopleAdapter(private val context: Context, private val people: List<Person>) :
    RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.person_preview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val person = people[position]
        holder.bind(person)
    }

    override fun getItemCount() = people.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
         {

        private val personImageView = itemView.findViewById<ImageView>(R.id.personImage)
        private val nameView = itemView.findViewById<TextView>(R.id.personName)
        private val departmentView = itemView.findViewById<TextView>(R.id.personDepartment)
//        private val personProfile1 = itemView.findViewById<View>(R.id.personProfile1)




        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(person: Person) {
            nameView.text = person.name
            departmentView.text = person.known_for_department
            val radius = 50 // corner radius, higher value = more rounded
            val margin = 10; // crop margin, set to 0 for corners with no crop

            Glide.with(context)
                .load(person.mediaImageUrl)
                .centerCrop()
                .transform(RoundedCorners(radius))
                .into(personImageView)
        }

        override fun onClick(v: View?) {
            // TODO: Get selected article
            val person = people[absoluteAdapterPosition]


            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PERSON_EXTRA, person)
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, person, "profile")
            context.startActivity(intent)
        }
    }
}