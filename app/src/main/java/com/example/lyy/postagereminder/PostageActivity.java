package com.example.lyy.postagereminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yy.www.libs.TransitionManager;
import com.yy.www.libs.helper.TransitionSingleHelper;

public class PostageActivity extends AppCompatActivity {

    public static final String POSTAGE_NAME = "postage_name";

    public static final String POSTAGE_IMAGE_ID = "postage_image_id";

    TransitionSingleHelper t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postage);

        Intent intent = getIntent();
        String postageName = intent.getStringExtra(POSTAGE_NAME);
        final int postageImageId = intent.getIntExtra(POSTAGE_IMAGE_ID, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView postageImageView = (ImageView) findViewById(R.id.postage_image_view);
        TextView postageContentText = (TextView) findViewById(R.id.postage_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(postageName);
        Glide.with(this).load(postageImageId).into(postageImageView);
        String postageContent = generatePostageContent(postageName);
        postageContentText.setText(postageContent);

        t = new TransitionManager(PostageActivity.this).getSingle();
        postageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.startViewerActivity(view, postageImageId);
            }
        });

    }

    private String generatePostageContent(String postageName) {
        StringBuilder postageContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            postageContent.append(postageName);
        }
        return postageContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
