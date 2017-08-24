package com.example.lyy.postagereminder;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InfoActivity extends AppCompatActivity {

    private static final long RIPPLE_DURATION = 250;

    private Postage[] postages = {
            new Postage("Ship1", R.drawable.ship1), new Postage("Ship2", R.drawable.ship2)
            , new Postage("Ship3", R.drawable.ship3), new Postage("Ship4", R.drawable.ship4)
            , new Postage("Ship5", R.drawable.ship5), new Postage("Ship6", R.drawable.ship6)
            , new Postage("Ship7", R.drawable.ship7), new Postage("Ship8", R.drawable.ship8)
            , new Postage("Ship9", R.drawable.ship9), new Postage("Ship10", R.drawable.ship10)
    };

    private List<Postage> postageList = new ArrayList<>();
    private PostageAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initToolBar();
        initPostage();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PostageAdapter(postageList);
        recyclerView.setAdapter(adapter);

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPostage();
            }
        });
    }

    private void refreshPostage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initPostage();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initPostage() {
        postageList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(postages.length);
            postageList.add(postages[index]);
        }
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
