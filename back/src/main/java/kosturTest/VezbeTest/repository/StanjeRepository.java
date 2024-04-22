package kosturTest.VezbeTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kosturTest.VezbeTest.model.Stanje;

@Repository
public interface StanjeRepository extends JpaRepository<Stanje, Long> {
	
	Stanje findOneById(Long id);

}
