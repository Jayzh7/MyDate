package com.example.jay.calendarview.EditEvent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jay.calendarview.R;

import java.util.Calendar;

/**
 * Created by Jay on 2016/12/22.
 */

public class DatePicker2Activity extends AppCompatActivity{
    private EditText editDate;
    private EditText editState;
    private EditText editMemo;

    private Button saveButton;
    private Button cancelButton;

    private static Calendar calendar;
    private Integer days;
    private DatePickDialogUtil2 datePickDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_date_pick);
        initToolbar();
        bindView();
        setListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simple_menu, menu);
        return true;
    }

    private void bindView(){

        editState = (EditText)findViewById(R.id.edit_state);
        editDate = (EditText)findViewById(R.id.edit_date);
        editMemo = (EditText)findViewById(R.id.edit_memo);

        saveButton = (Button)findViewById(R.id.btnEditSave);
        cancelButton = (Button)findViewById(R.id.btnEditCancel);
    }

    private void initToolbar(){
        Toolbar tabToolbar = (Toolbar) findViewById(R.id.date_pick_toolbar);
        setSupportActionBar(tabToolbar);
        ActionBar ab = getSupportActionBar();
        //enable the up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setListener(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar preCalendar = Calendar.getInstance();
                calendar = datePickDialog.getCalendar();

//                int day = ((Long)preCalendar.getTimeInMillis()).intValue;
                //days = (int)(((preCalendar.getTimeInMillis()-calendar.getTimeInMillis()))/(24 * 60 * 60 * 1000));
                preCalendar.set(Calendar.HOUR_OF_DAY, 0);
                preCalendar.set(Calendar.MINUTE, 0);
                preCalendar.set(Calendar.SECOND, 0);

                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);


                Long tmpStr = (preCalendar.getTimeInMillis()- calendar.getTimeInMillis())/(1000*60*60*24);
                days = tmpStr.intValue();
//                Toast.makeText(getApplicationContext(), tmpStr.toString(), Toast.LENGTH_SHORT).show();
                Log.d("DATEPCIKER2", tmpStr.toString());

                Intent intent = new Intent();
                intent.putExtra("state", editState.getText().toString());
                intent.putExtra("memo", editMemo.getText().toString());
                intent.putExtra("date", days);
                //Log.d("DatePicker2Activity", "debug in DatePicker name:"+(eventDate.getText().toString()));

                DatePicker2Activity.this.setResult(RESULT_OK, intent);
                DatePicker2Activity.this.finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                DatePicker2Activity.this.setResult(RESULT_CANCELED, intent);
                DatePicker2Activity.this.finish();
            }
        });

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickDialog = new DatePickDialogUtil2(
                        DatePicker2Activity.this);
                datePickDialog.datePickDialog(editDate);

            }
        });
    }
}
