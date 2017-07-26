package com.example.lyy.postagereminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gjiazhe.multichoicescirclebutton.MultiChoicesCircleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_chooseItem();

    }


    private void init_chooseItem() {
        MultiChoicesCircleButton.Item item1 = new MultiChoicesCircleButton.Item("Video", getResources().getDrawable(R.drawable.ic_video), 30);

        MultiChoicesCircleButton.Item item2 = new MultiChoicesCircleButton.Item("Home", getResources().getDrawable(R.drawable.ic_home), 90);

        MultiChoicesCircleButton.Item item3 = new MultiChoicesCircleButton.Item("Game", getResources().getDrawable(R.drawable.ic_game), 150);

        List<MultiChoicesCircleButton.Item> buttonItems = new ArrayList<>();
        buttonItems.add(item1);
        buttonItems.add(item2);
        buttonItems.add(item3);

        MultiChoicesCircleButton multiChoicesCircleButton = (MultiChoicesCircleButton) findViewById(R.id.multiChoicesCircleButton);
        multiChoicesCircleButton.setButtonItems(buttonItems);

        multiChoicesCircleButton.setOnSelectedItemListener(new MultiChoicesCircleButton.OnSelectedItemListener() {
            @Override
            public void onSelected(MultiChoicesCircleButton.Item item, int index) {
                // Do something
                switch (item.getText()) {
                    case "Video":
                        Intent intent1 = new Intent(MainActivity.this, VideoActivity.class);
                        startActivity(intent1);
                        break;
                    case "Home":
                        Intent intent2 = new Intent(MainActivity.this, InfoActivity.class);
                        startActivity(intent2);
                        break;
                    case "Game":
                        Intent intent3 = new Intent(MainActivity.this, GameActivity.class);
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
            }
        });
    }


}
