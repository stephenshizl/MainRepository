package com.example.a61555.bottomnavigationdemo4;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * RadioGroup + RadioButton 实现底部导航栏步骤有点多，需要配置style 文件，
 * 各个Tab 的 drawable selector 文件，color 文件，但是，配置完了这些之后，代码是最简洁的。这也是有好处的，
 * 以后要换什么图标啊，颜色，只需要改xml 文件就好，是不需要改代码逻辑的，因此这样方式来实现底部导航栏是个不错的选择
 * 不过点击按钮没有动画，但是反馈很快
 */
public class MainActivity extends AppCompatActivity {

    private Fragment[] mFragments;
    private RadioGroup radioGroup;
    private RadioButton mFirstRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mFragments = TabLayoutUtils.getFragments("Radio Button Navigation");
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                onTabItemSelected(checkedId);
            }
        });
        setDefaultView(R.id.radio_button_home);
    }
    /*
        切换界面
     */
    private void onTabItemSelected(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.radio_button_home:
                fragment = mFragments[0];
                break;
            case R.id.radio_button_explore:
                fragment = mFragments[1];
                break;
            case R.id.radio_button_like:
                fragment = mFragments[2];
                break;
            case R.id.radio_button_me:
                fragment = mFragments[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
        }
    }
    /*
        设置首页
     */
    private void setDefaultView(int firstViewId) {

        mFirstRadioButton = (RadioButton) findViewById(firstViewId);
        mFirstRadioButton.setChecked(true);
        onTabItemSelected(firstViewId);
    }
}
