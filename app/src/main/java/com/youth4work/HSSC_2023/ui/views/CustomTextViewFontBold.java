package com.youth4work.HSSC_2023.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextViewFontBold extends TextView {

    public CustomTextViewFontBold(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewFontBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewFontBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Bold.ttf");
            setTypeface(tf);
        }
    }

}