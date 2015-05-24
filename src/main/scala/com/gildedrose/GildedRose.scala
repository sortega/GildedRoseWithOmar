package com.gildedrose

class GildedRose(val items: Array[Item]) {
  def updateQuality(): Unit = {
    items.foreach(QualityUpdater.apply)
  }
}
