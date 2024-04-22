INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
                            

              
INSERT INTO sprint (id, ime, ukupno_bodova) VALUES (1, 'Test Sprint', '15');
INSERT INTO sprint (id, ime, ukupno_bodova) VALUES (2, 'Production Sprint', '11');

INSERT INTO stanje (id, ime) VALUES (1, 'NEW');
INSERT INTO stanje (id, ime) VALUES (2, 'IN PROGRESS');
INSERT INTO stanje (id, ime) VALUES (3, 'FINISHED');

INSERT INTO zadatak (id, zaduzeni, ime, bodovi, sprint_id, stanje_id) 
	VALUES (1, 'Marko', 'Kreiraj Login', '7', 1, 2);
INSERT INTO zadatak (id, zaduzeni, ime, bodovi, sprint_id, stanje_id) 
	VALUES (2, 'Ivan', 'Autorizacija na back-u', '7', 1, 3);
INSERT INTO zadatak (id, zaduzeni, ime, bodovi, sprint_id, stanje_id) 
	VALUES (3, 'Marko', 'Testiraj login', '1', 1, 1);
INSERT INTO zadatak (id, zaduzeni, ime, bodovi, sprint_id, stanje_id) 
	VALUES (4, 'Ivan', 'Pokreni bazu', '7', 2, 3);
INSERT INTO zadatak (id, zaduzeni, ime, bodovi, sprint_id, stanje_id) 
	VALUES (5, 'Jovan', 'Napisi Unit Testove', '4', 2, 1);