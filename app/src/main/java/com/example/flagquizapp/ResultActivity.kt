package com.example.flagquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquizapp.utils.Constants
import com.example.flagquizapp.databinding.ActivityResultBinding
import com.example.flagquizapp.model.HighScore
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.jvm.java

class ResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResultBinding
    private lateinit var firebaseRefHighScores: DatabaseReference
    private lateinit var buttonTry: Button
    private lateinit var buttonLeaderBoard: Button
    private var userName = ""
    private var score = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseRefHighScores = FirebaseDatabase.getInstance().getReference("high_scores")


        userName = intent.getStringExtra(Constants.USER_NAME).toString()
        score = intent.getIntExtra(Constants.SCORE, 0)

        addHighScoreToDB(userName, score)

        val totalQuestions = Constants.getQuestions().size
        val congratulationsTv: TextView = findViewById(R.id.congratulationsTv)
        val scoreTv: TextView = findViewById(R.id.scoreTv)

        congratulationsTv.text = "Congratulations, $userName!"
        scoreTv.text = "Your score is $score of $totalQuestions"

        buttonTry = findViewById(R.id.btnRestart)
        buttonLeaderBoard = findViewById(R.id.btn_lb)
        buttonTry.setOnClickListener(this)
        buttonLeaderBoard.setOnClickListener(this)

    }

    private fun addHighScoreToDB(userName: String, score: Int) {
        val recordId = firebaseRefHighScores.push().key!!
        val highScore = HighScore(recordId,userName, score)
        firebaseRefHighScores.child(recordId).setValue(highScore.copy(recordId))
    }

    override fun onClick(view: View?) {

            when (view?.id) {
                R.id.btn_lb -> {
                    val intent = Intent(this@ResultActivity, LeaderBoardActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                R.id.btnRestart -> {
                    val intent = Intent(this@ResultActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
