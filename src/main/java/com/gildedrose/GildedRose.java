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
        for (Item item : items) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                logger.debug("NS: " + item.name + " " + item.sellIn + " " + item.quality);
                SellIn(item);
                NormalItem(item);
            }
        }
    }

    public int SellIn(Item item) {
        return item.sellIn--;
    }

    public void NormalItem(Item item) {
        if (item.name.equals("Aged Brie") || item.name.equals(concert)) {
            item.quality = QualityUp(item);
            logger.debug("UP: " + item.name + " " + item.sellIn + " " + item.quality);
        } else if (item.name.equals("Aging Red Wine")) {
            if (item.sellIn < -100) {
                if (item.quality > 0) {
                    item.quality--;
                }
            } else if (item.sellIn < 0) {
                if(item.quality < 50) {item.quality++;}
            }
        } else {
            item.quality = QualityDown(item);
            logger.debug("DW: " + item.name + " " + item.sellIn + " " + item.quality);
        }
    }

    public int QualityUp(Item item) {
        if ((item.name.equals(concert)) && (item.sellIn < 0)) {
            return item.quality = 0;
        }
        if (item.quality < 50) {
            item.quality = WhenQualityUnderFifty(item);
        }
        return item.quality;
    }

    public int QualityDown(Item item) {
        if (item.quality > 0) {
            item.quality--;
            if (item.sellIn < 0 && item.quality > 0) {
                item.quality--;
            }
            if (item.name.contains("Conjured")  && item.quality > 0) {
                item.quality = ConjuredThings(item);
            }
        }
        return item.quality;
    }

    public int WhenQualityUnderFifty(Item item) {
        item.quality++;
        if (item.name.equals(concert)) {
            item.quality = DayConcert(item);
        } else if (item.sellIn < 0) {
            item.quality++;
        }
        return item.quality;
    }

    public int DayConcert(Item item) {
        if (item.sellIn < 11 && item.quality < 50) {
            item.quality++;
        }
        if (item.sellIn < 6 && item.quality < 50) {
            item.quality++;
        }
        return item.quality;
    }

    public int ConjuredThings(Item item) {
        item.quality--;
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
        return item.quality;
    }

    public Item[] getItems() {
        return items;
    }
}
