package com.example.ahmed.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener , DatePickerDialog.OnDateSetListener {


    TextView t1,t2;
    Button b1;


    int year , month , day, hour , min ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 =(Button) findViewById(R.id.button2);

        t1 = (TextView) findViewById(R.id.textView);
        t2= (TextView) findViewById(R.id.textView2);


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();

                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });


        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();


                TimePickerDialog time =TimePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.HOUR),
                        now.get(Calendar.MINUTE),
                        now.get(Calendar.SECOND),
                        true
                );

                time.show(getFragmentManager(),"");

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

                Calendar calendar = new GregorianCalendar(year,month,day,hour,min);

                long time = calendar.getTimeInMillis();


                Intent intent = new Intent(MainActivity.this,MainActivity.class);

                PendingIntent pending = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                manager.set(AlarmManager.RTC,time,pending);
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        String date = String.valueOf(dayOfMonth) + "/" +String.valueOf(monthOfYear) + "/" + String.valueOf(year);


        t1.setText(date);


        this.year = year;
        this.month = monthOfYear ;
        this.day = dayOfMonth;

    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {


        String time = String.valueOf(hourOfDay) + ":" +String.valueOf(minute);

        t2.setText(time);

        this.hour = hourOfDay;
        this.min = minute;
    }
}
