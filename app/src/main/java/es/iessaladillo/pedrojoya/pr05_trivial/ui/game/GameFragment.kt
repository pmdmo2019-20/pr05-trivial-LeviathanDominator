@file:Suppress("DEPRECATION")

package es.iessaladillo.pedrojoya.pr05_trivial.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import es.iessaladillo.pedrojoya.pr05_trivial.R
import es.iessaladillo.pedrojoya.pr05_trivial.data.Data
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game_over.GameOverFragment
import es.iessaladillo.pedrojoya.pr05_trivial.ui.game_won.GameWonFragment
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {

    private lateinit var data: Data
    private lateinit var fragContext: FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragContext = container!!.context as FragmentActivity
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        data = ViewModelProviders.of(requireActivity()).get(Data::class.java)
        setupViews()
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = getString(
                R.string.game_question_title,
                data.currentQuestionNumber.value,
                data.numQuestions()
            )
        }
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
        val question = data.currentQuestions.value!![data.currentQuestionNumber.value!! - 1]
        lblQuestion.text = question.question
        rbAnswer1.text = question.answer1
        rbAnswer2.text = question.answer2
        rbAnswer3.text = question.answer3
        rbAnswer4.text = question.answer4
        btnSubmit.setOnClickListener {
            data.incrementQuestionNumber()
            submit(question.correctNumber)
        }
    }

    // Checks if the answer is correct. It nothing is selected it shows a message to the user.
    private fun submit(correctNumber: Int) {
        val num = answerSelected()
        println(num)
        if (num == 0) {
            Toast.makeText(fragContext, "Please select an answer", Toast.LENGTH_LONG).show()
            return
        }
        if (num == correctNumber) {
            advance()
        } else {
            gameOver()
        }
    }

    // When failing a question, it goes to the game over screen.
    private fun gameOver() {
        val gameOverFragment = GameOverFragment()
        fragContext
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, gameOverFragment)
            .commit()
    }

    // If there are more questions, it advances to the next one. If not, it goes to the congratulations screen.
    private fun advance() {
        if (data.numQuestions() == data.currentQuestionNumber.value!! - 1) {
            fragContext.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, GameWonFragment())
                .commit()
        } else {
            fragContext.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, GameFragment())
                .commit()
        }
    }

    // Selects answer number from radiobutton group
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
