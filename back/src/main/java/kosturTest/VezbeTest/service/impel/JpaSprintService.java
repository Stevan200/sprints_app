package kosturTest.VezbeTest.service.impel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosturTest.VezbeTest.model.Sprint;
import kosturTest.VezbeTest.repository.SprintRepository;
import kosturTest.VezbeTest.service.SprintService;

@Service
public class JpaSprintService implements SprintService {
	
	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public List<Sprint> findAll() {
		return sprintRepository.findAll();
	}

	@Override
	public Optional<Sprint> findOne(Long id) {
		return sprintRepository.findById(id);
	}

	@Override
	public Sprint save(Sprint sprint) {
		return sprintRepository.save(sprint);
	}

	@Override
	public Sprint findOneById(Long id) {
		return sprintRepository.findOneById(id);
	}

}
