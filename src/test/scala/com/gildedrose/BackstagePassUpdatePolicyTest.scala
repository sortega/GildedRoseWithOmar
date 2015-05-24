package com.gildedrose

import org.scalatest.{FlatSpec, ShouldMatchers}

class BackstagePassUpdatePolicyTest extends FlatSpec with ShouldMatchers {

  val name = "Backstage passes to a TAFKAL80ETC concert"

  "The backstage pass update policy" should "increase quality as time goes before the event" in {
   val item = new Item(name, 20, 10)
   UpdatePolicy.BackstagePass.update(item)
   item.name shouldBe name
   item.quality shouldBe 11
   item.sellIn shouldBe 19
  }

  it should "increase quality twice as fast in the 10 days before the event" in {
   val item = new Item(name, 10, 10)
   UpdatePolicy.BackstagePass.update(item)
   item.quality shouldBe 12
   item.sellIn shouldBe 9
  }

  it should "increase quality tree times as fast in the 5 days before the event" in {
   val item = new Item(name, 5, 10)
   UpdatePolicy.BackstagePass.update(item)
   item.quality shouldBe 13
   item.sellIn shouldBe 4
  }

  it should "drop quality to nothing after the event" in {
    val item = new Item(name, 0, 10)
    UpdatePolicy.BackstagePass.update(item)
    item.quality shouldBe 0
    item.sellIn shouldBe -1
  }
}
