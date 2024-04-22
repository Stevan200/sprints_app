package kosturTest.VezbeTest.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kosturTest.VezbeTest.dto.ZadatakDTO;
import kosturTest.VezbeTest.model.Zadatak;


@Component
public class ZadatakToZadatakDto implements Converter<Zadatak, ZadatakDTO> {

	@Override
	public ZadatakDTO convert(Zadatak zadatak) {
		ZadatakDTO dto = new ZadatakDTO();
		dto.setId(zadatak.getId());
		dto.setIme(zadatak.getIme());
		dto.setZaduzeni(zadatak.getZaduzeni());
		dto.setBodovi(zadatak.getBodovi());
		dto.setSprintId(zadatak.getSprint().getId());
		dto.setStanjeId(zadatak.getStanje().getId());
		dto.setSprintIme(zadatak.getSprint().getIme());
		dto.setStanjeIme(zadatak.getStanje().getIme());

		
		return dto;
	}
	
	public List<ZadatakDTO> convert(List<Zadatak> zadaci) {
		List<ZadatakDTO> zadatakDTO = new ArrayList<>();

		for (Zadatak zadatak : zadaci) {
			zadatakDTO.add(convert(zadatak));
		}

		return zadatakDTO;
	}

}
