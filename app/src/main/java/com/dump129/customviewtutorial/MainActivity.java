package com.dump129.customviewtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements InfoCardView.InfoClickListener{
    private InfoCardView infoCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        setupView();
    }

    private void bindView() {
        infoCardView = (InfoCardView) findViewById(R.id.infoCarView);
    }

    private void setupView() {
        infoCardView.setTitle(getString(R.string.nougat_title));
        infoCardView.setContent(getString(R.string.nougat_content));
        infoCardView.setInfoClickListener(this);
    }

    @Override
    public void onTitleClick() {
        Toast.makeText(MainActivity.this, "" + infoCardView.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContentClick() {
        Toast.makeText(MainActivity.this, "" + infoCardView.getContent(), Toast.LENGTH_SHORT).show();
    }
}
