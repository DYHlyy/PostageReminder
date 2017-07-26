package com.example.lyy.postagereminder;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final long RIPPLE_DURATION = 250;

    private Toolbar toolbar;
    private ImageView contentHamburger;
    private FrameLayout root;

    private TextView myInfo;

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

        init();
        init_guillotineMenu();

        initPostages();

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
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
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
                        initPostages();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initPostages() {
        postageList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(postages.length);
            postageList.add(postages[index]);
        }
    }

    private void init() {
        root = (FrameLayout) findViewById(R.id.root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        contentHamburger = (ImageView) findViewById(R.id.content_hamburger);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
    }

    private void init_guillotineMenu() {
        //弹出的菜单
        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);

        myInfo = (TextView) findViewById(R.id.myInfo);
        myInfo.setOnClickListener(this);

        // 添加弹出的菜单
        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myInfo:
                Toast.makeText(this, "S", Toast.LENGTH_SHORT).show();
        }
    }
}
