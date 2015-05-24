package com.gildedrose

import org.scalatest.{FlatSpec, ShouldMatchers}

class ConjuredUpdatePolicyTest extends FlatSpec with ShouldMatchers {

  "The conjured update policy" should "decrease quality twice as fast" in {
    val item = new Item("Conjured item", 1, 10)
    UpdatePolicy.Conjured.update(item)
    item.name shouldBe "Conjured item"
    item.quality shouldBe 8
    item.sellIn shouldBe 0
  }

  it should "decrease quality twice as fast after the sell-in date" in {
    val item = new Item("Conjured item", -1, 10)
    UpdatePolicy.Conjured.update(item)
    item.quality shouldBe 6
    item.sellIn shouldBe -2
  }
}
