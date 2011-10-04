package com.mmuca.market;

import distributions.IDistribution;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GoodsGeneratorTest {

    public static final int MINIMUM_GOODS_PER_LEVEL = 2;
    public static final int NUMBER_OF_GOODS = 20;
    private MarketLevel level_1;
    private MarketLevel level_2;
    private MarketLevel level_3;
    private ArrayList<MarketLevel> levels;

    @Before
    public void setUp(){
        level_1 = new MarketLevel();
        level_2 = new MarketLevel();
        level_3 = new MarketLevel();
        levels = new ArrayList<MarketLevel>();
        levels.add(level_1);
        levels.add(level_2);
        levels.add(level_3);
        ValueRange range = new ValueRange(0,2);
        IDistribution goodLevelDistribution = new StubDistribution(range);
        GoodsGenerator generator= new GoodsGenerator(goodLevelDistribution, NUMBER_OF_GOODS, MINIMUM_GOODS_PER_LEVEL);
        generator.populate(levels);
    }

    @Test
    public void numberOfGeneratedGoods(){
        assertEquals("total number should be equal to requested", NUMBER_OF_GOODS, level_1.getGoods().size() + level_2.getGoods().size() + level_3.getGoods().size());
        assertTrue("Minimum requirement should be fulfilled", level_1.getGoods().size() >= MINIMUM_GOODS_PER_LEVEL);
        assertTrue("Minimum requirement should be fulfilled", level_2.getGoods().size() >= MINIMUM_GOODS_PER_LEVEL);
        assertTrue("Minimum requirement should be fulfilled", level_3.getGoods().size() >= MINIMUM_GOODS_PER_LEVEL);
    }
    
    @Test
    public void goodsAreUnique(){
        HashSet<Good> goods = new HashSet<Good>();
        goods.addAll(level_1.getGoods());
        goods.addAll(level_2.getGoods());
        goods.addAll(level_3.getGoods());
        assertEquals("number of unique goods should be equal to requested number of goods", NUMBER_OF_GOODS,goods.size());
    }
    
    

}
