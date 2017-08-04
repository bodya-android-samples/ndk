package ru.popov.bodya.learningndk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CONST = 50;

    private int mRandomValue;
    private List<Integer> mRandomList;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    // region Activity lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandomList = new ArrayList<>();

        final TextView sampleTextView = (TextView) findViewById(R.id.sample_text);
        sampleTextView.setText(stringFromJNI());

        final TextView evaluatedRandomTextView = (TextView) findViewById(R.id.evaluated_text);
        evaluatedRandomTextView.setText(getString(R.string.evaluated_random_value_text, 0));

        final TextView evaluatedMaxTextView = (TextView) findViewById(R.id.max_value_text);
        evaluatedMaxTextView.setText(getString(R.string.evaluated_max_value, 0));

        Button logButton = (Button) findViewById(R.id.log_button);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helloLog(sampleTextView.getText().toString());
            }
        });

        Button evaluateRandomButton = (Button) findViewById(R.id.random_button);
        evaluateRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRandomValue = evaluateRandomNumber();
                mRandomList.add(mRandomValue);
                evaluatedRandomTextView.setText(getString(R.string.evaluated_random_value_text, mRandomValue));
            }
        });

        Button evaluateMaxValueButton = (Button) findViewById(R.id.find_max_button);
        evaluateMaxValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maxValue = evaluateMaxValue(mRandomValue, CONST);
                evaluatedMaxTextView.setText(getString(R.string.evaluated_max_value, maxValue));
            }
        });
    }

    // endregion

    /**
     * native methods that are implemented
     */

    private native String evaluateRandomNumberInText();

    private native String stringFromJNI();

    private native int evaluateMaxValue(int first, int second);

    private native int evaluateRandomNumber();

    private native void helloLog(String logThis);
}
