package kosturTest.VezbeTest.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kosturTest.VezbeTest.dto.StanjeDTO;
import kosturTest.VezbeTest.model.Stanje;


@Component
public class StanjeToStanjeDto implements Converter<Stanje, StanjeDTO> {

	@Override
	public StanjeDTO convert(Stanje stanje) {
		StanjeDTO dto = new StanjeDTO();
		dto.setId(stanje.getId());
		dto.setIme(stanje.getIme());
		
		return dto;
	}
	
	public List<StanjeDTO> convert(List<Stanje> stanja) {
		List<StanjeDTO> stanjeDTO = new ArrayList<>();

		for (Stanje stanje : stanja) {
			stanjeDTO.add(convert(stanje));
		}

		return stanjeDTO;
	}

}
