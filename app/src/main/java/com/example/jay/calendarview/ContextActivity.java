package com.example.jay.calendarview;

import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//unused, waiting to modify.
/**
 * Created by Jay on 2016/12/19.
 */

public class ContextActivity extends AppCompatActivity{
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ctx_menu_item_add: {
                Toast.makeText(this, "Context Menu Add", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.ctx_menu_item_edit: {
                Toast.makeText(this, "Context Menu Delete", Toast.LENGTH_SHORT).show();
                return true;
            }
            default: {
                return super.onContextItemSelected(item);
            }
        }
    }

}
