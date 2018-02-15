package topcolleguesbackend.topcollegues.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import topcolleguesbackend.topcollegues.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	public Collegue getByPseudo(String pseudo);
}
