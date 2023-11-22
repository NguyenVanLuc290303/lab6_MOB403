package com.example.lab6_mob403;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText ed_content;
    TextView tv_result;
    Button btn_CtoF,btn_FtoC;
    int convertStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_content = findViewById(R.id.ed_content);
        tv_result =findViewById(R.id.tv_result);
        btn_CtoF = findViewById(R.id.btn_CtoF);
        btn_FtoC = findViewById(R.id.btn_FtoC);
        btn_FtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invokeAsyncTask("Fahrenheit", Constants.F_TO_C_SOAP_ACTION,
                        Constants.F_TO_C_METHOD_NAME);
                convertStyle = 1;
            }
        });

        btn_CtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invokeAsyncTask("Celsius", Constants.C_TO_F_SOAP_ACTION,
                        Constants.C_TO_F_METHOD_NAME);
                convertStyle = 0;
            }
        });

    }

    private void invokeAsyncTask(String convertParams, String soapAction, String methodName) {
        new ConvertTemperatureTask(this, soapAction, methodName,
                convertParams).execute(ed_content.getText()
                .toString().trim());
    }
    //call back data from background thread and set to views
    public void callBackDataFromAsyncTask(String result) {
        double resultTemperature = Double.parseDouble(result);
        if (convertStyle == 0) {// C degree to F degree
            tv_result.setText(ed_content.getText().toString().trim() +" degree\n" +
                    "Celsius = " +  String.format("%.2f", resultTemperature) + "degree\n" +
                    "Fahrenheit ");

        }else {
            tv_result.setText(ed_content.getText().toString().trim() + " degree\n" +
                   " Fahrenheit = "
                            + String.format("%.2f", resultTemperature) + " degree\n" +
                   " Celsius");
        }
    }
}