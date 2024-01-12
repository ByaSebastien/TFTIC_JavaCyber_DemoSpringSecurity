package be.tftic.spring.demo.dal;

import be.tftic.spring.demo.domain.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("""
        SELECT COUNT(u.id) > 0
        FROM User u
        WHERE u.admin != null AND u.username = :username
    """)
    boolean isAdmin(String username);
    boolean existsByAdminNotNullAndUsername(String username);


    @Modifying // uniquement si le comportement est défini par @Query
    @Transactional
    @Query("""
        DELETE FROM User u
        WHERE u.username = :username
    """)
    void deleteByUsername(String username);


}
