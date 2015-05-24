package com.gildedrose

import org.scalatest.{FlatSpec, ShouldMatchers}

class NormalUpdatePolicyTest extends FlatSpec with ShouldMatchers {

  "The normal update policy" should "decrease quality as time goes" in {
    val item = new Item("Normal item", 5, 10)
    UpdatePolicy.Normal.update(item)
    item.name shouldBe "Normal item"
    item.quality shouldBe 9
    item.sellIn shouldBe 4
  }

  it should "decrease quality twice as fast after the sell-in date" in {
    val item = new Item("Normal item", 0, 10)
    UpdatePolicy.Normal.update(item)
    item.quality shouldBe 8
    item.sellIn shouldBe -1
  }

  it should "never have negative quality" in {
    val item = new Item("Normal item", 20, 10)
    for (_ <- 1 to 15) {
      UpdatePolicy.Normal.update(item)
    }
    item.quality shouldBe 0
  }
}
