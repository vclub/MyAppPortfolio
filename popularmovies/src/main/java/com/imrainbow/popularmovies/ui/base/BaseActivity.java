package com.imrainbow.popularmovies.ui.base;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.imrainbow.popularmovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vclub on 15/7/24.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        setupToolbar();
    }

    protected void setupToolbar(){
        if (mToolbar!=null){
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
