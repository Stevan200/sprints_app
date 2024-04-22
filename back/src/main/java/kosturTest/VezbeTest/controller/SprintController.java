package kosturTest.VezbeTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosturTest.VezbeTest.dto.SprintDTO;
import kosturTest.VezbeTest.model.Sprint;
import kosturTest.VezbeTest.service.SprintService;
import kosturTest.VezbeTest.support.SprintToSprintDto;

@RestController
@RequestMapping(value = "/api/sprintovi", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class SprintController {
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private SprintToSprintDto toSprintDto;
	
	@GetMapping
    public ResponseEntity<List<SprintDTO>> getAll(){

        List<Sprint> sprintovi = sprintService.findAll();

        return new ResponseEntity<>(toSprintDto.convert(sprintovi), HttpStatus.OK);
    }

}
