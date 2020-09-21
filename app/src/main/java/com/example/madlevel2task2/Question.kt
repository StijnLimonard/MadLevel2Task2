package com.example.madlevel2task2

data class Question(
    var question : String,
    var answer: Boolean
) {
    companion object{
        val QUESTIONS = arrayOf(
            "dit is waar",
            "dit is waar",
            "dit is niet waar",
            "dit is niet waar",
            "dit is waar"
        )

        val ANSWERS = arrayOf(
            true,
            true,
            false,
            false,
            true
        )
    }
}