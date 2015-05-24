package com.gildedrose

sealed trait UpdatePolicy {
  def update(item: Item): Unit
}

object UpdatePolicy {

  def policyFor(item: Item): UpdatePolicy = item.name match {
    case UpdatePolicy.Sulfuras.Name => Sulfuras
    case UpdatePolicy.AgedBrie.Name => AgedBrie
    case _ => Default
  }

  abstract class AbstractUpdatePolicy extends UpdatePolicy {
    override def update(item: Item): Unit = {
      if (!item.name.equals("Aged Brie")
        && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        if (item.quality > 0) {
          item.quality = item.quality - 1
        }
      } else {
        if (item.quality < 50) {
          item.quality = item.quality + 1

          if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11) {
              if (item.quality < 50) {
                item.quality = item.quality + 1
              }
            }

            if (item.sellIn < 6) {
              if (item.quality < 50) {
                item.quality = item.quality + 1
              }
            }
          }
        }
      }

      item.sellIn = item.sellIn - 1

      if (item.sellIn < 0) {
        if (!item.name.equals("Aged Brie")) {
          if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
              item.quality = item.quality - 1
            }
          } else {
            item.quality = item.quality - item.quality
          }
        } else {
          if (item.quality < 50) {
            item.quality = item.quality + 1
          }
        }
      }
    }
  }

  case object AgedBrie extends AbstractUpdatePolicy {
    val Name = "Aged Brie"
  }

  case object Sulfuras extends UpdatePolicy {
    val Name = "Sulfuras, Hand of Ragnaros"

    override def update(item: Item): Unit = {
      // Sulfuras nor degrades nor has to be sold
    }
  }

  case object Default extends AbstractUpdatePolicy
}
