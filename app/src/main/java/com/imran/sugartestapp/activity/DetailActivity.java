package com.imran.sugartestapp.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.imran.sugartestapp.R;
import com.imran.sugartestapp.utils.Utils;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    private ImageView mPosterImageView;
    private TextView mTitle, mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setUpActionBar();
        getWidgets();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String image = bundle.getString(getString(R.string.image));
            String title = bundle.getString(getString(R.string.title));
            String description = bundle.getString(getString(R.string.description));
            updateData(image, title, description);
        }
    }

    private void getWidgets() {
        mPosterImageView = findViewById(R.id.poster_image);
        mTitle = findViewById(R.id.title);
        mDescription = findViewById(R.id.description);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);
    }

    public void updateData(String image, String title, String description) {
        Picasso.get().load(image)
                .placeholder(Utils.setplaceholder(2))
                .into(mPosterImageView);
        mTitle.setText(title);
        mDescription.setText(Html.fromHtml(description));
    }

    protected void setUpActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_title));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
