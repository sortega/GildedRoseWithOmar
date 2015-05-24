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
      if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        age(item)
      } else {
        enhance(item)

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
          if (item.sellIn < 11) {
            enhance(item)
          }

          if (item.sellIn < 6) {
            enhance(item)
          }
        }
      }

      item.sellIn = item.sellIn - 1

      if (item.sellIn < 0) {
        if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
          age(item)
        } else {
          item.quality = item.quality - item.quality
        }
      }
    }

    protected def age(item: Item): Unit = {
      degrade(item)
    }

    protected def degrade(item: Item): Unit = {
      if (item.quality > 0) {
        item.quality = item.quality - 1
      }
    }

    protected def enhance(item: Item): Unit = {
      if (item.quality < 50) {
        item.quality = item.quality + 1
      }
    }
  }

  case object AgedBrie extends AbstractUpdatePolicy {
    val Name = "Aged Brie"

    override protected def age(item: Item): Unit = {
      enhance(item)
    }
  }

  case object Sulfuras extends UpdatePolicy {
    val Name = "Sulfuras, Hand of Ragnaros"

    override def update(item: Item): Unit = {
      // Sulfuras nor degrades nor has to be sold
    }
  }

  case object Default extends AbstractUpdatePolicy
}
