package vbakaev.app.interfaces.adapters.usecases.repositories

import scala.concurrent.Future

trait Repository[A, ID]
    extends CreateRepository[A]
    with UpdateRepository[A]
    with ReadRepository[A, ID]
    with DeleteRepository[A, ID]

trait CreateRepository[A] {
  def create(item: A): Future[A]
}

trait ReadRepository[A, ID] {
  def read(id: ID): Future[Option[A]]
}

trait UpdateRepository[A] {
  def update(item: A): Future[Option[A]]
}

trait DeleteRepository[A, ID] {
  def delete(id: ID): Future[Option[A]]
}
