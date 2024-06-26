package kosturTest.VezbeTest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ZadatakDTO {
	
	private Long id;
	
	@NotBlank
	@Length(max = 40, message = "Duzina moze biti max 40 char.")
	private String ime;
	
	private String zaduzeni;
	
	@Min(value = 0, message = "Min. vrednost bodova 0.")
	@Max(value = 20, message = "max. vrednost bodova 20.")
	private Integer bodovi;
	
	private Long sprintId;
	
	private Long stanjeId;

	private String sprintIme;
	
	private String stanjeIme;

	public ZadatakDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getZaduzeni() {
		return zaduzeni;
	}

	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}

	public Integer getBodovi() {
		return bodovi;
	}

	public void setBodovi(Integer bodovi) {
		this.bodovi = bodovi;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public Long getStanjeId() {
		return stanjeId;
	}

	public void setStanjeId(Long stanjeId) {
		this.stanjeId = stanjeId;
	}

	public String getSprintIme() {
		return sprintIme;
	}

	public void setSprintIme(String sprintIme) {
		this.sprintIme = sprintIme;
	}

	public String getStanjeIme() {
		return stanjeIme;
	}

	public void setStanjeIme(String stanjeIme) {
		this.stanjeIme = stanjeIme;
	}

	
	
}
