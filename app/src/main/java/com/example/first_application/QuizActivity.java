package com.example.first_application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;






public class QuizActivity extends AppCompatActivity {

    Button btNext;
    RadioButton rb,rep,rep1,rep2;
    TextView questionNum,questionTextView;
    ImageView imageView;

    RadioGroup rg;
    String RepCorrect ;
    int score = 0;
    private int currentImageIndex = 0;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference questionsRef = db.collection("Question");
    private List<Question> questions = new ArrayList<>();
    int cpt=1;
    private int currentQuestionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        btNext = findViewById(R.id.bNext);
        rg = findViewById(R.id.rg);
        rep1 = findViewById(R.id.rb1);
        rep2 = findViewById(R.id.rb2);
        imageView = findViewById(R.id.image1);
        questionNum = findViewById(R.id.nquiz);
        questionTextView = findViewById(R.id.q);

        chargerQuestions();
        btNext.setOnClickListener(v -> {
            int selectedId = rg.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedId);
                String selectedAnswer = selectedRadioButton.getText().toString();
                String correctAnswer = questions.get(currentQuestionIndex).getCorrectrep();

                if (selectedAnswer.equals(correctAnswer)) {
                    score++;
                }
            } else {
                Toast.makeText(getApplicationContext(), "Choose an answer.", Toast.LENGTH_SHORT).show();
                return;
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                questionDisplay();
            } else {

                Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        });
    }
    private void chargerQuestions() {
        questionsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Question question = document.toObject(Question.class);
                        questions.add(question);
                    }
                    questionDisplay();
                } else {
                    Toast.makeText(getApplicationContext(), "Error in extracting data from firestore !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void questionDisplay() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionNum.setText("Question " +cpt);
            cpt++;
            questionTextView.setText(currentQuestion.getQuestion());
            Glide.with(this)
                    .load(currentQuestion.getImage())
                    .into(imageView);

            rep1.setText(currentQuestion.getReponse2());
            rep2.setText(currentQuestion.getReponse3());

            rg.clearCheck();
        }
    }




}