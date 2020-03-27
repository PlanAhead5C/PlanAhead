package com.example.planahead5c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class addStats extends AppCompatActivity {

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stats);
        myDb = new DatabaseHelper(this);
    }

    public void updateBar(View view)
    {
        try
        {
            myDb = new DatabaseHelper(this);
            TextView attendanceVal = (TextView) findViewById(R.id.attendNum);
            TextView studiedVal = (TextView) findViewById(R.id.studyNum);
            TextView sleepVal = (TextView) findViewById(R.id.sleepNum);
            int attendance = Integer.parseInt(attendanceVal.getText().toString());
            int study = Integer.parseInt(studiedVal.getText().toString());
            float newStudy = 0;
            if (study >= 20)
            {
                newStudy = 100;
            }
            else
            {
                newStudy = study*100;
                newStudy = (newStudy/20);
            }
            int sleep = Integer.parseInt((sleepVal.getText().toString()));
            if (sleep >= 49 && sleep <= 63)
            {
                sleep = 100;
            }
            else if ((sleep < 49 && sleep >= 35) || (sleep > 63 && sleep <= 70))
            {
                sleep = 50;
            }
            else if (sleep < 35 || sleep > 70)
            {
                sleep = 0;
            }

            Double eff = ((attendance * 0.3) + (newStudy* 0.5) + (sleep*0.2));
            ProgressBar efficiency = (ProgressBar) findViewById(R.id.progressBar);
            efficiency.setProgress(eff.intValue());
            int progress = eff.intValue();
            TextView progressNum = (TextView) findViewById(R.id.progressNumber);
            progressNum.setText(String.valueOf(progress));

            boolean isInserted = myDb.insertStats(progressNum.getText().toString(),
                    attendanceVal.getText().toString(),
                    studiedVal.getText().toString(), sleepVal.getText().toString());
            if(isInserted == true)
                Toast.makeText(addStats.this,"Data Inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(addStats.this,"Data not Inserted",Toast.LENGTH_LONG).show();

           /* attendanceVal.setText("");
            studiedVal.setText("");
            sleepVal.setText("");*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
