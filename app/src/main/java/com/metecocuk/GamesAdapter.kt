package com.metecocuk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class GamesAdapter(
    private val gamesList: List<MainActivity.GameItem>,
    private val onGameClick: (MainActivity.GameItem) -> Unit
) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gamesList[position]
        holder.bind(game)
    }

    override fun getItemCount(): Int = gamesList.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.gameCard)
        private val iconView: ImageView = itemView.findViewById(R.id.gameIcon)
        private val titleView: TextView = itemView.findViewById(R.id.gameTitle)
        private val descriptionView: TextView = itemView.findViewById(R.id.gameDescription)

        fun bind(game: MainActivity.GameItem) {
            iconView.setImageResource(game.iconResId)
            titleView.text = game.title
            descriptionView.text = game.description
            
            cardView.setCardBackgroundColor(
                itemView.context.getColor(game.colorResId)
            )
            
            itemView.setOnClickListener {
                onGameClick(game)
            }
        }
    }
}
