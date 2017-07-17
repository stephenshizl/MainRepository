package com.example.a61555.constraintlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private MenuItem item1;
    private MenuItem item2;
    private MenuItem item3;
    private MenuItem item4;
    private ToggleButton toggleBtn;
    private Switch switchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        toggleBtn = (ToggleButton) findViewById(R.id.toggleButton);
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invalidateOptionsMenu();
            }
        });

        switchBtn = (Switch) findViewById(R.id.switch1);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    invalidateOptionsMenu();
                else {
                    item1.setEnabled(true);
                    item2.setEnabled(true);
                    item3.setEnabled(true);
                    item4.setEnabled(true);
                }
            }
        });
    }

    /**
     * 首次构建App时调用
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * 用于对 Menu 进行预处理，每次打开就会调用此方法
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.i("[MENU]", "Prepare Options Menu");
        item1 = menu.getItem(0);
        item2 = menu.getItem(1);
        item3 = menu.getItem(2);
        item4 = menu.getItem(3);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 重写禁用 Item 的方法，需要自己调用
     */
    @Override
    public void invalidateOptionsMenu() {
        Log.i("[MENU]", "Invalidate Options Menu");
        item1.setEnabled(false);
        item2.setEnabled(false);
        item3.setEnabled(false);
        item4.setEnabled(false);
    }

    /**
     * 重写 Item 点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("[MENU]", "Options Item Selected");
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item2:
                Toast.makeText(this, "Item2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item3:
                Toast.makeText(this, "Item3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item4:
                Toast.makeText(this, "Item4", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
