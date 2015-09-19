package com.imrainbow.popularmovies.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by vclub on 15/9/19.
 */
public class DensityUtils {

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
