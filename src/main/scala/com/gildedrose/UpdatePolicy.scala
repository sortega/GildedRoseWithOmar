package com.gildedrose

import scala.util.matching.Regex

sealed trait UpdatePolicy {
  def appliesTo(item: Item): Boolean
  def update(item: Item): Unit
}

object UpdatePolicy {

  def policyFor(item: Item): UpdatePolicy =
    Seq(Sulfuras, AgedBrie, BackstagePass, Conjured)
      .find(_.appliesTo(item))
      .getOrElse(Normal)

  trait MatchByName extends UpdatePolicy {
    val name: String
    override def appliesTo(item: Item) = item.name == name
  }

  trait MatchByPattern extends UpdatePolicy {
    val pattern: Regex
    override def appliesTo(item: Item) = pattern.unapplySeq(item.name).isDefined
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

  case object AgedBrie extends AbstractUpdatePolicy with MatchByName {
    override val name = "Aged Brie"
    override protected def age(item: Item): Unit = {
      enhance(item)
    }
  }

  case object Sulfuras extends UpdatePolicy with MatchByName {
    override val name = "Sulfuras, Hand of Ragnaros"
    override def update(item: Item): Unit = {
      // Sulfuras nor degrades nor has to be sold
    }
  }

  case object BackstagePass extends AbstractUpdatePolicy with MatchByPattern {
    override val pattern = "Backstage passes to a (.*) concert".r

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

  case object Conjured extends AbstractUpdatePolicy with MatchByPattern {
    override val pattern = "Conjured (.*)".r

    override protected def degrade(item: Item): Unit = {
      super.degrade(item)
      super.degrade(item)
    }
  }

  case object Normal extends AbstractUpdatePolicy {
    override def appliesTo(item: Item) = true
  }
}
