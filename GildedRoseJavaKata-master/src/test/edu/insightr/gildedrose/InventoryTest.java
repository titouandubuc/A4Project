package edu.insightr.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    @Test
    void updateQuality() {
        //item("+5 Dexterity Vest", sellIn=9, quality=19),

        //SETUP
        Inventory testInventory = new Inventory();
        Item vest = testInventory.getItems()[0];
        Item vieuxbrie = testInventory.getItems()[1];
        Item elixir = testInventory.getItems()[2];
        Item sulfuras = testInventory.getItems()[3];
        Item backstage = testInventory.getItems()[4];
        Item cake = testInventory.getItems()[5];
        assertEquals("+5 Dexterity Vest", vest.getName());
        assertEquals(20, vest.getQuality());

        assertEquals("Aged Brie", vieuxbrie.getName());
        assertEquals(0, vieuxbrie.getQuality());


        assertEquals("Elixir of the Mongoose", elixir.getName());
        assertEquals(7, elixir.getQuality());

        assertEquals("Sulfuras, Hand of Ragnaros", sulfuras.getName());
        assertEquals(80, sulfuras.getQuality());

        assertEquals("Backstage passes to a TAFKAL80ETC concert", backstage.getName());
        assertEquals(20, backstage.getQuality());

        assertEquals("Conjured Mana Cake", cake.getName());
        assertEquals(6, cake.getQuality());

        //ACTION
        testInventory.updateQuality();

        //TEST
        assertEquals(19, vest.getQuality());
        assertEquals(1, vieuxbrie.getQuality());
        assertEquals(6, elixir.getQuality());
        assertEquals(80, sulfuras.getQuality());
        assertEquals(21,backstage.getQuality());
        assertEquals(4, cake.getQuality());

        // Here we are testing the quality of each item in the inventory after the first day
        testInventory.updateQuality();

        assertEquals(2, vieuxbrie.getQuality());
        assertEquals(0, vieuxbrie.getSellIn());
        testInventory.updateQuality();
        assertEquals(4, vieuxbrie.getQuality());
        assertEquals(-1, vieuxbrie.getSellIn());

        //on verifie que le brie augmente bien de 2 en 2 après le premier jour (car son sellIn est a 0)

        // Test de la qualite de la place de concert
        for(int j=0;j<15;j++)
        {
            int oldQuality= backstage.getQuality();
            testInventory.updateQuality();
            if(backstage.getSellIn()>10)
            {
                assertEquals(oldQuality+1,backstage.getQuality());
            }
            if(backstage.getSellIn()<10&&backstage.getSellIn()>=5)
            {
                assertEquals(oldQuality+2,backstage.getQuality());
            }
            if(backstage.getSellIn()<5&&backstage.getSellIn()>=0)
            {
                assertEquals(oldQuality+3,backstage.getQuality());
            }
            if(backstage.getSellIn()<0)
            {
                assertEquals(0,backstage.getQuality());
            }
        }
        // En reappliquant les regles de l'evolution de la qualite on peux la tester sur 20jours(duree suffisante pour que la qualite retombe a 0)

    }



}