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

  it should "detect conjured items" in {
    UpdatePolicy.policyFor(new Item("Conjured +5 Dexterity Vest", 4, 15)) shouldBe
      UpdatePolicy.Conjured
    UpdatePolicy.policyFor(new Item("Conjured shield", 4, 15)) shouldBe UpdatePolicy.Conjured
  }

  it should "detect normal items" in {
    UpdatePolicy.policyFor(new Item("+5 Dexterity Vest", 4, 15)) shouldBe UpdatePolicy.Normal
    UpdatePolicy.policyFor(new Item("Normal item", 4, 15)) shouldBe UpdatePolicy.Normal
  }
}
