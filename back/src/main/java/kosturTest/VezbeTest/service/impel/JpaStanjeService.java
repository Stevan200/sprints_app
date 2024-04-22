package kosturTest.VezbeTest.service.impel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosturTest.VezbeTest.model.Stanje;
import kosturTest.VezbeTest.repository.StanjeRepository;
import kosturTest.VezbeTest.service.StanjeService;

@Service
public class JpaStanjeService implements StanjeService {
	
	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public List<Stanje> findAll() {
		return stanjeRepository.findAll();
	}

	@Override
	public Optional<Stanje> findOne(Long id) {
		return stanjeRepository.findById(id);
	}

	@Override
	public Stanje save(Stanje stanje) {
		return stanjeRepository.save(stanje);
	}

	@Override
	public Stanje findOneById(Long id) {
		return stanjeRepository.findOneById(id);
	}

}
