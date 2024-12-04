package com.example.ijdje;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.graphics.Canvas;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.textView);



        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {
                hello.setText("Speech Start....");

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(Arrays.toString(buffer));
                hello.setText(stringBuffer);

            }

            @Override
            public void onEndOfSpeech() {
                hello.setText("Speech Stop....");
            }

            @Override
            public void onError(int error) {
                hello.setText(String.valueOf(error));
            }

            @Override
            public void onResults(Bundle results) {
                results.putCharSequence("A","Andy");

                hello.setText(results.getCharSequence("A"));

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

                partialResults.putCharSequence("A","Andy");
                hello.setText(partialResults.getCharSequence("A"));
            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        speechRecognizer.startListening(new Intent(SpeechRecognizer.DETECTED_LANGUAGE));




        TextToSpeech textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {



            }
        });



        textToSpeech.addSpeech("work",new File("3313_rain_raining_rain_drops_RaindropsVidevo.mp4"));
        textToSpeech.speak("work",TextToSpeech.QUEUE_ADD, Bundle.EMPTY, "104");
        textToSpeech.setVoice(textToSpeech.getDefaultVoice());


        Canvas canvas = new Canvas();
        canvas.drawText("Andy", 3.5f,7.3f, new Paint());
        canvas.clipOutPath(new Path());
        canvas.save();

    }
}