package com.gildedrose

object QualityUpdater {
  def apply(item: Item): Unit = UpdatePolicy.policyFor(item).update(item)
}
