package topcolleguesbackend.topcollegues.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import topcolleguesbackend.topcollegues.entite.Collegue;
import topcolleguesbackend.topcollegues.repository.CollegueRepository;

@RestController
@RequestMapping("/collegues")
@CrossOrigin
public class CollegueController {

	@Autowired
	private CollegueRepository colRepo;

	@GetMapping
	public ResponseEntity<List<Collegue>> lister() {
		return ResponseEntity.ok(colRepo.findAll());
	}

	@PostMapping
	public ResponseEntity<Collegue> ajouter(@RequestBody Collegue col) {
		Optional<Collegue> collegue = colRepo.getByPseudo(col.getPseudo());
		try {
			if (!collegue.isPresent()) {
				return ResponseEntity.ok(colRepo.save(col));
			}
			return ResponseEntity.badRequest().build();
		} catch (javax.persistence.PersistenceException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{pseudo}")
	public ResponseEntity<Collegue> supprimer(@PathVariable String pseudo) {
		Optional<Collegue> collegue = colRepo.getByPseudo(pseudo);
		try {
			if (collegue.isPresent()) {
				colRepo.delete(collegue.get());
				return ResponseEntity.ok(collegue.get());
			}
			return ResponseEntity.badRequest().build();
		} catch (javax.persistence.PersistenceException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PatchMapping("/{pseudo}")
	public ResponseEntity<Collegue> modif(@PathVariable String pseudo, @RequestBody Map<String, String> action) {
		Optional<Collegue> cible = colRepo.getByPseudo(pseudo);
		if (cible.isPresent()) {
			switch (action.get("action")) {
			case "aimer":
				cible.get().setScore(cible.get().getScore() + 10);
				break;
			case "detester":
				cible.get().setScore(cible.get().getScore() - 5);
				break;
			default:
				return ResponseEntity.unprocessableEntity().build();
			}
			return ResponseEntity.ok(colRepo.save(cible.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
