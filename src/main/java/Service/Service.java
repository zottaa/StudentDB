package Service;

import Repository.Repository;

import java.io.Serializable;
import java.util.List;

public interface Service<T, ID extends Serializable> {
    public T findById(ID id);

    public List<T> findAll();

    public void save(T entity);

    public void update(T entity);

    public void delete(T entity);

    abstract class Abstract<T, ID extends Serializable> implements Service<T, ID> {
        protected final Repository<T, ID> repository;

        public Abstract(Repository<T, ID> repository) {
            this.repository = repository;
        }
        @Override
        public T findById(ID id) {
            return repository.findById(id);
        }

        @Override
        public List<T> findAll() {
            return repository.findAll();
        }

        @Override
        public void save(T entity) {
            repository.save(entity);
        }

        @Override
        public void update(T entity) {
            repository.update(entity);
        }

        @Override
        public void delete(T entity) {
            repository.delete(entity);
        }
    }
}
