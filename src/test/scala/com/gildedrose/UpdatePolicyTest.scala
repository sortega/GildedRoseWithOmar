package com.gildedrose

import org.scalatest.{ShouldMatchers, FlatSpec}

class UpdatePolicyTest extends FlatSpec with ShouldMatchers {

  "The update policy" should "detect Sulfuras items" in {
    UpdatePolicy.policyFor(new Item("Sulfuras, Hand of Ragnaros", 1, 80)) shouldBe
      UpdatePolicy.Sulfuras
  }
}
