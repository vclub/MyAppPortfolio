package com.imrainbow.myappportfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSpotifyStreamer).setOnClickListener(this);
        findViewById(R.id.btnScoresApp).setOnClickListener(this);
        findViewById(R.id.btnLibraryApp).setOnClickListener(this);
        findViewById(R.id.btnBuildItBigger).setOnClickListener(this);
        findViewById(R.id.btnBaconReader).setOnClickListener(this);
        findViewById(R.id.btnCapstone).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCapstone:
                Toast.makeText(this,"This button will launch my capstone app!", Toast.LENGTH_SHORT).show();
                break;
            default:
                if (v instanceof Button){
                    Toast.makeText(this, ((Button) v).getText() + " click", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
