package com.example.flagquizapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.flagquizapp.model.Question
import com.example.flagquizapp.model.QuizState
import com.example.flagquizapp.utils.Constants
import com.example.flagquizapp.ResultActivity
import android.graphics.Color as color
import android.graphics.Typeface as typeFace


class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView

    private lateinit var textViewOp1: TextView
    private lateinit var textViewOp2: TextView
    private lateinit var textViewOp3: TextView
    private lateinit var textViewOp4: TextView
    private var currentPosition = 1
    private lateinit var checkButton: Button
    private lateinit var questionList: MutableList<Question>
    private var selectedAnswer = 0
    private var isAnswerChecked = false
    private var score = 0
    private lateinit var currentQuestion: Question
    private var quizState: QuizState = QuizState.ANSWERING
    private var userName: String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        userName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progress_bar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_View)
        flagImage = findViewById(R.id.flag_image_view)
        checkButton = findViewById(R.id.button_check_ans)
        textViewOp1 = findViewById(R.id.text_View_option1)
        textViewOp2 = findViewById(R.id.text_View_option2)
        textViewOp3 = findViewById(R.id.text_View_option3)
        textViewOp4 = findViewById(R.id.text_View_option4)

        textViewOp1.setOnClickListener(this)
        textViewOp2.setOnClickListener(this)
        textViewOp3.setOnClickListener(this)
        textViewOp4.setOnClickListener(this)
        checkButton.setOnClickListener(this)
        questionList = Constants.getQuestions()

        setNewQuestion()
    }

    private fun setNewQuestion() {
        resetOptions()
        quizState = QuizState.ANSWERING
        isAnswerChecked = false
        selectedAnswer = 0
        val question = questionList[currentPosition - 1]
        flagImage.setImageResource(question.image)
        progressBar.progress = currentPosition
        textViewProgress.text = "$currentPosition/${questionList.size}"
        textViewQuestion.text = question.question
        textViewOp1.text = question.optionOne
        textViewOp2.text = question.optionTwo
        textViewOp3.text = question.optionThree
        textViewOp4.text = question.optionFour
        checkButton.text = "CHECK"
    }

    private fun resetOptions() {
        textViewOp1.background = ContextCompat.getDrawable(this, R.drawable.default_option_bg)
        textViewOp2.background = ContextCompat.getDrawable(this, R.drawable.default_option_bg)
        textViewOp3.background = ContextCompat.getDrawable(this, R.drawable.default_option_bg)
        textViewOp4.background = ContextCompat.getDrawable(this, R.drawable.default_option_bg)
    }


    private fun checkAnswer() {
        if (quizState == QuizState.COMPLETED) {
            endQuiz()
        }

        if (quizState == QuizState.NEXT_QUESTION) {
            nextQuestion()
        }

        if (selectedAnswer == 0) {
            return
        }

        isAnswerChecked = true
        val question = questionList[currentPosition - 1]
        if (selectedAnswer == question.correctAnswer) {
            score++
            highlightAnswer(selectedAnswer, R.drawable.correct_option_bg)
        } else {
            highlightAnswer(selectedAnswer, R.drawable.wrong_option_bg)
            highlightAnswer(question.correctAnswer, R.drawable.correct_option_bg)
        }

        Log.d("QuestionActivity", "Score: $score")

        if (currentPosition == questionList.size) {
            checkButton.text = "FINISH"
            quizState = QuizState.COMPLETED
        } else {
            quizState = QuizState.NEXT_QUESTION
            checkButton.text = "NEXT"
        }
    }

    private fun nextQuestion() {
        currentPosition++
        setNewQuestion()
    }

    private fun endQuiz() {
        val intent = Intent(this@QuestionActivity, ResultActivity::class.java).apply {
            putExtra(Constants.USER_NAME, userName)
            putExtra(Constants.SCORE, score)
        }
        startActivity(intent)
        finish()
    }

    private fun highlightAnswer(answer: Int, drawableRes: Int) {
        when (answer) {
            1 -> textViewOp1.background = ContextCompat.getDrawable(this, drawableRes)
            2 -> textViewOp2.background = ContextCompat.getDrawable(this, drawableRes)
            3 -> textViewOp3.background = ContextCompat.getDrawable(this, drawableRes)
            4 -> textViewOp4.background = ContextCompat.getDrawable(this, drawableRes)
        }
    }


    private fun selectedOption(textView: TextView, selectOptionNumber: Int) {
        resetOptions()
        if (!isAnswerChecked) {
            selectedAnswer = selectOptionNumber
            textView.setTextColor(color.parseColor("#363A43"))
            textView.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_bg
            )
        }


    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_View_option1 -> {
                selectedOption(textViewOp1, 1)
            }

            R.id.text_View_option2 -> {
                selectedOption(textViewOp2, 2)
            }

            R.id.text_View_option3 -> {
                selectedOption(textViewOp3, 3)
            }

            R.id.text_View_option4 -> {
                selectedOption(textViewOp4, 4)
            }

            R.id.button_check_ans -> {
                checkAnswer()
            }


        }
    }
    }




