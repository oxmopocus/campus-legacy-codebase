package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {
    final static Logger logger = LoggerFactory.getLogger(GildedRose.class);
    Item[] items;
    String concert = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        logger.info("Item : " + items[i].name + " | Quality : " + items[i].quality );
                        items[i].quality = items[i].quality - 1;
                        logger.info("L'item perd 1 de quality");
                        logger.info("Item : " + items[i].name + " | Quality : " + items[i].quality );
                        logger.info("--------------------------------------------------------------");
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                    logger.info("L'item " + items[i].name + " prend " + items[i].quality + " de qualité");

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
/*
    private void decreaseQuality(Item item){
        item.quality --;
        if (item.quality <= 0) item.quality = 0;
    }

    private void increaseQuality(Item item){
        if (item.quality <= 0) item.quality = 0;
        item.quality ++;
        if (item.quality >= 50) item.quality = 50;
    }

    private boolean isConjured(Item item){
        return item.name.contains("Conjured");
    }

    private boolean isCheese(Item item){
        return item.name.contains("Aged") || item.name.contains("Brie");
    }

    private boolean isBackstage(Item item){
        return item.name.contains("Backstage") || item.name.contains("pass") || item.name.contains("concert");
    }

    private boolean isLegendary(Item item){
        return item.name.contains("Legend") || item.name.contains("Sulfuras") || item.name.contains("Ragnaros");
    }

*/

    public Item[] getItems() {
        return items;
    }
}