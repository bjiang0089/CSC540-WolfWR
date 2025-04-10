package CSC540.WolfWR.services;

import CSC540.WolfWR.models.DomainObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * A generic service class that provides common CRUD (Create, Read, Update, Delete) operations
 * for entities that extend {@link DomainObject}.
 * <p>
 * This abstract class is designed to be inherited by specific service classes, providing functionality
 * to interact with the database via a {@link JpaRepository}. It provides methods to save, find, and delete entities
 * using the repository.
 * </p>
 * 
 * @param <T> the type of the entity class, which extends {@link DomainObject}.
 * @param <K> the type of the primary key of the entity.
 * 
 * @see DomainObject
 * @see JpaRepository
 */
public abstract class Services<T extends DomainObject, K> {

    /**
     * Returns the repository associated with the entity class {@link T}.
     * <p>
     * This is an abstract method, and must be implemented by subclasses to return
     * the specific repository for the entity class.
     * </p>
     * 
     * @return the {@link JpaRepository} for the entity class {@link T}.
     */
    protected abstract JpaRepository<T, K> getRepo();

    /**
     * Saves a single entity to the database.
     * <p>
     * This method saves the given entity using the {@link JpaRepository#saveAndFlush(Object)} method, 
     * which persists the entity and immediately flushes the changes to the database.
     * </p>
     * 
     * @param entity the entity to be saved.
     */
    public void save(T entity) {
        getRepo().saveAndFlush(entity);
    }

    /**
     * Saves a list of entities to the database.
     * <p>
     * This method saves all entities from the provided iterable using the 
     * {@link JpaRepository#saveAllAndFlush(Iterable)} method, 
     * which persists the entities and immediately flushes the changes to the database.
     * </p>
     * 
     * @param entities the iterable collection of entities to be saved.
     */
    public void saveAll(Iterable<T> entities) {
        getRepo().saveAllAndFlush(entities);
    }

    /**
     * Finds an entity by its ID.
     * <p>
     * This method uses the {@link JpaRepository#findById(Object)} method to fetch an entity
     * by its primary key. If the entity is found, it is returned; otherwise, {@code null} is returned.
     * </p>
     * 
     * @param id the primary key of the entity to be found.
     * @return the entity with the given ID, or {@code null} if not found.
     */
    public T findByID(K id) {
        Optional<T> obj = getRepo().findById( id );
        return obj.orElse(null);
    }

    /**
     * Retrieves all entities of the given type.
     * <p>
     * This method uses the {@link JpaRepository#findAll()} method to fetch all entities of type {@link T}
     * from the database.
     * </p>
     * 
     * @return a list of all entities of type {@link T}.
     */
    public List<T> findAll() {
        return getRepo().findAll();
    }

    /**
     * Deletes the specified entity from the database.
     * <p>
     * This method uses the {@link JpaRepository#delete(Object)} method to remove the given entity
     * from the database.
     * </p>
     * 
     * @param obj the entity to be deleted.
     */
    public void delete(T obj){getRepo().delete(obj);}

    /**
     * Deletes a list of entities from the database.
     * <p>
     * This method uses the {@link JpaRepository#deleteAll(Iterable)} method to remove the provided
     * list of entities from the database.
     * </p>
     * 
     * @param objs the list of entities to be deleted.
     */
    public void deleteList(List<T> objs){ getRepo().deleteAll(objs); }
}
