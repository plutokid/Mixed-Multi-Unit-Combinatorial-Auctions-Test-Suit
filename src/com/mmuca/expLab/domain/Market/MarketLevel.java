package com.mmuca.expLab.domain.Market;

import com.mmuca.expLab.domain.Market.goods.Good;
import com.mmuca.expLab.domain.Market.transformations.Transformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MarketLevel {
    private HashMap<Integer,Good> goods;
    private HashMap<Integer,Transformation> transformations;

    public MarketLevel(){
       goods = new HashMap<Integer,Good>();
       transformations=new HashMap<Integer,Transformation>();
    }

    public void addGood(Good newGood) {
       goods.put(goods.size(),newGood);
    }

    public Good getGood(int key) {
        //TODO Fast fail
        return goods.get(key);
    }

    public ArrayList<Good> getAllGoods() {
        return new ArrayList<Good>(goods.values());
    }

    public void addTransformation(Transformation transformation) {
       transformations.put(transformations.size(),transformation);
    }

    public Transformation getTransformation(int key) {
        //TODO Fast fail
        return transformations.get(key).clone();
    }

    public Collection<Transformation> getAllTransformations() {
        return transformations.values();
    }
}