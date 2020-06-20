package vbakaev.app.interfaces.repositories.async

import vbakaev.app.interfaces.adapters.usecases.repositories.AsyncRepository
import vbakaev.app.interfaces.repositories.pure.{Generator, InMemoryRepository}

import scala.concurrent.{ExecutionContext, Future}

class InMemoryAsyncRepository[ID: Generator, A]()(implicit ec: ExecutionContext)
    extends AsyncRepository[ID, A, Future] {

  private val inMemoryRepository = new InMemoryRepository[ID, A]()

  def create(item: A): Future[ID] = Future(inMemoryRepository.create(item))

  def delete(id: ID): Future[Option[A]] = Future(inMemoryRepository.delete(id))

  def read(id: ID): Future[Option[A]] = Future(inMemoryRepository.read(id))

  def update(id: ID, item: A): Future[Option[A]] = Future(inMemoryRepository.update(id, item))

}
