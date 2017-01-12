package com.example.jay.calendarview.EditEvent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jay.calendarview.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jay on 2016/12/22.
 */

public class DatePickDialogUtil2 implements DatePicker.OnDateChangedListener{
    private DatePicker datePicker;
    private String newDate;
    private Activity activity;
    private Calendar newCal;

    public DatePickDialogUtil2(Activity activity){
        this.activity = activity;
    }

    public void init(DatePicker datePicker){
        android.text.format.Time t = new android.text.format.Time();
        t.setToNow();
        datePicker.init(t.year, t.month, t.monthDay, this);
    }

    public AlertDialog datePickDialog(final EditText inputDate){
        LinearLayout dateLayout = (LinearLayout)activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker)dateLayout.findViewById(R.id.datepicker);
        init(datePicker);

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setView(dateLayout)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        inputDate.setText(newDate);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        inputDate.setText("");
                    }
                }).show();

        onDateChanged(null, 0, 0, 0);

        return alertDialog;
    }

    public void onDateChanged(DatePicker view, int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        newCal = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        newCal = calendar;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        newDate = sdf.format(calendar.getTime());

    }

    public Calendar getCalendar()
    {
        return newCal;
    }
}
