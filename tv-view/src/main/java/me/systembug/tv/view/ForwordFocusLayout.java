package me.systembug.tv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by systembug on 4/9/16.
 */
public class ForwordFocusLayout extends RelativeLayout {

    private View mTarget;

    public ForwordFocusLayout(Context context) {
        this(context, null);
    }

    public ForwordFocusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTarget(View target) {
        mTarget = target;
    }

    public View getTarget() {
        return mTarget;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
            {
                if (mTarget != null) {
                    return mTarget.onKeyDown(keyCode, event);
                }
                return true;
            }
            case KeyEvent.KEYCODE_DPAD_RIGHT:
            {
                if (mTarget != null) {
                    return mTarget.onKeyDown(keyCode, event);
                }
                return true;
            }
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                if (mTarget != null) {
                    return mTarget.onKeyDown(keyCode, event);
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
