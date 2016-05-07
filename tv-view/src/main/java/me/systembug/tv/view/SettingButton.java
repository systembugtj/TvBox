package me.systembug.tv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.Set;

/**
 * Created by systembug on 5/6/16.
 */
public class SettingButton extends FrameLayout {
    public SettingButton(Context context) {
        this(context, null);
    }

    public SettingButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

    }
}
