package com.pfa.gestiontransfert;

import com.pfa.gestiontransfert.models.Horaire;
import com.pfa.gestiontransfert.repositories.HoraireRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestiontransfertApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiontransfertApplication.class, args);
	}

	@Autowired
	HoraireRepository horaireRepository;

	@PostConstruct
	public void init() {
		Horaire agenceHoraire = horaireRepository.findById(1L).orElseThrow(() -> new RuntimeException("Agence horaire not found"));
	}

}
