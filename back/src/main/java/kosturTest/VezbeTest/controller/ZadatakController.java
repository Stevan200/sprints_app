package kosturTest.VezbeTest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kosturTest.VezbeTest.dto.ZadatakDTO;
import kosturTest.VezbeTest.model.Zadatak;
import kosturTest.VezbeTest.service.ZadatakService;
import kosturTest.VezbeTest.support.ZadatakDtoToZadatak;
import kosturTest.VezbeTest.support.ZadatakToZadatakDto;


@RestController
@RequestMapping(value = "/api/zadaci", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ZadatakController {
	
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private ZadatakDtoToZadatak toZadatak;
	
	@Autowired
	private ZadatakToZadatakDto toZadatakDto;
	
	@GetMapping
    public ResponseEntity<List<ZadatakDTO>> getAll(
    		@RequestParam(value = "ime", required=false) String zadatakIme,
			@RequestParam(required=false) Long sprintId,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){
		Page<Zadatak> page;
		try {
	        page= zadatakService.pretraga(zadatakIme, sprintId, pageNo);
		} catch (Exception e) {
	        page = zadatakService.findAll(pageNo);
		}
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

        return new ResponseEntity<>(toZadatakDto.convert(page.getContent()),headers, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<ZadatakDTO> getOne(@PathVariable Long id) {
		Zadatak zadatak = zadatakService.findOne(id);

		if(zadatak != null) {
			return new ResponseEntity<>(toZadatakDto.convert(zadatak), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZadatakDTO> create(@Valid @RequestBody ZadatakDTO zadatakDTO){
        Zadatak zadatak = toZadatak.convert(zadatakDTO);
        Zadatak sacuvanaZadatak = zadatakService.save(zadatak);

        return new ResponseEntity<>(toZadatakDto.convert(sacuvanaZadatak), HttpStatus.CREATED);
    }
    
  //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZadatakDTO> update(@PathVariable Long id, @Valid @RequestBody ZadatakDTO zadatakDTO){

        if(!id.equals(zadatakDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Zadatak zadatak = toZadatak.convert(zadatakDTO);
        Zadatak sacuvanaZadatak = zadatakService.update(zadatak);

        return new ResponseEntity<>(toZadatakDto.convert(sacuvanaZadatak),HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Zadatak obrisanaZadatak = zadatakService.delete(id);

        if(obrisanaZadatak != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@RequestMapping(value = "/{id}/change_state", method = RequestMethod.POST)
	public ResponseEntity<ZadatakDTO> changeState(@PathVariable Long id) {
		
		Zadatak task = zadatakService.prelazak(id);
		if(task != null) {
			return new ResponseEntity<>(toZadatakDto.convert(task),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}





