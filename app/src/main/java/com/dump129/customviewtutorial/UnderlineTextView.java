package com.dump129.customviewtutorial;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Dump129 on 1/18/2017.
 */

public class UnderlineTextView extends TextView {
    public UnderlineTextView(Context context) {
        super(context);
        setUp();
    }

    public UnderlineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public UnderlineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    @RequiresApi(21)
    public UnderlineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUp();
    }

    private void setUp() {
        setPaintFlags(getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
