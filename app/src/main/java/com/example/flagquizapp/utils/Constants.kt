package com.example.flagquizapp.utils

import com.example.flagquizapp.R
import com.example.flagquizapp.model.Question

object Constants {
    val USER_NAME: String = "user_name"
    val TOTAL_QUESTIONS: String = "total_questions"
    val SCORE: String = "score"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val questionOne = Question(
            1,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Australia", "Armenia", "Austria",
            1,
        )
        questionsList.add(questionOne)

        // 2
        val questionTwo = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Angola", "Austria", "Australia", "Armenia",
            3
        )
        questionsList.add(questionTwo)

        // 3
        val questionThree = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Belarus", "Belize", "Brunei", "Brazil",
            4
        )
        questionsList.add(questionThree)

        // 4
        val questionFour = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Bahamas", "Belgium", "Barbados", "Belize",
            2
        )
        questionsList.add(questionFour)

        // 5
        val questionFive = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Gabon", "France", "Fiji", "Finland",
            3
        )
        questionsList.add(questionFive)

        // 6
        val questionSix = Question(
            6,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Germany", "Georgia", "Greece", "none of these",
            1
        )
        questionsList.add(questionSix)

        // 7
        val questionSeven = Question(
            7,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Dominica", "Egypt", "Denmark", "Ethiopia",
            3
        )
        questionsList.add(questionSeven)

        // 8
        val questionEight = Question(
            8,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Ireland", "Iran", "Hungary", "India",
            4
        )
        questionsList.add(questionEight)

        // 9
        val questionNine = Question(
            9,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Australia", "New Zealand", "Tuvalu", "United States of America",
            2
        )
        questionsList.add(questionNine)

        // 10
        val questionTen = Question(
            10,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Kuwait", "Jordan", "Sudan", "Palestine",
            1
        )
        questionsList.add(questionTen)

        return questionsList
    }
}