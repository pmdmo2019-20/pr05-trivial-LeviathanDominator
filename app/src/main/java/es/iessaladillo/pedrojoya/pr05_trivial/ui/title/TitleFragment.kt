package es.iessaladillo.pedrojoya.pr05_trivial.ui.title

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.preference.SeekBarPreference
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.data.Data
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game.GameFragment
import kotlinx.android.synthetic.main.fragment_title.*


class TitleFragment : Fragment() {

    private lateinit var data: Data
    private var myContext: FragmentActivity? = null

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    @Suppress("DEPRECATION")
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

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data = ViewModelProviders.of(requireActivity()).get(Data::class.java)
        setupViews(requireView())
    }

    private fun setupViews(requireView: View) {
        data.reset()
        data.setNumberQuestions(settings.getInt(getString(R.string.seekBarPreference_key), 2))
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
