package topcolleguesbackend.topcollegues.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import topcolleguesbackend.topcollegues.entite.Avis;
import topcolleguesbackend.topcollegues.entite.Collegue;
import topcolleguesbackend.topcollegues.entite.Comment;
import topcolleguesbackend.topcollegues.repository.CollegueRepository;
import topcolleguesbackend.topcollegues.repository.CommentRepository;
import topcolleguesbackend.topcollegues.service.HistoriqueAvis;

@RestController
@RequestMapping("/collegues")
@CrossOrigin
public class CollegueController {

	@Autowired
	private CollegueRepository colRepo;
	
	@Autowired
	private CommentRepository comRepo;
	
	@Autowired
	private HistoriqueAvis histoAvis;

	@GetMapping
	@Transactional
	public ResponseEntity<List<Collegue>> lister() {
		return ResponseEntity.ok(colRepo.findAll());
	}
	
	@GetMapping("/{pseudo}")
	@Transactional
	public ResponseEntity<Collegue> addcollegue(@PathVariable String pseudo) {
		Optional<Collegue> collegue = colRepo.getByPseudo(pseudo);
		if (collegue.isPresent()) {
			return ResponseEntity.ok(collegue.get());
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping
	@Transactional
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

	@PatchMapping("/{pseudo}")
	@Transactional
	public ResponseEntity<Collegue> modif(@PathVariable String pseudo, @RequestBody Map<String, String> action) {
		Optional<Collegue> cible = colRepo.getByPseudo(pseudo);
		String ac = "action";
		if (cible.isPresent()) {
			switch (action.get(ac)) {
				case "aimer":
					cible.get().setScore(cible.get().getScore() + 10);
					histoAvis.newAvis(new Avis(cible.get(), action.get(ac)));
					break;
				case "detester":
					cible.get().setScore(cible.get().getScore() - 5);
					histoAvis.newAvis(new Avis(cible.get(), action.get(ac)));
					break;
				case "avis":
					Comment com = new Comment(action.get("comment"));
					comRepo.save(com);
					cible.get().getAvis().add(com);
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
