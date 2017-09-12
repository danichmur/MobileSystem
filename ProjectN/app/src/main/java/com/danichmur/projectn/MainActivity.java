package com.danichmur.projectn;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int secret_value;
    int max, min;
    Resources resources;
    boolean gameFinished;
    int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.textView);
        etInput = (EditText) findViewById(R.id.editText);
        bControl = (Button) findViewById(R.id.button);
        secret_value = (int) (Math.random() * 100);
        resources = getResources();
        max = 100;
        min = 1;
        gameFinished = false;
        colors = new int[]{R.color.color10, R.color.color20, R.color.color30,
                R.color.color40, R.color.color50, R.color.color60,
                R.color.color70, R.color.color80, R.color.color90};
    }

    public void onClick(View v) {
        if (!gameFinished) {
            try {
                int value = Integer.parseInt(etInput.getText().toString());

                if (value > max || value < min) {
                    tvInfo.setText(resources.getString(R.string.error));
                    return;
                }
                bControl.setText(resources.getString(R.string.input_value));
                changeColor(colors[Math.abs(secret_value - value) / 10 % 9]);
                if (value > secret_value)
                    tvInfo.setText(resources.getString(R.string.ahead));
                if (value < secret_value)
                    tvInfo.setText(resources.getString(R.string.behind));
                if (value == secret_value) {
                    tvInfo.setText(resources.getString(R.string.hit));
                    bControl.setText(resources.getString(R.string.play_more));
                    gameFinished = true;
                }
            } catch (NumberFormatException e) {
                tvInfo.setText(resources.getString(R.string.error));
            }
        } else {
            secret_value = (int) (Math.random() * 100);
            bControl.setText(resources.getString(R.string.input_value));
            tvInfo.setText(resources.getString(R.string.try_to_guess));
            gameFinished = false;
            etInput.setText("");
            changeColor(colors[8]);
        }
    }

    public void changeColor(int color){
        System.out.println(secret_value);
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(resources.getColor(color));
    }
}
