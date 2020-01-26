@file:Suppress("DEPRECATION")

package es.iessaladillo.pedrojoya.pr05_trivial.ui.game_over

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
import es.iessaladillo.pedrojoya.pr05_trivial.ui.title.TitleFragment
import kotlinx.android.synthetic.main.fragment_game_over.*

class GameOverFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_game_over, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        data = ViewModelProviders.of(requireActivity()).get(Data::class.java)
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.game_over_title)
        }
        setupViews()
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupViews() {
        btnTryAgain.setOnClickListener {
            data.reset()
            myContext!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, TitleFragment())
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        super.onCreate(savedInstanceState)
    }


}