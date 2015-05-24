package com.gildedrose

sealed trait UpdatePolicy {
  def update(item: Item): Unit
}

object UpdatePolicy {

  def policyFor(item: Item): UpdatePolicy = item.name match {
    case UpdatePolicy.Sulfuras.Name => Sulfuras
    case UpdatePolicy.AgedBrie.Name => AgedBrie
    case UpdatePolicy.BackstagePass.NamePattern(_) => BackstagePass
    case _ => Normal
  }

  abstract class AbstractUpdatePolicy extends UpdatePolicy {
    override def update(item: Item): Unit = {
      age(item)
      item.sellIn = item.sellIn - 1
      if (item.sellIn < 0) {
        age(item)
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

  case object BackstagePass extends AbstractUpdatePolicy {
    val NamePattern = "Backstage passes to a (.*) concert".r

    protected override def age(item: Item): Unit = {
      if (item.sellIn >= 0) makeMoreDemanded(item)
      else makeWorthless(item)
    }

    private def makeMoreDemanded(item: Item): Unit = {
      enhance(item)
      if (item.sellIn < 11) enhance(item)
      if (item.sellIn < 6) enhance(item)
    }

    private def makeWorthless(item: Item): Unit = {
      item.quality = 0
    }
  }

  case object Normal extends AbstractUpdatePolicy
}
