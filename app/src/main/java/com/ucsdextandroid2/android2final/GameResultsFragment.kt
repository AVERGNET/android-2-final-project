package com.ucsdextandroid2.android2final

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.ucsdextandroid2.android2final.dummy.DummyContent
import com.ucsdextandroid2.android2final.dummy.DummyContent.DummyItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 */
class GameResultsFragment : Fragment() {

    private val adapter : GamesResultsRecyclerViewAdapter by lazy {
        GamesResultsRecyclerViewAdapter()
    }

    private var game: Game? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = GameResultsFragmentArgs.fromBundle(arguments ?: Bundle.EMPTY)

        if(game == null) {
            //create game from args
            game = Game(
                args.score, args.score * 100
            )

            AppDatabase.get(requireContext()).gameDao().insertGame(game!!)
        }

        AppDatabase.get(requireContext()).gameDao()
            .getAllGamesLiveData()
            .observe(this, Observer<List<Game>> { notes ->
                adapter.submitList(notes)
            })

        //TODO display all the past scores

    }
}
