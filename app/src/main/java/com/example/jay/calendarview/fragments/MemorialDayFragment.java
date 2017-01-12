package com.example.jay.calendarview.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jay.calendarview.R;

/**
 * Created by Jay on 2016/12/22.
 */


public class MemorialDayFragment extends Fragment {
    private TextView state;
    private TextView time;
    private static View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.memorial_day, container, false);


//        Calendar from = Calendar.getInstance(), to = Calendar.getInstance();
//        from.set(2015, 9, 17);
//        from.set(Calendar.HOUR_OF_DAY,0);
//        from.set(Calendar.MINUTE, 0);
//        from.set(Calendar.SECOND, 0);
//        to.set(Calendar.HOUR_OF_DAY,0);
//        to.set(Calendar.MINUTE, 0);
//        to.set(Calendar.SECOND, 0);

//        Long dayL = (to.getTimeInMillis()-from.getTimeInMillis())/(24*3600*1000);

//        time.setText(""+dayL.intValue()+"days");
        if(view == null)
            Log.d("MDF", "view null");
        return view;
    }

    public void refresh(String stat, String tim){

        state = (TextView)view.findViewById(R.id.state);
        time = (TextView)view.findViewById(R.id.time);

        if(state != null)
            state.setText(stat);
        else
            Log.d("MDF", "state null");
        if(time != null)
            time.setText(tim);
        else
            Log.d("MDF", "time null");
    }


}


