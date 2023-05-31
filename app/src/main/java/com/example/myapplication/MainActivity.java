package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editTextWidth;
    private EditText editTextLength;
    private EditText editTextHeight;
    private Button buttonCalculate;
    private TextView tvResult;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextHeight = findViewById(R.id.etHeight);
        editTextLength = findViewById(R.id.etLength);
        editTextWidth = findViewById(R.id.etWidth);
        buttonCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String width = editTextWidth.getText().toString();
                String height = editTextHeight.getText().toString();
                String length = editTextLength.getText().toString();
                if (width.isEmpty()){editTextWidth.setError("Masih Kosong");}
                else if (height.isEmpty()){editTextHeight.setError("Masih Kosong");}
                else if (length.isEmpty()){editTextLength.setError("Masih Kosong");}
                else{
                    mainViewModel.calcultae(width,height,length);
//                    calculate(width,height,length);
                }
            }
        });
        displayResult();
    }
    public void displayResult(){
        //melakukan observe live data
        mainViewModel.result.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvResult.setText(String.valueOf(integer));
            }
        });
    }

    private void calculate(String width, String height, String length){
        //menghitung volume tanpa viewmodel
        //data tidak dipertahankan
        int result = Integer.parseInt(width) * Integer.parseInt(height) * Integer.parseInt(length);
        tvResult.setText(String.valueOf(result));
    }
}