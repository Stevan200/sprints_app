package kosturTest.VezbeTest.service.impel;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kosturTest.VezbeTest.model.Stanje;
import kosturTest.VezbeTest.model.Zadatak;
import kosturTest.VezbeTest.repository.SprintRepository;
import kosturTest.VezbeTest.repository.StanjeRepository;
import kosturTest.VezbeTest.repository.ZadatakRepository;
import kosturTest.VezbeTest.service.ZadatakService;

@Service
public class JpaZadatakService implements ZadatakService {
	
	@Autowired
	private ZadatakRepository zadatakRepository;
	
	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private StanjeRepository stanjeRepository;

	@Override
	public Zadatak findOne(Long id) {
		return zadatakRepository.findOneById(id);
	}

	@Override
	public Page<Zadatak> findAll(Integer pageNo) {
		return zadatakRepository.findAll(PageRequest.of(pageNo, 10));
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak update(Zadatak zadatak) {
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak delete(Long id) {
		Optional<Zadatak> zadatak = zadatakRepository.findById(id);
        if(zadatak.isPresent()){
            zadatakRepository.deleteById(id);
            return zadatak.get();
        }
        return null;
	}

	@Override
	public Long sumPoints(Long sprintId) {
		return zadatakRepository.sumPoints(sprintId);
	}

	@Override
	public Page<Zadatak> pretraga(String zadatakIme, Long sprintId, int pageNo) {
		
		if(zadatakIme != null) {
			zadatakIme = "%" + zadatakIme + "%";
		}
		return zadatakRepository.pretraga(zadatakIme, sprintId, PageRequest.of(pageNo, 3));
	}

	@Override
	public Zadatak prelazak(Long id) {
		Zadatak zadatak = zadatakRepository.getOne(id);
		if(zadatak != null) {
			Stanje currentState = zadatak.getStanje();
			if(currentState.getId().equals(1L)) {
				Stanje inProgress = stanjeRepository.getOne(2L);
				zadatak.setStanje(inProgress);
			}
			else if(currentState.getId().equals(2L)) {
				Stanje finished = stanjeRepository.getOne(3L);
				zadatak.setStanje(finished);
			}
			
			return zadatakRepository.save(zadatak);
		}
		
		return null;
	}

}
