package topcolleguesbackend.topcollegues.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class CollegueController {

	@Autowired
	private CollegueRepository colRepo;

	@GetMapping
	@CrossOrigin
	public List<Collegue> lister() {
		return colRepo.findAll();
	}

	@PostMapping
	@CrossOrigin
	public Collegue ajouter(@RequestBody Collegue col) {
		if (colRepo.getByPseudo(col.getPseudo()) == null) {
			colRepo.save(col);
			return col;
		}
		return null;
	}

	@PatchMapping("/{pseudo}")
	@CrossOrigin
	public Collegue modif(@PathVariable String pseudo, @RequestBody Map<String, String> action) {
		Collegue col = colRepo.getByPseudo(pseudo);
		if (col != null) {
			if (action.get("action").equals("aimer")) {
				col.setScore(col.getScore() + 10);
			}
			if (action.get("action").equals("detester")) {
				col.setScore(col.getScore() - 5);
			}
			colRepo.save(col);
			return col;
		}
		return null;
	}
}
