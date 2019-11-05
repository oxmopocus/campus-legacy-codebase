package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

/*

for (int i = 0; i < items.length; i++) {
    logger.info("loop : " + i);

    switch (items[i].name) {
        case "Aged Brie":
            logger.info(items[i].name + " | " + items[i].quality + " | " + items[i].sellIn );
            items[i].sellIn = items[i].sellIn - 1;
            if (items[i].quality < 50) {
                logger.info("aged < 50 passed");
                logger.info("quality was : " + items[i].quality );
                items[i].quality = items[i].quality + 1;
                logger.info("quality now : " + items[i].quality );

                if (items[i].sellIn < 0){
                    logger.info("aged  selling < 0 passed");
                    if (items[i].quality < 50) {
                        logger.info("aged < 50 passed");
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
            break;
        case "Backstage passes to a TAFKAL80ETC concert":
            logger.info(items[i].name + " | " + items[i].quality + " | " + items[i].sellIn );
            if (items[i].sellIn > 10) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }

            }else if (items[i].sellIn < 6) {
                items[i].quality = items[i].quality + 3;
            }else if (items[i].sellIn < 11) {
                items[i].quality = items[i].quality + 2;
            }


            items[i].quality = items[i].quality > 50 ? 50 : items[i].quality;


            if (items[i].sellIn <= 0) {
                items[i].quality = 0;
            }
            items[i].sellIn = items[i].sellIn - 1;
            break;

        case "Sulfuras, Hand of Ragnaros":
            logger.info(items[i].name + " | " + items[i].quality + " | " + items[i].sellIn );
            // code block
            break;
        case "Conjured Mana Cake":
            logger.info("conjured data ");
            logger.info("before " +  items[i].name + " | " + items[i].quality + " | " + items[i].sellIn );
            if (items[i].quality - 2 >= 0){
                items[i].quality  = items[i].quality - 2;
            }else{
                items[i].quality  = 0;
            }

            items[i].sellIn = items[i].sellIn - 1;

            logger.info(" after " +  items[i].name + " | " + items[i].quality + " | " + items[i].sellIn );
            // code block
            break;
        default:
            logger.info(items[i].name + " | " + items[i].quality + " | " + items[i].sellIn );
            // passed
            if (items[i].sellIn <= 0){
                logger.info(" foo default ");
                if (items[i].quality - 2 >= 0 ){
                    items[i].quality = items[i].quality - 2;

                    logger.info("foo_date_Has_Passed_And_Quality_Never_Negativ  :");
                }else{

                }

            }else{
                logger.info("foo_decrease_Sellin_All_Day");
                logger.info("foo_decrease_Quality_All_Day");

                items[i].quality = items[i].quality -1;
                items[i].sellIn  = items[i].sellIn - 1;
            }
    }

    logger.info("quality now : " + items[i].quality );
}

*/

    Logger logger = LoggerFactory.getLogger(GildedRose.class);

    public void updateQuality() {
        logger.info("length : " + items.length );
        for (int i = 0; i < items.length; i++) {
            items[i] = SelectItemByName(items[i]);
        }
    }

    private Item SelectItemByName(Item item) {

    //    logger.info("before : " +  item.name +  " Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);

        if (item.name.startsWith("Conjured")){
        //    logger.info("conjured before : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);

            item.quality = qualityBounded(item.quality,-2);

         //   logger.info("conjured after : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);

        }else if (item.name.startsWith("Sulfuras") ){
            //nothing to do
         //   logger.info("i'm Sulfuras  ... doing nothing");
        }else if (item.name.contains("Brie") ){

            logger.info("Brie before : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);
            item.quality = qualityBounded(item.quality,1);
            logger.info("brie after : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);

        }else if (item.name.contains("Backstage passes") ){

            // date dépassée
            logger.info("BackStage before : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);

            if (item.sellIn < 0){
                item.quality = qualityBounded(item.quality,-50);
            //    logger.info("< 0 conjured After : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);
            }else if (item.sellIn >= 0 && item.sellIn < 6   ){
                item.quality = qualityBounded(item.quality,3);
             //   logger.info("< 6 conjured After : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);
            }else if (item.sellIn > 5 && item.sellIn < 11){
                item.quality = qualityBounded(item.quality,2);
               // logger.info(" < 11 conjured After : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);
            }else{
                item.quality = qualityBounded(item.quality,1);
              //  logger.info("other case After : Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);
            }
            // other  unNamedItems
        }else{
            if (item.sellIn < 0){
               // logger.info(" < 0 foo before : Ql  : " + item.quality  );
                item.quality = qualityBounded(item.quality,-2);
               // logger.info("< 0  foo after : Ql  : " + item.quality  );
            }else{
                logger.info("foo before : Ql  : " + item.quality  );
                item.quality = qualityBounded(item.quality,-1);
               // logger.info("foo after : Ql  : " + item.quality  );
            }
        }

        // Sellin decrease in all cases
        if (!item.name.startsWith("Sulfuras")){
            item  = downSellin(item , 1);
        }

        //logger.info("after  : " +  item.name +  " Ql  : " + item.quality  + " | sellIn :  " + item.sellIn);
        return item;
    }
    /**
     *
     * @param currentQuality
     * @param QualityMouvment
     * @return
     */
    public int qualityBounded(int currentQuality,int QualityMouvment ){
        int maxQuality = 50;
        int minQuality = 0;
        int tempQuality = currentQuality + QualityMouvment;

        if (tempQuality > maxQuality) { tempQuality = maxQuality; }
        if (tempQuality < minQuality) { tempQuality = minQuality; }

        return tempQuality;
    }


    private Item downSellin(Item item ,int num){
        item.sellIn = item.sellIn - num;
        return item;
    }



    public Item[] getItems() {
        return items;
    }
}