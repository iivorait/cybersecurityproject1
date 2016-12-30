package sec.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sec.project.domain.Signup;

public interface SignupRepository extends JpaRepository<Signup, Long> {
    
    //I tried to do an SQL injection here but I failed - it seems foolproof!
    @Query(value = "SELECT p FROM Signup p WHERE p.name = ':name'")
    List<Signup> findParticipants(@Param("name") String name);
    
}
