package com.dump129.customviewtutorial;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Dump129 on 1/18/2017.
 */

public class InfoCardView extends FrameLayout implements View.OnClickListener {
    private TextView tvTitle, tvContent;
    private InfoClickListener infoClickListener;
    private String title;
    private String content;

    public InfoCardView(@NonNull Context context) {
        super(context);
        setUp(null);
    }

    public InfoCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp(attrs);
    }

    public InfoCardView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(attrs);
    }

    @RequiresApi(21)
    public InfoCardView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUp(attrs);
    }

    private void setUp(AttributeSet attrs) {
        inflate(getContext(), R.layout.widget_info_card, this); // set layout xml on this FrameLayout
        bindView();
        setupStyleable(attrs);
        setupView();
    }

    private void setupStyleable(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InfoCardView);
            title = typedArray.getString(R.styleable.InfoCardView_icv_title);
            content = typedArray.getString(R.styleable.InfoCardView_icv_content);
            typedArray.recycle();
        }
    }

    private void setupView() {
        tvTitle.setOnClickListener(this);
        tvContent.setOnClickListener(this);
        setTitle(title);
        setContent(content);
    }

    private void bindView() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    public void setTitle(String title) {
        this.title = title;
        tvTitle.setText(title);
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String content) {
        this.content = content;
        tvContent.setText(content);
    }

    public String getContent() {
        return this.content;
    }

    public void setInfoClickListener(InfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @Override
    public void onClick(View view) {
        if (view == tvTitle) {
            onTitleClick();
        } else {
            onContentClick();
        }
    }

    private void onTitleClick() {
        if (infoClickListener != null) {
            infoClickListener.onTitleClick();
        }
    }

    private void onContentClick() {
        if (infoClickListener != null) {
            infoClickListener.onContentClick();
        }
    }

    public interface InfoClickListener {
        void onTitleClick();

        void onContentClick();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.title = this.title;
        savedState.content = this.content;
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(((SavedState) state).getSuperState());
        this.title = savedState.title;
        this.content = savedState.content;
        setTitle(title);
        setContent(content);
    }

    // Save/Restore Instance State in Custom View
    private static class SavedState extends BaseSavedState {
        String title, content;
        boolean isEnabled;


        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel in) {
            super(in);
            this.title = in.readString();
            this.content = in.readString();

            // For boolean
            this.isEnabled = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.title);
            out.writeString(this.content);

            // For boolean
            out.writeByte((byte) (isEnabled ? 1 : 0)); //0 is false, 1 is true
        }

        public static final Creator CREATOR = new Creator() {
            @Override
            public Object createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public Object[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
