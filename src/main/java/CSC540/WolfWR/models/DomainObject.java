package CSC540.WolfWR.models;

import java.io.Serializable;

/**
 * This is an abstract base class for domain objects in the application.
 * It implements {@link Serializable} to allow objects of subclasses to be serialized,
 * making them suitable for persistence mechanisms such as database storage or session management.
 * <p>
 * The {@code DomainObject} class serves as a common superclass for all entities in the application.
 * </p>
 * 
 * @see Serializable
 */
abstract public class DomainObject implements Serializable {
}
