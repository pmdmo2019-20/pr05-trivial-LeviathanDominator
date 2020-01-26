package es.iessaladillo.pedrojoya.pr05_trivial.ui.game

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.data.Trivia
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game_over.GameOverFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game_won.GameWonFragment
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    private lateinit var fragContext: FragmentActivity

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragContext = container!!.context as FragmentActivity
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.game_question_title, 1, 2)
        }
        setupViews()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.toolbar)?.let {
            menu.findItem(R.id.toolbar).isEnabled = !it.isChecked
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        super.onCreate(savedInstanceState)
    }

    private fun setupViews() {
        val wa = Trivia()
        wa.question1
        lblQuestion.text = wa.question1.question
        rbAnswer1.text = wa.question1.answer1
        rbAnswer2.text = wa.question1.answer2
        rbAnswer3.text = wa.question1.answer3
        rbAnswer4.text = wa.question1.answer4
        btnSubmit.setOnClickListener{
            submit(wa.question1.correctNumber)
        }
    }

    private fun submit(correctNumber: Int) {
        val num = answerSelected()
        println(num)
        if (num == 0){
            Toast.makeText(fragContext, "Please select an answer", Toast.LENGTH_LONG).show()
            return
        }
        if (num == correctNumber){
            advance()
        } else {
            gameOver()
        }
    }

    private fun gameOver() {
        val gameOverFragment
         = GameOverFragment()
        fragContext
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, gameOverFragment)
            .commit()    }

   private fun advance() {
       fragContext.supportFragmentManager
           .beginTransaction()
           .replace(R.id.frgContainer, GameWonFragment())
           .commit()
    }

    private fun answerSelected(): Int {
        when {
            rbAnswer1.isChecked -> {
                return 1
            }
            rbAnswer2.isChecked -> {
                return 2
            }
            rbAnswer3.isChecked -> {
                return 3
            }
            rbAnswer4.isChecked -> {
                return 4
            }
        }
        return 0
    }

}
