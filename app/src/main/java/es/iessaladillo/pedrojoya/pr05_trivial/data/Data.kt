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

    private fun resetQuestions(): List<Question> {
        return allQuestions.value!!.shuffled()
    }

    fun numQuestions(): Int {
        return currentQuestions.value!!.size
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
            Question("Which of these actors never played The Joker in a Batman movie?", "Heath Ledger", "Jack Nicholson", "Joaquin Phoenix", "Jared Leto", 3),
            Question("What position did Commander Baldomero Espartero take during the First Carlist War?", "King of Spain", "Viceroy of Navarra", "Count of Toledo", "All of the above", 2),
            Question("Who fought Triple H and Thanos's army in 2019?", "Batista", "Thor", "Kratos", "Baldomero Espartero", 1),
            Question("According to Alberto Canosa, future monarch of Spain, where do the giants hide?", "On the surface of Mars, on the opposite side of the airport", "Inside the Rock of Gibraltar", "On Palaven's moon", "Inside some rocks in the Cave of Hercules, in Toledo", 4),
            Question("How many story missions does 'Grand Theft Auto V' have?", "53", "69", "72", "80", 2),
            Question("What did The Dude want all along in 'The Big Lebowski'?", "Win the bowling tournament", "The briefcase with a million dollars", "The briefcase with Walter's undies", "His rug", 4),
            Question("Which of these Disney worlds do not appear in 'Kingdom Hearts II'?", "Beast's Castle (Beauty and the Beast)", "Port Royal (Pirates of the Caribbean)", "Neverland (Peter Pan)", "Space Paranoids (Tron)", 3)
        )
    }

}
