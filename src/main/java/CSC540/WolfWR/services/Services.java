package CSC540.WolfWR.services;

import CSC540.WolfWR.models.DomainObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class Services<T extends DomainObject, K> {

    protected abstract JpaRepository<T, K> getRepo();

    public void save(T entity) {
        getRepo().saveAndFlush(entity);
    }

    public void saveAll(Iterable<T> entities) {
        getRepo().saveAllAndFlush(entities);
    }

    public T findByID(K id) {
        Optional<T> obj = getRepo().findById( id );
        return obj.orElse(null);
    }

    public List<T> findAll() {
        return getRepo().findAll();
    }

    public void delete(T obj){getRepo().delete(obj);}

    public void deleteList(List<T> objs){ getRepo().deleteAll(objs); }
}
