package es.iessaladillo.pedrojoya.pr05_trivial.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.pr05_trivial.data.entity.Question

class Data : ViewModel() {

    private val _allQuestions: MutableLiveData<List<Question>> = MutableLiveData(questionList())
    private val allQuestions: LiveData<List<Question>>
        get() = _allQuestions

    private val _currentQuestions: MutableLiveData<List<Question>> =
        MutableLiveData(resetQuestions())
    val currentQuestions: LiveData<List<Question>>
        get() = _currentQuestions

    private val _currentQuestionNumber: MutableLiveData<Int> = MutableLiveData(1)
    val currentQuestionNumber: LiveData<Int>
        get() = _currentQuestionNumber

    private var numberOfQuestions = 2

    private fun resetQuestions(): List<Question> {
        return allQuestions.value!!.shuffled()
    }

    fun numQuestions(): Int {
        return numberOfQuestions
    }

    fun reset() {
        _currentQuestions.value = resetQuestions()
        _currentQuestionNumber.value = 1
    }

    fun incrementQuestionNumber() {
        val currentNumber = _currentQuestionNumber.value ?: 0
        _currentQuestionNumber.value = currentNumber + 1
        println(currentQuestionNumber.value!!)
    }

    private fun questionList(): List<Question> {
        return listOf(
            Question(
                "Which of these actors never played The Joker in a Batman movie?",
                "Heath Ledger",
                "Jack Nicholson",
                "Joaquin Phoenix",
                "Jared Leto",
                3
            ),
            Question(
                "What position did Commander Baldomero Espartero take during the First Carlist War?",
                "King of Spain",
                "Viceroy of Navarra",
                "Count of Toledo",
                "All of the above",
                2
            ),
            Question(
                "Who fought Triple H and Thanos's army in 2019?",
                "Batista",
                "Thor",
                "Kratos",
                "Baldomero Espartero",
                1
            ),
            Question(
                "According to Alberto Canosa, future monarch of Spain, where do the giants hide?",
                "On the surface of Mars, on the opposite side of the airport",
                "Inside the Rock of Gibraltar",
                "On Palaven's moon",
                "Inside some rocks in the Cave of Hercules, in Toledo",
                4
            ),
            Question(
                "How many story missions does 'Grand Theft Auto V' have?",
                "53",
                "69",
                "72",
                "80",
                2
            ),
            Question(
                "What did The Dude want all along in 'The Big Lebowski'?",
                "Win the bowling tournament",
                "The briefcase with a million dollars",
                "The briefcase with Walter's undies",
                "His rug",
                4
            ),
            Question(
                "Which of these Disney worlds do not appear in 'Kingdom Hearts II'?",
                "Beast's Castle (Beauty and the Beast)",
                "Port Royal (Pirates of the Caribbean)",
                "Neverland (Peter Pan)",
                "Space Paranoids (Tron)",
                3
            ),
            Question(
                "Which NXT team was the first to have all the men's titles of the brand at the same time?",
                "SAnitY",
                "The Undisputed Era",
                "#DIY",
                "The Revival",
                2
            ),
            Question(
                "How did Palpatine survive in 'Return of the Jedi' according to the new Star Wars Trilogy?",
                "It is revealed that his escape was planned from the very beginning",
                "He showed his unmatched dance skills on the dance floor",
                "He used a phoenix feather",
                "Because of reasons",
                4
            ),
            Question(
                "Which of these dragons is killed by Kratos in 'God of War'?",
                "Fafnir",
                "Hræzlyr",
                "Reginn",
                "Otr",
                2
            )
        )
    }

    fun setNumberQuestions(numQuestions: Int) {
        numberOfQuestions = numQuestions
    }

}
