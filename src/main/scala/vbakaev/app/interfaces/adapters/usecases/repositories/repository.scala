package vbakaev.app.interfaces.adapters.usecases.repositories

trait AsyncRepository[ID, A, F[_]]
    extends CreateRepositoryAsync[ID, A, F]
    with UpdateRepositoryAsync[ID, A, F]
    with ReadRepositoryAsync[ID, A, F]
    with DeleteRepositoryAsync[ID, A, F]

trait CreateRepositoryAsync[ID, A, F[_]] {
  def create(item: A): F[ID]
}

trait ReadRepositoryAsync[ID, A, F[_]] {
  def read(id: ID): F[Option[A]]
}

trait UpdateRepositoryAsync[ID, A, F[_]] {
  def update(id: ID, item: A): F[Option[A]]
}

trait DeleteRepositoryAsync[ID, A, F[_]] {
  def delete(id: ID): F[Option[A]]
}
