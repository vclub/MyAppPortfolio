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
        Entity subscriptionItem = schema.addEntity("Favorite");
        subscriptionItem.addStringProperty("uid").primaryKey();
        subscriptionItem.addStringProperty("uname");
        subscriptionItem.addStringProperty("nickname");
        subscriptionItem.addStringProperty("mobile");
        subscriptionItem.addStringProperty("gender");
        subscriptionItem.addStringProperty("location");
        subscriptionItem.addStringProperty("avatar");
    }

}
