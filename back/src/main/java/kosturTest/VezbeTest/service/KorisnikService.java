package kosturTest.VezbeTest.service;


import org.springframework.data.domain.Page;

import kosturTest.VezbeTest.dto.KorisnikPromenaLozinkeDto;
import kosturTest.VezbeTest.model.Korisnik;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {

    Optional<Korisnik> findOne(Long id);

    List<Korisnik> findAll();

    Page<Korisnik> findAll(int brojStranice);

    Korisnik save(Korisnik korisnik);

    void delete(Long id);

    Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme);

    boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto);
}
