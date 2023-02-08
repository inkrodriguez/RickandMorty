package com.inkrodriguez.rickandmortyapi

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inkrodriguez.rickandmortyapi.api.myData

class Adapter(private val characters: List<com.inkrodriguez.rickandmortyapi.api.Result>): RecyclerView.Adapter<Adapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    //SERÁ O LAYOUT DO ITEM, RECUPERA OS DADOS DO ITEMVIEW.
    class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(data: com.inkrodriguez.rickandmortyapi.api.Result){
            with(itemView){
               val nome = findViewById<TextView>(R.id.tvNome)
               val imagem = findViewById<ImageView>(R.id.image)
               val status = findViewById<TextView>(R.id.tvStatus)
               val cardStatus = findViewById<CardView>(R.id.cardStatus)
               val specie = findViewById<TextView>(R.id.tvSpecie)
               val location = findViewById<TextView>(R.id.tvLocation)
               val seen = findViewById<TextView>(R.id.tvSeen)

                nome.text = data.name
                status.text = data.status.toUpperCase()
                specie.text = data.species
                seen.text = data.origin.name

                when (data.status) {
                    "Alive" -> {
                        cardStatus.setCardBackgroundColor(Color.GREEN)
                    }
                    "Dead" -> {
                        cardStatus.setCardBackgroundColor(Color.RED)
                    }
                    "unknow" -> {
                        cardStatus.setCardBackgroundColor(Color.GRAY)
                    }
                }

                location.text = data.location.name
                Glide.with(this).load(data.image).into(imagem)
            }
        }
    }
}