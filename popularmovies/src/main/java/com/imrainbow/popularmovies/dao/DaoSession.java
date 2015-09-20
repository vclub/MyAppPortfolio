package com.imrainbow.popularmovies.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig movieEntityDaoConfig;

    private final MovieEntityDao movieEntityDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        movieEntityDaoConfig = daoConfigMap.get(MovieEntityDao.class).clone();
        movieEntityDaoConfig.initIdentityScope(type);

        movieEntityDao = new MovieEntityDao(movieEntityDaoConfig, this);

        registerDao(MovieEntity.class, movieEntityDao);
    }
    
    public void clear() {
        movieEntityDaoConfig.getIdentityScope().clear();
    }

    public MovieEntityDao getMovieEntityDao() {
        return movieEntityDao;
    }

}
