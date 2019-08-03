package com.ucsdextandroid2.android2final

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class GamesResultsRecyclerViewAdapter : ListAdapter<Game, GameViewHolder>(listDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val viewHolder = GameViewHolder.inflate(parent)

        return viewHolder
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val listDiffer: DiffUtil.ItemCallback<Game> = object: DiffUtil.ItemCallback<Game>() {

            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.timesStamp == newItem.timesStamp
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }

        }
    }

}

class GameViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

    private val finalScorePercentage: TextView = itemView.findViewById(R.id.final_score_percentage)
    private val token: TextView = itemView.findViewById(R.id.token_used)
    private val timeStamp: TextView = itemView.findViewById(R.id.time_stamp)


    companion object {
        fun inflate(parent: ViewGroup): GameViewHolder = GameViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.game_items_view, parent, false)
        )
    }

    fun bind(game: Game?) {
        if(game != null) {
            finalScorePercentage.text = game.finalScorePercentage.toString()
            token.text = game.token
            timeStamp.text = game.timesStamp.toString()
        }
        else {
            finalScorePercentage.text = ""
            token.text = ""
            timeStamp.text = ""
        }
    }

}