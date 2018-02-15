package topcolleguesbackend.topcollegues.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import topcolleguesbackend.topcollegues.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	public Optional<Collegue> getByPseudo(String pseudo);
}
