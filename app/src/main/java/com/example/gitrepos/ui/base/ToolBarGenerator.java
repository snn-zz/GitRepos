package com.example.gitrepos.ui.base;

import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gitrepos.R;
import com.example.gitrepos.utils.DeviceScreenUtil;

public class ToolBarGenerator {

    public static void setToolbar(BaseActivity activity) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            View toolbarView = inflater.inflate(R.layout.toolbar_layout, null);
            TextView textView = toolbarView.findViewById(R.id.app_bar_text_view);
            textView.setText(activity.getTitle());
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(toolbarView);
        }
    }

    static void setTitle(BaseActivity activity, CharSequence title) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            View toolbarView = actionBar.getCustomView();
            TextView textView = toolbarView.findViewById(R.id.app_bar_text_view);
            textView.setText(title);
        }
    }

    static void addLeftItem(BaseActivity activity, int resourceDrawable, View.OnClickListener listener) {
        addItem(activity, resourceDrawable, listener, R.id.left_items_container);
    }

    private static void addItem(BaseActivity activity, int resourceDrawable, View.OnClickListener listener, int containerResourceID) {
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            ImageView itemView = new ImageView(activity);
            int iconSize = DeviceScreenUtil.dpToPx(32);
            int iconMargin = DeviceScreenUtil.dpToPx(4);
            itemView.setPadding(iconMargin, iconMargin, iconMargin, iconMargin);
            LinearLayout.LayoutParams iconParam = new LinearLayout.LayoutParams(iconSize, iconSize);
            itemView.setLayoutParams(iconParam);
            itemView.setImageResource(resourceDrawable);
            itemView.setOnClickListener(listener);
            View toolbarView = activity.getSupportActionBar().getCustomView();
            LinearLayout containerLayout;
            containerLayout = toolbarView.findViewById(containerResourceID);
            containerLayout.addView(itemView);
        }
    }
}
