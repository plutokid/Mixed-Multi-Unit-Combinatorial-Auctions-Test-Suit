package com.mmuca.expLab.ui.market.design.models;

import com.mmuca.expLab.domain.Market.Market;
import com.mmuca.expLab.domain.Market.MarketDistribution;
import com.mmuca.expLab.domain.Market.MarketGenerator;
import com.mmuca.expLab.domain.Market.MarketGeneratorBuilder;
import com.mmuca.expLab.ui.market.design.views.ObserverView;

import java.util.ArrayList;
import java.util.Observable;

public class MarketModel extends Observable{
    public static final int DEFAULT_NUM_LEVELS = 6;
    public static final int DEFAULT_NUM_GOODS = 15;
    public static final int DEFAULT_MIN_GOODS_PER_LEVEL = 2;
    public static final int DEFAULT_NUM_IOT = 5;
    public static final int MINIMUM_NUM_GOODS = 1;
    public static final int MINIMUM_MIN_NUM_GOODS_PER_LEVEL = 1;
    public static final int MINIMUM_NUM_LEVELS = 3;
    public static final int MINIMUM_NUM_IOT = 0;
    public static final boolean DEFAULT_SHOW_ONLY_IOT = true;

    private MarketGenerator.Parameters generatorParameters;
    private ArrayList<ObserverView> views;
    private boolean showOnlyIOT;
    private DistributionModel goodLevelDistrModel;
    private DistributionModel inputBundlesNumDistrModel;
    private DistributionModel outputBundlesNumDistrModel;
    private DistributionModel inputBundleGoodLevelDistrModel;
    private DistributionModel outputBundleGoodLevelDistrModel;
    private DistributionModel iotLevelDistrModel;

    public MarketModel(){
        views = new ArrayList<ObserverView>();
        generatorParameters = new MarketGenerator.Parameters(DEFAULT_NUM_LEVELS, DEFAULT_NUM_GOODS, DEFAULT_MIN_GOODS_PER_LEVEL, DEFAULT_NUM_IOT);
        showOnlyIOT= DEFAULT_SHOW_ONLY_IOT;
        initChildModels();
    }

    private void initChildModels() {
        MarketDistribution.GOOD_LEVEL.setRangeEnd(DEFAULT_NUM_LEVELS);
        MarketDistribution.INPUT_BUNDLES_NUM.setRangeEnd(DEFAULT_NUM_GOODS);
        MarketDistribution.OUTPUT_BUNDLES_NUM.setRangeEnd(DEFAULT_NUM_GOODS);
        MarketDistribution.INPUT_BUNDLE_GOOD_LEVEL.setRangeEnd(DEFAULT_NUM_LEVELS);
        MarketDistribution.OUTPUT_BUNDLE_GOOD_LEVEL.setRangeEnd(DEFAULT_NUM_LEVELS);
        MarketDistribution.IOT_LEVEL.setRangeEnd(DEFAULT_NUM_LEVELS);

        goodLevelDistrModel = new DistributionModel(MarketDistribution.GOOD_LEVEL);
        inputBundlesNumDistrModel = new DistributionModel(MarketDistribution.INPUT_BUNDLES_NUM);
        outputBundlesNumDistrModel = new DistributionModel(MarketDistribution.OUTPUT_BUNDLES_NUM);
        inputBundleGoodLevelDistrModel = new DistributionModel(MarketDistribution.INPUT_BUNDLE_GOOD_LEVEL);
        outputBundleGoodLevelDistrModel = new DistributionModel(MarketDistribution.OUTPUT_BUNDLE_GOOD_LEVEL);
        iotLevelDistrModel = new DistributionModel(MarketDistribution.IOT_LEVEL);
    }

    public void addView(ObserverView view){
        views.add(view);
        goodLevelDistrModel.addView(view);
        inputBundleGoodLevelDistrModel.addView(view);
        inputBundlesNumDistrModel.addView(view);
        outputBundleGoodLevelDistrModel.addView(view);
        outputBundlesNumDistrModel.addView(view);
        iotLevelDistrModel.addView(view);
    }

    private void commitChanges(){
        startUpdateTransaction();
        updateChildModels();
        endUpdateTransaction();

    }

    private void endUpdateTransaction() {
        for(ObserverView view: views)
            view.endUpdate();
    }

    private void startUpdateTransaction() {
        for(ObserverView view: views)
            view.beginUpdate();
    }

    private void updateChildModels() {
        goodLevelDistrModel.setRangeEnd(generatorParameters.getNumLevels());
        inputBundlesNumDistrModel.setRangeEnd(generatorParameters.getNumGoods());
        outputBundlesNumDistrModel.setRangeEnd(generatorParameters.getNumGoods());
        inputBundleGoodLevelDistrModel.setRangeEnd(generatorParameters.getNumLevels());
        outputBundleGoodLevelDistrModel.setRangeEnd(generatorParameters.getNumLevels());
        iotLevelDistrModel.setRangeEnd(generatorParameters.getNumLevels());
    }

    public DistributionModel getGoodLevelDistrModel() {
        return goodLevelDistrModel;
    }

    public DistributionModel getInputBundlesNumDistrModel() {
        return inputBundlesNumDistrModel;
    }

    public DistributionModel getOutputBundlesNumDistrModel() {
        return outputBundlesNumDistrModel;
    }

    public DistributionModel getInputBundleGoodLevelDistrModel() {
        return inputBundleGoodLevelDistrModel;
    }

    public DistributionModel getOutputBundleGoodLevelDistrModel() {
        return outputBundleGoodLevelDistrModel;
    }

    public DistributionModel getIotLevelDistrModel() {
        return iotLevelDistrModel;
    }

    public boolean isShowOnlyIOT() {
        return showOnlyIOT;
    }

    public void setShowOnlyIOT(boolean showOnlyIOT) {
        this.showOnlyIOT = showOnlyIOT;
        commitChanges();
    }

    public int getNumGoods(){
       return generatorParameters.getNumGoods();
    }

    public void setNumGoods(int numGoods){
        generatorParameters.setNumGoods(numGoods);
        commitChanges();
    }

    public int getMinGoodsPerLevel(){
        return generatorParameters.getMinGoodsPerLevel();
    }

    public void setMinGoodsPerLevel(int minNumGoodsPerLevel){
        generatorParameters.setMinGoodsPerLevel(minNumGoodsPerLevel);
        commitChanges();
    }

    public int getNumLevels(){
        return generatorParameters.getNumLevels();
    }

    public void setNumLevels(int numLevels){
        generatorParameters.setNumLevels(numLevels);
        commitChanges();
    }

    public int getNumIOT(){
        return generatorParameters.getNumIOT();
    }

    public void setNumIOT(int numIOT){
        generatorParameters.setNumIOT(numIOT);
        commitChanges();
    }

    public void recreateMarket(){
        commitChanges();
    }

    public Market market(){
        return new MarketGeneratorBuilder(generatorParameters, generatorDistributions()).build().nextMarket();
    }

    private MarketGenerator.Distributions generatorDistributions() {
        return new MarketGenerator.Distributions(
                goodLevelDistrModel.getDistribution(),
                iotLevelDistrModel.getDistribution(),
                inputBundleGoodLevelDistrModel.getDistribution(),
                outputBundleGoodLevelDistrModel.getDistribution(),
                inputBundlesNumDistrModel.getDistribution(),
                outputBundlesNumDistrModel.getDistribution()
            );
    }


}
