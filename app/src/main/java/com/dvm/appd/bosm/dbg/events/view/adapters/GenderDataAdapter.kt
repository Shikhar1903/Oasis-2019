package com.dvm.appd.bosm.dbg.events.view.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dvm.appd.bosm.dbg.R
import kotlinx.android.synthetic.main.card_sports_horizontal.view.*

class GenderDataAdapter( private val genderDefault: String,private val listener: GenderDataAdapter.OnGenderClicked): RecyclerView.Adapter<GenderDataAdapter.GenderViewHolder>(){

    var gender: List<String> = emptyList()
    var genderSelected=genderDefault
    interface OnGenderClicked{
        fun genderClicked(gender: String)
    }

    class GenderViewHolder(view: View): RecyclerView.ViewHolder(view){

        val editText: TextView = view.editTextGender
        val parent:LinearLayout=view.Parent_horizontal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_sports_horizontal, parent, false)
        return GenderViewHolder(view)
    }

    override fun getItemCount(): Int = gender.size

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        holder.editText.setText(gender[position])
        if (gender[position].equals(genderSelected))
            holder.editText.setBackgroundColor(Color.MAGENTA)
        else
            holder.editText.setBackgroundColor(Color.WHITE)


        Log.d("SportsGender","Gender onBindviewholder genderClicked$${gender[position]}")


        holder.editText.setOnClickListener {
                Log.d("SportsGender","Gender data Apadter genderClicked$${gender[position]}")
                genderSelected=gender[position]
                notifyDataSetChanged()
                listener.genderClicked(gender[position])

        }

    }

}