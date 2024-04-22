package kosturTest.VezbeTest.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kosturTest.VezbeTest.dto.ZadatakDTO;
import kosturTest.VezbeTest.model.Zadatak;
import kosturTest.VezbeTest.service.SprintService;
import kosturTest.VezbeTest.service.StanjeService;
import kosturTest.VezbeTest.service.ZadatakService;

@Component
public class ZadatakDtoToZadatak implements Converter<ZadatakDTO, Zadatak> {
	
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private StanjeService stanjeService;

	@Override
	public Zadatak convert(ZadatakDTO dto) {

		Zadatak zadatak;
		
		if(dto.getId() == null){
            zadatak = new Zadatak();
        }else{
            zadatak = zadatakService.findOne(dto.getId());
        }
		if (zadatak != null) {
			zadatak.setId(dto.getId());
			zadatak.setSprint(sprintService.findOneById(dto.getSprintId()));
			zadatak.setStanje(stanjeService.findOneById(dto.getStanjeId()));
			zadatak.setIme(dto.getIme());
			zadatak.setBodovi(dto.getBodovi());
			zadatak.setZaduzeni(dto.getZaduzeni());
			
		}
		
		return zadatak;
	}

}
