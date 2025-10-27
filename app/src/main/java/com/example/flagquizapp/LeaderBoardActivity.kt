package com.example.flagquizapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquizapp.model.HighScore
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class LeaderBoardActivity : AppCompatActivity() {
    private lateinit var textViewUsername1: TextView
    private lateinit var textViewScore1: TextView
    private lateinit var textViewUsername2: TextView
    private lateinit var textViewScore2: TextView
    private lateinit var textViewUsername3: TextView
    private lateinit var textViewScore3: TextView
    private lateinit var textViewUsername4: TextView
    private lateinit var textViewScore4: TextView
    private lateinit var textViewUsername5: TextView
    private lateinit var textViewScore5: TextView

    private lateinit var firebaseRefHighScores: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)

        firebaseRefHighScores = FirebaseDatabase.getInstance().getReference("high_scores")

        textViewUsername1 = findViewById(R.id.tv_user_name_1)
        textViewUsername2 = findViewById(R.id.tv_user_name_2)
        textViewUsername3 = findViewById(R.id.tv_user_name_3)
        textViewUsername4 = findViewById(R.id.tv_user_name_4)
        textViewUsername5 = findViewById(R.id.tv_user_name_5)
        textViewScore1 = findViewById(R.id.tv_score_1)
        textViewScore2 = findViewById(R.id.tv_score_2)
        textViewScore3 = findViewById(R.id.tv_score_3)
        textViewScore4 = findViewById(R.id.tv_score_4)
        textViewScore5 = findViewById(R.id.tv_score_5)


        val highScores = mutableListOf<HighScore>()

        firebaseRefHighScores.get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                for (scoreSnapshot in dataSnapshot.children) {
                    val highScore = scoreSnapshot.getValue(HighScore::class.java)
                    if (highScore != null) {
                        highScores.add(highScore)
                    }
                }
                highScores.sortByDescending { it.score }

                textViewUsername1.text = highScores[0].username
                textViewUsername2.text = highScores[1].username
                textViewUsername3.text = highScores[2].username
                textViewUsername4.text = highScores[3].username
                textViewUsername5.text = highScores[4].username

                textViewScore1.text = highScores[0].score.toString()
                textViewScore2.text = highScores[1].score.toString()
                textViewScore3.text = highScores[2].score.toString()
                textViewScore4.text = highScores[3].score.toString()
                textViewScore5.text = highScores[4].score.toString()

            }
        }


    }
}