package com.example.pratyush.asynctaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText num1;
    Button btn1;
    TextView txtv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=(EditText)findViewById(R.id.edt1);
        btn1=(Button)findViewById(R.id.btnProcess);
        txtv1=(TextView)findViewById(R.id.txtView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numFiles=Integer.parseInt(num1.getText().toString());
                Mytask mytask=new Mytask();
                mytask.execute(numFiles);
                txtv1.setText("Time left is " + num1 + "Seconds.");

            }
        });
    }
    private class Mytask extends AsyncTask<Integer, Integer, String>{
        protected void onPreExecute(){
            txtv1.setText(("Start the Simulator"));

        }
        protected String doInBackground(Integer... integers) {


            for (int i = 0; i<integers[0]; i++)
                try {
                    Thread.sleep(1000);
                    publishProgress(i + 1);
                } catch (InterruptedException e) {
                    Log.wtf("MainActivity", e.getMessage());
                }
            return "Done Processing" +integers[0]+""+"files";
        }
        protected void onProgressUpdate(Integer... values) {
            txtv1.setText((values[0])+ "second left");
        }
        protected void onPostExecute(String message) {

            txtv1.setText(message);
        }
    }
}
