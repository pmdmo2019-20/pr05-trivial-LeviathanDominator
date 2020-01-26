package es.iessaladillo.pedrojoya.pr05_trivial.ui.game_won

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.data.Data
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import kotlinx.android.synthetic.main.fragment_game_won.*

class GameWonFragment : Fragment() {

    private lateinit var data: Data
    private var myContext: FragmentActivity? = null

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_won, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        data = ViewModelProviders.of(requireActivity()).get(Data::class.java)
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.game_won_title)
        }
        setupViews()
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupViews() {
        btnNext.setOnClickListener {
            data.reset()
            myContext!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, GameFragment())
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        super.onCreate(savedInstanceState)
    }

}