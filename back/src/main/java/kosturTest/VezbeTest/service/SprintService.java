package kosturTest.VezbeTest.service;

import java.util.List;
import java.util.Optional;

import kosturTest.VezbeTest.model.Sprint;


public interface SprintService {
	
	Sprint findOneById(Long id);
    List<Sprint> findAll();
    Optional<Sprint> findOne(Long id);
    Sprint save(Sprint sprint);

}
