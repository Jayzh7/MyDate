package com.example.jay.calendarview.TabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.calendarview.AddEvent.DatePickerActivity;
import com.example.jay.calendarview.EditEvent.DatePicker2Activity;
import com.example.jay.calendarview.R;
import com.example.jay.calendarview.fragments.DeadLineFragment;
import com.example.jay.calendarview.fragments.DeadlineContent.DeadlineContent;
import com.example.jay.calendarview.fragments.MemorialDayFragment;

/**
 * Created by Jay on 2016/12/22.
 */


public class TabActivity extends AppCompatActivity implements DeadLineFragment.OnListFragmentInteractionListener{

    public final int ADD_BUTTON   = 1;
    public final int EDIT_BUTTON = 0;
    private DeadlineContent deadlineContent = new DeadlineContent();
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Deadline"));
        tabLayout.addTab(tabLayout.newTab().setText("Memorial Day"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        initToolBar();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.menu_item_add:
                //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"易成更喜欢黄铮杰！", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(TabActivity.this,//TODO editted just now
                        DatePickerActivity.class), ADD_BUTTON);
                break;
            case R.id.menu_item_edit:
                Toast.makeText(getApplicationContext(),"黄铮杰最喜欢易成了！", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(TabActivity.this,
                        DatePicker2Activity.class), EDIT_BUTTON);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolBar(){
        Toolbar tabToolbar = (Toolbar) findViewById(R.id.tab_toolbar);
        setSupportActionBar(tabToolbar);
        ActionBar ab = getSupportActionBar();
        //enable the up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onListFragmentInteraction(DeadlineContent.DeadlineItem item) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case ADD_BUTTON:
                    String name = data.getStringExtra("name");
                    String memo = data.getStringExtra("memo");
                    String dateStr = data.getStringExtra("date");
                    DeadlineContent.DeadlineItem newItem = new DeadlineContent.DeadlineItem(name, dateStr, memo);
                    deadlineContent.addItem(newItem);

                    Log.d("TabActivity", "debug in Tab " + DeadlineContent.numOfItems);
                    adapter.update(0);//update the first tab
                    break;
                case EDIT_BUTTON:
                    String state = data.getStringExtra("state");
                    int days = data.getIntExtra("date", 0);
                    String mm = data.getStringExtra(("memo"));

                    TextView stateTextView = (TextView)findViewById(R.id.state);
                    TextView dateView = (TextView)findViewById(R.id.time);
                    stateTextView.setText(state);
                    Toast.makeText(getApplicationContext(), "黄铮杰最喜欢易成了！", Toast.LENGTH_SHORT).show();
                    dateView.setText(""+days+"days");
                    Log.d("TabActivity", "debug in EDIT" + state);
                    ((MemorialDayFragment) adapter.getItem(1)).refresh(state, ""+days+" days");
                    break;
            }
    }
}
