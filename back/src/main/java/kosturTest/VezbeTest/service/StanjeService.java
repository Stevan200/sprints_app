package kosturTest.VezbeTest.service;

import java.util.List;
import java.util.Optional;

import kosturTest.VezbeTest.model.Stanje;


public interface StanjeService {
	
	Stanje findOneById(Long id);
	List<Stanje> findAll();
    Optional<Stanje> findOne(Long id);
    Stanje save(Stanje stanje);


}
