package es.iessaladillo.pedrojoya.pr05_trivial.data.entity

data class Question(val question: String, val answer1: String, val answer2: String, val answer3: String, val answer4: String, val correctNumber: Int){


    fun answer(numAnswer: Int): Boolean {
        return numAnswer == correctNumber
    }
}

