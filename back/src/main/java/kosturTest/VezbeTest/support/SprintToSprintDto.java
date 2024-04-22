package kosturTest.VezbeTest.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import kosturTest.VezbeTest.dto.SprintDTO;
import kosturTest.VezbeTest.model.Sprint;


@Component
public class SprintToSprintDto implements Converter<Sprint, SprintDTO> {

	@Override
	public SprintDTO convert(Sprint sprint) {
		SprintDTO dto = new SprintDTO();
		dto.setId(sprint.getId());
		dto.setIme(sprint.getIme());
		dto.setUkupnoBodova(sprint.getUkupnoBodova());
		
		return dto;
	}
	
	public List<SprintDTO> convert(List<Sprint> sprintovi) {
		List<SprintDTO> sprintDTO = new ArrayList<>();

		for (Sprint sprint : sprintovi) {
			sprintDTO.add(convert(sprint));
		}

		return sprintDTO;
	}

}
