package com.imrainbow.popularmovies.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bin Li on 2015/9/13.
 */
public class GreenDaoHelper {

    private static final String DATABASE_FILE_NAME = "popular-movies-db";
    private static GreenDaoHelper instance;
    private DaoSession daoSession;

    public static synchronized GreenDaoHelper getInstance(Context context){
        if (instance == null){
            instance = new GreenDaoHelper(context);
        }
        return instance;
    }

    public GreenDaoHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public FavoriteDao getFavoriteDao() {
        return daoSession.getFavoriteDao();
    }
}
