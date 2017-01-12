package com.example.jay.calendarview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.calendarview.Calendar.CalendarCard;
import com.example.jay.calendarview.Calendar.CalendarCard.OnCellClickListener;
import com.example.jay.calendarview.Calendar.CalendarViewAdapter;
import com.example.jay.calendarview.model.CustomDate;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnCellClickListener, NavigationView.OnNavigationItemSelectedListener{
    private ViewPager mViewPager;
    private int mCurrentIndex = 498;
    private CalendarCard[] mShowViews;
    private CalendarViewAdapter<CalendarCard> adapter;
    private SildeDirection mDirection = SildeDirection.NO_SILDE;
    private Button textButton;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    enum SildeDirection {
        RIGHT, LEFT, NO_SILDE;
    }

    private TextView monthText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initToolbar();
        bindItems();
        bindListener();

        CalendarCard[] views = new CalendarCard[3];
        for (int i = 0; i < 3; i++) {
            views[i] = new CalendarCard(this, this);
        }
        adapter = new CalendarViewAdapter<>(views);
        setViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.simple_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //TODO further realization
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener()
    {
        @Override public boolean onMenuItemClick(MenuItem menuItem)
        {
            switch (menuItem.getItemId()) {
                default:
                    break;
            }
            return true;
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //debug message
                Toast.makeText(getApplicationContext(), "易成最可爱了！", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, com.example.jay.calendarview.TabLayout.TabActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar()
    {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);

        //corresponding to the toolbar
        ActionBar ab = getSupportActionBar();

        //enable the up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void bindListener()
    {
        textButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                for further use
                 */
                //Toast.makeText(getApplicationContext(), "别点了，再点也没用", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindItems()
    {
        mViewPager = (ViewPager) this.findViewById(R.id.vp_calendar);
        monthText = (TextView) this.findViewById(R.id.tvCurrentMonth);
        textButton = (Button)this.findViewById(R.id.btnDeadline);
    }



    private void setViewPager() {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(498);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                measureDirection(position);
                updateCalendarView(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnPreMonth:
//                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
//                break;
//            case R.id.btnNextMonth:
//                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
//                break;
           // case R.id.btnMemday:
                // TODO: 2016/12/19

                //break;
            default:
                break;
        }
    }

    /*
    further use
     */
    @Override
    public void clickDate(CustomDate date) {
    }

    @Override
    public void changeDate(CustomDate date) {
        monthText.setText(date.month + "月");
    }

    /**
     * 计算方向
     *
     * @param arg0
     */
    private void measureDirection(int arg0) {

        if (arg0 > mCurrentIndex) {
            mDirection = SildeDirection.RIGHT;

        } else if (arg0 < mCurrentIndex) {
            mDirection = SildeDirection.LEFT;
        }
        mCurrentIndex = arg0;
    }

    // 更新日历视图
    private void updateCalendarView(int arg0) {
        mShowViews = adapter.getAllItems();
        if (mDirection == SildeDirection.RIGHT) {
            mShowViews[arg0 % mShowViews.length].rightSlide();
        } else if (mDirection == SildeDirection.LEFT) {
            mShowViews[arg0 % mShowViews.length].leftSlide();
        }
        mDirection = SildeDirection.NO_SILDE;
    }

}

