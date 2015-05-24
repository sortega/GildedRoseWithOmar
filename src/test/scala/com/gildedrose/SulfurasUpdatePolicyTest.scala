package com.gildedrose

import org.scalatest.{ShouldMatchers, FlatSpec}

class SulfurasUpdatePolicyTest extends FlatSpec with ShouldMatchers {

  "The sulfuras update policy" should "left quality unchanged" in {
    val item = new Item(UpdatePolicy.Sulfuras.Name, 10, 80)
    UpdatePolicy.Sulfuras.update(item)
    item.name shouldBe UpdatePolicy.Sulfuras.Name
    item.quality shouldBe 80
    item.sellIn shouldBe 10
  }
}
