package me.systembug.tv.view;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * Created by systembug on 4/9/16.
 */
public class CustomeSwitchCompat extends SwitchCompat {

    public CustomeSwitchCompat(Context context) {
        super(context);
    }
    public CustomeSwitchCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeSwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                toggle();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
