package com.gildedrose

import org.scalatest.{ShouldMatchers, FlatSpec}

class UpdatePolicyTest extends FlatSpec with ShouldMatchers {

  "The update policy" should "detect Sulfuras items" in {
    UpdatePolicy.policyFor(new Item(UpdatePolicy.Sulfuras.Name, 1, 80)) shouldBe
      UpdatePolicy.Sulfuras
  }

  it should "detect Aged Brie items" in {
    UpdatePolicy.policyFor(new Item(UpdatePolicy.AgedBrie.Name, 4, 15)) shouldBe
      UpdatePolicy.AgedBrie
  }

  it should "detect backstage pass items" in {
    UpdatePolicy.policyFor(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 15)) shouldBe
      UpdatePolicy.BackstagePass
    UpdatePolicy.policyFor(new Item("Backstage passes to a DefConDos concert", 4, 15)) shouldBe
      UpdatePolicy.BackstagePass
  }

}
