package com.example.gitrepos.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseActivity extends AppCompatActivity {

    protected String activityTitle = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getTitle() != null) {
            activityTitle = getTitle().toString();
        }
        ToolBarGenerator.setToolbar(BaseActivity.this);
    }

    public void openActivity(@Nullable Bundle extras, Class openClass, int enterAnim, int exitAnim) {

        Intent open = new Intent(this, openClass);
        if (extras != null) {
            open.putExtras(extras);
        }
        open.putExtra("enterAnimation", enterAnim);
        startActivity(open);
        overridePendingTransition(enterAnim, exitAnim);
    }

    protected void setActivityTitle(String title) {
        setTitle(title);
        activityTitle = title;
    }

    protected void addLeftItem(int resourceDrawable, View.OnClickListener listener) {
        ToolBarGenerator.addLeftItem(BaseActivity.this, resourceDrawable, listener);
    }


    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        ToolBarGenerator.setTitle(BaseActivity.this, title);
    }
}
