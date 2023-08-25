package com.slide.mobileappdevelopment.Slide7;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hieuhayho.mobileappdevelopment.R;

public class ExplicitIntent extends AppCompatActivity {

    private Button buttonSendMessage;
    private EditText feedbackInput;
    private TextView feedbackOutput;

    private int MY_REQUEST_CODE = 11112001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        buttonSendMessage = (Button)this.findViewById((R.id.SendFeedbackButton));
        feedbackInput = (EditText)this.findViewById(R.id.FullNameInput);
        feedbackOutput = (TextView)this.findViewById((R.id.FeedbackOutput));

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessage();
            }

        });
    }


    public void SendMessage() {
        String fullName = this.feedbackInput.getText().toString();
        String message = "Hello, this is a message.";

        Intent intent = new Intent(this, GreetingActivity.class);
        intent.putExtra("FullName", fullName);
        intent.putExtra("Message", message);
        startActivityIntent.launch(intent);
    }

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        String feedback = result.getData().getStringExtra("Feedback");
                        feedbackOutput.setText(feedback);
                    } else {
                        feedbackOutput.setText("????");
                    }
                }
            });


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            String feedback = data.getStringExtra("Feedback");
            feedbackOutput.setText(feedback);
        } else {
            feedbackOutput.setText("????");
        }
    }
}