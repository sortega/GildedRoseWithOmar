package com.gildedrose

import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, ShouldMatchers}

class GildedRoseTest extends FlatSpec with ShouldMatchers with PropertyChecks {

  val sellInDays = Gen.chooseNum[Int](-10, 10)
  val qualities = Gen.posNum[Int]
  val itemNames = Gen.oneOf(
    "Normal item",
    UpdatePolicy.AgedBrie.Name,
    UpdatePolicy.Sulfuras.Name,
    "Backstage passes to a TAFKAL80ETC concert",
    "+5 Dexterity Vest"
  )
  val items = for {
    name <- itemNames
    sellIn <- sellInDays
    quality <- qualities
  } yield new Item(name, sellIn, quality)
  val itemArrays = Gen.containerOf[Array, Item](items)
  val daysToPass = Gen.posNum[Int]

  "The refactored gilded rose" should "behave like the golden master" in {
    forAll(daysToPass, itemArrays) { (days, items) =>
      val master = new GoldenMaster(items)
      val instance = new GildedRose(items.map(copyItem))
      for (_ <- 1 to days) {
        master.updateQuality()
        instance.updateQuality()
      }
      instance.items.toList shouldBe master.items.toList
    }
  }

  def copyItem(item: Item) = new Item(item.name, item.sellIn, item.quality)
}
