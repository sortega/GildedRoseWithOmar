package com.gildedrose

import org.scalatest.{FlatSpec, ShouldMatchers}

class AgedBrieUpdatePolicyTest extends FlatSpec with ShouldMatchers {

   "The aged brie update policy" should "increase quality as time goes" in {
     val item = new Item(UpdatePolicy.AgedBrie.Name, 10, 15)
     UpdatePolicy.AgedBrie.update(item)
     item.name shouldBe UpdatePolicy.AgedBrie.Name
     item.quality shouldBe 16
     item.sellIn shouldBe 9
   }

   it should "increase quality twice as fast after the sell-in date" in {
     val item = new Item(UpdatePolicy.AgedBrie.Name, 0, 15)
     UpdatePolicy.AgedBrie.update(item)
     item.quality shouldBe 17
     item.sellIn shouldBe -1
   }
 }
