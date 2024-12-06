/*

Written by AD

 */
package com.example.ijdje;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;


public class MainActivity extends AppCompatActivity {
    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.textView);

        //create an instance......
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {
                hello.setText(R.string.speechStart);

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                //....
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(Arrays.toString(buffer));
                //  hello.setText(stringBuffer);

            }

            @Override
            public void onEndOfSpeech() {
                hello.setText(R.string.speechStop);
            }

            @Override
            public void onError(int error) {
                if(error == SpeechRecognizer.ERROR_NO_MATCH){

                    hello.setText(R.string.match);
                }

            }

            @Override
            public void onResults(Bundle results) {

                ArrayList<String> arrayList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                arrayList.iterator().forEachRemaining(new Consumer<String>() {
                    @Override
                    public void accept(String string) {


                        hello.setText(string);

                    }

                });
            }
            @Override
            public void onPartialResults(Bundle partialResults) {



            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        speechRecognizer.startListening(new Intent(SpeechRecognizer.DETECTED_LANGUAGE));



        //create an instance
        TextToSpeech textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {



            }
        });


        //add a sound
        textToSpeech.addSpeech("work",new File("3313_rain_raining_rain_drops_RaindropsVidevo.mp4"));
        textToSpeech.speak("work",TextToSpeech.QUEUE_ADD, Bundle.EMPTY, "104");
        textToSpeech.setVoice(textToSpeech.getDefaultVoice());

    }
}