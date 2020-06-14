package vbakaev.app.interfaces.repositories.pure

import scala.collection.concurrent.{Map, TrieMap}

trait Generator[ID] {
  def generateRandomId(): ID
}

class InMemoryRepository[ID: Generator, A] {
  private val map: Map[ID, A] = new TrieMap()

  def create(item: A): ID = {
    val newID = implicitly[Generator[ID]].generateRandomId()
    map.putIfAbsent(newID, item) match {
      case None       => newID
      case Some(item) => throw new IllegalStateException(s"ID collision on $newID for $item")
    }
  }

  def delete(id: ID): Option[A] = map.remove(id)

  def read(id: ID): Option[A] = map.get(id)

  def update(id: ID, item: A): Option[A] = map.replace(id, item)
}
