package kosturTest.VezbeTest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String ime;
	
	@Column
	private String ukupnoBodova;
	
	@OneToMany(mappedBy = "sprint", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Zadatak> zadaci = new ArrayList<>();

	public Sprint() {
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

	public String getUkupnoBodova() {
		return ukupnoBodova;
	}

	public void setUkupnoBodova(String ukupnoBodova) {
		this.ukupnoBodova = ukupnoBodova;
	}

	public void removeZadatak(Long id) {
		for(Zadatak t : this.zadaci) {
			if (t.getId()==id){
				this.zadaci.remove(t);
				this.setUkupnoBodova(Integer.parseInt(this.getUkupnoBodova())-t.getBodovi()+"");
				return;
			}
		}
	}

	public void addZadatak(Zadatak zadatak) {
		this.zadaci.add(zadatak);
		this.setUkupnoBodova(Integer.parseInt(this.getUkupnoBodova())+zadatak.getBodovi()+"");		
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprint other = (Sprint) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Sprint [id=" + id + ", ime=" + ime + ", ukupnoBodova=" + ukupnoBodova + ", zadaci=" + zadaci + "]";
	}
	
}
