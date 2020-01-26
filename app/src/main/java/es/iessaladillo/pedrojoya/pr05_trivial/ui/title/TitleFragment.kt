package es.iessaladillo.pedrojoya.pr05_trivial.ui.title

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import kotlinx.android.synthetic.main.fragment_title.*


class TitleFragment : Fragment() {

    private var myContext: FragmentActivity? = null

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        super.onAttach(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_title, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews(requireView())
    }

    private fun setupViews(requireView: View) {
        btnPlay.setOnClickListener { play() }
        myContext!!.setActionBar(requireView.findViewById(R.id.toolbar))

        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.android_trivia)
        }
    }

    private fun play() {
        val playFragment = GameFragment()
        myContext!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, playFragment)
            .commit()
    }


}
