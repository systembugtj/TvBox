package me.systembug.tv.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;

import java.util.ArrayList;
import java.util.List;

import me.systemb.tv.view.R;

/**
 * Created by systembug on 5/4/16.
 */
public class SelectBox extends RelativeLayout {


    public interface OnSelectChanged {
        void selectChanged (int which);
    }

    TextView mActionItem;
    ImageView mLeft;
    ImageView mRight;
    TextView mTitle;
    TextView mDesc;
    int mCurrent = 0;
    List<String> mOptions = new ArrayList<String>();
    OnSelectChanged mChangedListener = null;

    public SelectBox(Context context) {
        this(context, null);
    }

    public SelectBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    public void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        View v = LayoutInflater.from(context).inflate(R.layout.select_box, null);

        mLeft = (ImageView) v.findViewById(R.id.left_arrow);
        mRight = (ImageView) v.findViewById(R.id.right_arrow);

        mTitle = (TextView) v.findViewById(R.id.action_title);
        mDesc = (TextView) v.findViewById(R.id.action_description);
        mActionItem = (TextView) v.findViewById(R.id.action_item);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SelectBox);

            mTitle.setText(a.getString(R.styleable.SelectBox_select_title));
            mDesc.setText(a.getString(R.styleable.SelectBox_select_description));

            Drawable leftArrow = a.getDrawable(R.styleable.SelectBox_left_arrow);
            if (leftArrow != null) {
                mLeft.setImageDrawable(leftArrow);
            } else {
                mLeft.setImageDrawable(new IconDrawable(getContext(),
                        MaterialIcons.md_keyboard_arrow_left).color(Color.WHITE).actionBarSize());
            }
            Drawable rightArrow = a.getDrawable(R.styleable.SelectBox_right_arrow);
            if (rightArrow != null) {
                mRight.setImageDrawable(rightArrow);
            } else {
                mRight.setImageDrawable(new IconDrawable(getContext(),
                        MaterialIcons.md_keyboard_arrow_right).color(Color.WHITE).actionBarSize());
            }
        }
        this.setFocusable(true);
        // Disable descendant handle focus, we are one.
        this.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        this.setFocusableInTouchMode(true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelection();
            }
        });
        addView(v);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                mCurrent ++;
                if (mCurrent >= mOptions.size()) {
                    mCurrent = mOptions.size() - 1;
                }
                updateSelection();

                return true;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                mCurrent --;
                if (mCurrent <= 0) {
                    mCurrent = 0;
                }
                updateSelection();

                return true;
            case KeyEvent.KEYCODE_ENTER:
                showSelection();
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public void showSelection() {
        AlertDialog.Builder b = new AlertDialog.Builder(this.getContext());
        b.setTitle(mTitle.getText());

        String[] opts = mOptions.toArray(new String[mOptions.size()]);

        b.setItems(opts, (dialog, which) -> {

                    dialog.dismiss();
                    mCurrent = which;



                    updateSelection();
                });

        b.create().show();
    }

    private void updateSelection() {
        if (mCurrent >= 0 && mCurrent < mOptions.size()) {
            mActionItem.setText(mOptions.get(mCurrent));
            fireSelecChanged(mCurrent);
        }
    }

    public SelectBox selectChanged(@NonNull OnSelectChanged listener) {
        mChangedListener = listener;
        return this;
    }

    public SelectBox options(@NonNull List<String> options) {

        if(options.size() > 0) {
            mCurrent = 0;
            mActionItem.setText(options.get(mCurrent));

            mOptions.clear();
            mOptions.addAll(options);
            fireSelecChanged(mCurrent);
        }
        return this;
    }

    public SelectBox leftArrow(@NonNull Drawable arrow) {
        mLeft.setImageDrawable(arrow);
        return this;
    }

    public SelectBox rightArrow(@NonNull Drawable arrow) {
        mRight.setImageDrawable(arrow);
        return this;
    }

    private void fireSelecChanged(int which) {
        if (mChangedListener != null) {
            mChangedListener.selectChanged(which);
        }
    }

    public SelectBox current(int which) {
        if (which >= 0 && which < mOptions.size()) {
            mCurrent = which;
            updateSelection();
        }
        return this;
    }
}
