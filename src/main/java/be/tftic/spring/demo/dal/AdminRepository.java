package be.tftic.spring.demo.dal;

import be.tftic.spring.demo.domain.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface AdminRepository extends Repository<Admin, Long> {

    @Query("""
        SELECT COUNT(a.id) > 0
        FROM Admin a
        WHERE a.user.username = :username
    """)
    boolean existsByUser(String username);
    boolean existsByUser_Username(String username);

}
