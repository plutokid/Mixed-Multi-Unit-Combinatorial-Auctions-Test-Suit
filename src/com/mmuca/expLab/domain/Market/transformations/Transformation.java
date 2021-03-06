package com.mmuca.expLab.domain.Market.transformations;

import com.mmuca.expLab.domain.Market.collections.GoodBundlesSet;
import com.mmuca.expLab.domain.Market.goods.bundles.GoodBundle;

public class Transformation implements Cloneable{
    GoodBundlesSet input;
    GoodBundlesSet output;

    public Transformation(){
        input=new GoodBundlesSet();
        output=new GoodBundlesSet();
    }

    public void addInput(GoodBundle bundle) {
        input.add(bundle);
    }

    public void addAllInput(GoodBundlesSet bundles) {
        for (GoodBundle bundle : bundles) addInput(bundle);
    }

    public GoodBundlesSet getInput() {
        return input;
    }

    public void addOutput(GoodBundle bundle) {
        output.add(bundle);
    }

    public void addAllOutput(GoodBundlesSet bundles) {
        for (GoodBundle bundle : bundles) addOutput(bundle);
    }

    public GoodBundlesSet getOutput() {
        return output;
    }

    public Transformation clone(){
       try{
        return (Transformation) super.clone();
       }
       catch (CloneNotSupportedException e){
           throw new InternalError();
       }
    }

}
