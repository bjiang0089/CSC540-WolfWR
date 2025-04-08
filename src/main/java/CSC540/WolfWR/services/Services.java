package CSC540.WolfWR.services;

import CSC540.WolfWR.models.DomainObject;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class Services<T extends DomainObject, K> {

    public abstract JpaRepository<T, K> getRepo();

    public void save(T entity) {
        getRepo().saveAndFlush(entity);
    }

    public void saveAll(Iterable<T> entities) {
        getRepo().saveAllAndFlush(entities);
    }


}
