package me.systembug.tv.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;

import org.w3c.dom.Text;

import me.systemb.tv.view.R;

/**
 * Created by systembug on 5/6/16.
 */
public class NavigationHeader extends FrameLayout {

    ImageView mBack;
    TextView mTitle;
    TextView mDesc;
    OnClickListener mClickedListener = null;

    public NavigationHeader(Context context) {
        this(context, null);
    }

    public NavigationHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    public void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        View v = LayoutInflater.from(context).inflate(R.layout.navigation_header, null);


        mBack = (ImageView) v.findViewById(R.id.navigator_icon);

        mTitle = (TextView) v.findViewById(R.id.navigator_title);
        mDesc = (TextView) v.findViewById(R.id.navigator_description);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NavigationHeader);

            mTitle.setText(a.getString(R.styleable.NavigationHeader_navigator_title));
            mDesc.setText(a.getString(R.styleable.NavigationHeader_navigator_description));

            Drawable backArrow = a.getDrawable(R.styleable.NavigationHeader_back_arrow);
            if (backArrow != null) {
                mBack.setImageDrawable(backArrow);
            } else {
                mBack.setImageDrawable(new IconDrawable(getContext(),
                        MaterialIcons.md_keyboard_arrow_left).color(Color.WHITE).sizeDp(24));
            }
        }
        this.setFocusable(true);
        // Disable descendant handle focus, we are one.
        this.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        this.setFocusableInTouchMode(true);
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireClickEvent();
            }
        });
        addView(v);
    }

    private void fireClickEvent() {
        if(mClickedListener != null) {
            mClickedListener.onClick(this);
        }
    }


    public NavigationHeader back(@NonNull Drawable icon) {
        mBack.setImageDrawable(icon);
        return this;
    }


    public NavigationHeader clicked(@NonNull OnClickListener listener) {
        mClickedListener = listener;
        return this;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                fireClickEvent();
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
