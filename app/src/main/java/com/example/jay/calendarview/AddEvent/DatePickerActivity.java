package com.example.jay.calendarview.AddEvent;

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

import com.example.jay.calendarview.R;

/**
 * Created by Jay on 2016/12/22.
 */

public class DatePickerActivity extends AppCompatActivity{
    private EditText eventDate;
    private EditText eventName;
    private EditText eventMemo;

    private Button saveButton;
    private Button cancelButton;

    //private String initDateTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_date_pick);
        initToolbar();
        bindView();
        setListener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //Toast.makeText(getApplicationContext(), "up clicked", Toast.LENGTH_SHORT).show();
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
        saveButton = (Button)findViewById(R.id.btnSave);
        cancelButton = (Button)findViewById(R.id.btnCancel);

        eventMemo = (EditText)findViewById(R.id.event_memo);
        eventName = (EditText)findViewById(R.id.event_name);
        eventDate = (EditText)findViewById(R.id.event_Date);
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

                Intent intent = new Intent();
                intent.putExtra("name", eventName.getText().toString());
                intent.putExtra("memo", eventMemo.getText().toString());
                intent.putExtra("date", eventDate.getText().toString());
                Log.d("DatePickerActivity", "debug in DatePicker name:"+(eventDate.getText().toString()));


                Log.d("DatePickerActivity", "debug in DatePicker");
                DatePickerActivity.this.setResult(RESULT_OK, intent);
                DatePickerActivity.this.finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                DatePickerActivity.this.setResult(RESULT_CANCELED, intent);
                DatePickerActivity.this.finish();
            }
        });

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickDialogUtil datePickDialog = new DatePickDialogUtil(
                        DatePickerActivity.this);
                datePickDialog.datePickDialog(eventDate);
            }
        });
    }
}
