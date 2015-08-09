package com.imrainbow.popularmovies.ui.base;

import android.view.MenuItem;

import com.imrainbow.popularmovies.R;

/**
 * Created by Bin Li on 2015/8/9.
 */
public abstract class BackBaseActivity extends BaseActivity {
    @Override
    protected void setupToolbar() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
