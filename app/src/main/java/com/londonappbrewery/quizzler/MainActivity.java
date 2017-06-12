package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here
      Button mTrueButon;
    Button mFalseButton;
    int mindex;
    TextView mQuestionTextView;
    int mQuestion;
    int mScore;
    TextView mScoreTextView;
    ProgressBar mProgressBar;

    // TODO: Declare member variables here:


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
          new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
  };
  final int PROGRESS_BAR_INCREMENT = (int)Math.ceil(100.0/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     mTrueButon = (Button) findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
mQuestionTextView =(TextView)findViewById(R.id.question_text_view);
        mQuestion = mQuestionBank[mindex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
         mScoreTextView =(TextView)findViewById(R.id.score);
        mProgressBar =(ProgressBar)findViewById(R.id.progress_bar);

        mTrueButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Quizzler","Button pressed !!");
                //Toast.makeText(getApplicationContext(),"True Presssed!!",Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                updateQuestion();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText( getApplicationContext(), "False Pressed !!", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                updateQuestion();
            }
        });
        TrueFalse exampleQuestion = new TrueFalse(R.string.question_1,true);
    }
    private void updateQuestion(){
        mindex=(mindex+1)%mQuestionBank.length;
        if(mindex==0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over ..");
            alert.setCancelable(false);
            alert.setMessage("Your Score " + mScore +" points");
            alert.setPositiveButton("Close Application ",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    finish();
                }
            });

          alert.show();
        }
        mQuestion = mQuestionBank[mindex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score" + mScore +"/" + mQuestionBank.length);
    }
    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mindex].isAnswer();
        if(userSelection==correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            mScore+=1;
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
