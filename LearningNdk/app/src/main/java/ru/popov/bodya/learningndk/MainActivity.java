package ru.popov.bodya.learningndk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView sampleTextView = (TextView) findViewById(R.id.sample_text);
        sampleTextView.setText(stringFromJNI());

        final TextView evaluatedTextView = (TextView) findViewById(R.id.evaluated_text);
        evaluatedTextView.setText(getString(R.string.evaluated_value_text, 0));

        Button logButton = (Button) findViewById(R.id.log_button);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helloLog(sampleTextView.getText().toString());
            }
        });

        Button evaluateButton = (Button) findViewById(R.id.random_button);
        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = evaluateRandomNumber();
                evaluatedTextView.setText(result);
            }
        });


    }

    /**
     * native methods that are implemented
     */
    private native String evaluateRandomNumber();
    private native String stringFromJNI();
    private native void helloLog(String logThis);
}
