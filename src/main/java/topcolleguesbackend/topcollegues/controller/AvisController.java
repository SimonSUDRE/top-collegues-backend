package topcolleguesbackend.topcollegues.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import topcolleguesbackend.topcollegues.entite.Avis;
import topcolleguesbackend.topcollegues.service.HistoriqueAvis;

@RestController
@CrossOrigin
@RequestMapping("/avis")
public class AvisController {

	@Autowired
	private HistoriqueAvis histoAvis;
	
	@GetMapping
	public List<Avis> historique(@RequestParam(value = "since", required = false) Integer voteId) {
		if(voteId == null) {
			return histoAvis.getDefaultHistorique();
		}
		return histoAvis.getHistorique(voteId);
	}
	
	@DeleteMapping
	public Avis retirerHistorique(@RequestParam(value = "since", required = true) Integer voteId) {
		return histoAvis.delete(voteId);
	}

}
