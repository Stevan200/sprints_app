package kosturTest.VezbeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kosturTest.VezbeTest.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
	
	Sprint findOneById(Long id);

}
