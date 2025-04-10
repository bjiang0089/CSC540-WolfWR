package CSC540.WolfWR.services;


import CSC540.WolfWR.models.Member;
import CSC540.WolfWR.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing {@link Member} entities.
 * <p>
 * This service provides methods for interacting with the data layer to perform CRUD (Create, Read, Update, Delete)
 * operations on {@link Member} entities, using the {@link MemberRepository}.
 * </p>
 * <p>
 * It extends the {@link Services} class to inherit common CRUD functionality and is annotated with {@link Component}
 * to make it a Spring-managed bean. The {@link Transactional} annotation ensures that database operations are performed
 * within a transaction context.
 * </p>
 * 
 * @see Member
 * @see MemberRepository
 * @see Services
 */
@Transactional
@Component
public class MemberService extends Services<Member, Long> {

    /**
     * The repository used for accessing and managing {@link Member} entities in the database.
     */
    @Autowired
    private MemberRepository repo;

    /**
     * Returns the repository associated with {@link Member} entities.
     * <p>
     * This method overrides the {@link Services#getRepo()} method to provide the specific repository for {@link Member}.
     * </p>
     * 
     * @return the {@link MemberRepository} instance used to interact with {@link Member} entities.
     */
    @Override
    protected JpaRepository<Member, Long> getRepo() {
        return this.repo;
    }
}
