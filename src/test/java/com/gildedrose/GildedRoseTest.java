package com.gildedrose;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


public class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name).isEqualTo("foo");
    }
    @Test
    void cheese() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(1);
    }
    @Test
    void concert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isNotZero();
    }



    @Test
    void foo_decrease_Quality_All_Day() {
        Item[] items = new Item[] { new Item("foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    void foo_date_Has_Passed_Quality_Degrade_Twice() {
        Item[] items = new Item[] { new Item("foo", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(8);
    }

    @Test
    void foo_date_Lasr_Day_Quality_Degrade_Once() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(9);
        assertThat(app.items[0].sellIn).isEqualTo(-1);
    }

    @Test
    void foo_date_Has_Passed_And_Quality_Never_Negativ() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void foo_Quality_Never_Greater_Than_50() {
        Item[] items = new Item[] { new Item("foo", 10,  50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isLessThanOrEqualTo(50);
    }

    @Test
    void foo_Not_Increase_Quality_By_Two_While_Sellin_Under_Or_Less_10() {
        Item[] items = new Item[] { new Item("foo", 10,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(29);
    }


    @Test
    void foo_Not_Increase_Quality_By_Three_While_Sellin_Under_Or_Less_5() {
        Item[] items = new Item[] { new Item("foo", 5,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(29);
    }

    //Named Items


    @Test
    void sulfuras_Never_Change_Quality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(30);
    }


    @Test
    void Backstage_Quality_Never_Greater_Than_50() {
        Item[] items = new Item[] { new Item("Backstage passes", 10,  50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isLessThanOrEqualTo(50);
    }


    @Test
    void backstage_Increase_Quality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 20,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(31);
    }

    // 10 days less increase Value
    @Test
    void backstage_Increase_Quality_By_Two_While_Sellin_Under_Or_Less_10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(32);
    }

    // 5 days less increase Value
    @Test
    void backstage_Increase_Quality_By_Three_While_Sellin_Under_Or_Less_5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(33);
    }

    @Test
    void backstage_Drop_Quality_To_zero_if_Sellin_Less_Zero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }


    @Test
    void brie_Increase_Quality() {
        Item[] items = new Item[] { new Item("Aged Brie", 20,  10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(11);
    }



    @Test
    void brie_Not_Increase_Quality_By_Two_While_Sellin_Under_Or_Less_10() {
        Item[] items = new Item[] { new Item("Aged Brie", 10,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(31);
    }


    @Test
    void brie_Not_Increase_Quality_By_Three_While_Sellin_Under_Or_Less_5() {
        Item[] items = new Item[] { new Item("Aged Brie", 5,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(31);
    }

    @Test
    void Conjured_Decrease_Quality_By_Two() {
        Item[] items = new Item[] { new Item("Conjured infact", 5,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(28);
    }

    @Test
    void Conjured_Decrease_Quality_to_zero() {
        Item[] items = new Item[] { new Item("Conjured infact", 0,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(28);
    }

    @Test
    void Like_Conjured_Decrease_Quality_by_ONe() {
        Item[] items = new Item[] { new Item("Like Conjured infact", 5,  30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(29);
    }

    @Test
    void qualityBounded_0_to_50() {
        Item[] items = new Item[] { new Item("Conjured infact", 10,  30) };
        GildedRose app = new GildedRose(items);

        assertThat(app.qualityBounded(30,-2)).isEqualTo(28);
        assertThat(app.qualityBounded(33,-2)).isEqualTo(31);
    }

    @Test
    void qualityBounded_50() {
        Item[] items = new Item[] { new Item("Conjured infact", 10,  55) };
        GildedRose app = new GildedRose(items);
        assertThat(app.qualityBounded(55,-2)).isEqualTo(50);
    }

}