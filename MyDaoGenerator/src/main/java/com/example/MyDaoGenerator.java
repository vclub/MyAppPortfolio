package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception{
        Schema schema = new Schema(1000, "com.imrainbow.popularmovies.dao");
        addFavorite(schema);
        new DaoGenerator().generateAll(schema, args[0]);
    }

    private static void addFavorite(Schema schema){
        Entity subscriptionItem = schema.addEntity("MovieEntity");
        subscriptionItem.setHasKeepSections(true);
        subscriptionItem.addLongProperty("id").primaryKey();
        subscriptionItem.addStringProperty("title");
        subscriptionItem.addStringProperty("release_date");
        subscriptionItem.addFloatProperty("vote_average");
        subscriptionItem.addStringProperty("overview");
        subscriptionItem.addStringProperty("poster_path");
    }

}
