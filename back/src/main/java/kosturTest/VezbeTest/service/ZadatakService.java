package kosturTest.VezbeTest.service;

import org.springframework.data.domain.Page;

import kosturTest.VezbeTest.model.Zadatak;


public interface ZadatakService {
	
	Zadatak findOne(Long id);

    Page<Zadatak> findAll(Integer pageNo);
    
    Zadatak save(Zadatak zadatak);

    Zadatak update(Zadatak zadatak);

    Zadatak delete(Long id);
    
	Long sumPoints(Long sprintId);

    Page<Zadatak> pretraga(String zadatakIme, Long sprintId, int pageNo);
    
    Zadatak prelazak(Long id);

}
