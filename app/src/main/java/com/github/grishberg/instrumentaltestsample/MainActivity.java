package com.github.grishberg.instrumentaltestsample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button phoneButton;
    private Button tabletButton;
    private Button tabletButton600dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        phoneButton = findViewById(R.id.buttonForPhone);
        tabletButton = findViewById(R.id.buttonForTablet);
        tabletButton600dp = findViewById(R.id.buttonFor600dp);

        if (phoneButton != null) {
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(R.string.text_for_phone);
                    // simulate process crash: System.exit(-1);
                }
            });
        }

        if (tabletButton != null) {
            tabletButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(R.string.text_for_tablet);
                }
            });
        }

        if (tabletButton600dp != null) {
            tabletButton600dp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(R.string.text_for_600dp);
                }
            });
        }
    }
}
