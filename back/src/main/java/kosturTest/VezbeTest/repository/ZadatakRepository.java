package kosturTest.VezbeTest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kosturTest.VezbeTest.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long> {
	
	Zadatak findOneById(Long id);
	
	Page<Zadatak> findAll(Pageable pageable);
	
	@Query("SELECT z FROM Zadatak z WHERE "
			+ "(:zadatakIme IS NULL OR z.ime LIKE :zadatakIme) AND "
			+ "(:sprintId IS NULL OR z.sprint.id = :sprintId)")
	Page<Zadatak> pretraga(
			@Param("zadatakIme")String zadatakIme ,
			@Param("sprintId") Long sprintId, Pageable pageable);
	
	@Query("SELECT COALESCE(SUM(z.bodovi),0) FROM Zadatak z WHERE z.sprint.id = :sprintId")
	Long sumPoints(Long sprintId);

}
