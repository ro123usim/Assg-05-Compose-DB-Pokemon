package com.example.m02_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.editText_Num1);
        num2 = findViewById(R.id.editText_Num2);
        result = findViewById(R.id.editText_Result);
    }

    private double getNum1() {
        return Double.parseDouble(num1.getText().toString());
    }

    private double getNum2() {
        return Double.parseDouble(num2.getText().toString());
    }

    public void add(View v) {
        result.setText(String.valueOf(getNum1() + getNum2()));
    }

    public void subtract(View v) {
        result.setText(String.valueOf(getNum1() - getNum2()));
    }

    public void multiply(View v) {
        result.setText(String.valueOf(getNum1() * getNum2()));
    }

    public void divide(View v) {
        result.setText(String.valueOf(getNum1() / getNum2()));
    }
}

