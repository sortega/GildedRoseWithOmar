package com.gildedrose

class Item(val name: String, var sellIn: Int, var quality: Int) {

  override def equals(obj: scala.Any): Boolean = obj match {
    case other: Item =>
      this.name == other.name && this.sellIn == other.sellIn && this.quality == other.quality
    case _ => false
  }

  override def toString: String = s"Item($name, $sellIn, $quality)"
}
