package representation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRepresentant {
	// Quelques constantes
	private static final float FIXE_BASTIDE = 1000f;
	private static final float INDEMNITE_OCCITANIE = 200f;
	
	private Representant r; // L'objet à tester
	private ZoneGeographique occitanie;
	
	@BeforeEach
	public void setUp() {
		// Initialiser les objets utilisés dans les tests
		occitanie = new ZoneGeographique(1, "Occitanie");
		occitanie.setIndemniteRepas(INDEMNITE_OCCITANIE);

		r = new Representant(36, "Bastide", "Rémi", occitanie);	
		r.setSalaireFixe(FIXE_BASTIDE);				
	}
	
	@Test
	public void testSalaireMensuel() {
		float CA = 50000f;
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		// On enregistre un CA pour le mois 0 (janvier)
		r.enregistrerCA(0, CA);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		
		assertEquals(// Comparaison de "float"
			// valeur attendue
			FIXE_BASTIDE + INDEMNITE_OCCITANIE + CA * POURCENTAGE,
			// Valeur calculée
			salaire,
			// Marge d'erreur tolérée
			0.001,
			// Message si erreur
			"Le salaire mensuel est incorrect"
		); 
	}

	@Test
	public void testCAParDefaut() {
		float POURCENTAGE= 0.1f; // 10% de pourcentage sur CA
		
		// On n'enregistre aucun CA
		//r.enregistrerCA(0, 10000f);
		
		// On calcule son salaire pour le mois 0 avec 10% de part sur CA
		float salaire = r.salaireMensuel(0, POURCENTAGE);
		
		// A quel résultat on s'attend ?
		// Le CA du mois doit avoir été initialisé à 0
		
		assertEquals(
			FIXE_BASTIDE + INDEMNITE_OCCITANIE, 
			salaire, 
			0.001,
			"Le CA n'est pas correctement initialisé"
		);
	}

	@Test
	public void testCANegatifImpossible() {
		
		try {
			// On enregistre un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(0, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un CA négatif doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

	@Test
	public void testMoisPlusImpossible() {
		
		try {
			// On enregistre un mois inexistant, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(15, 10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois inexistant doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

	@Test
	public void testMoisMoinsImpossible() {
		
		try {
			// On enregistre un mois inexistant, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(-5, 10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois inexistant doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

	@Test
	public void testCANegatifMoinsPlusImpossible() {
		
		try {
			// On enregistre un mois inexistant et un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(15, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un moins inexistant et un CA négatif doivent générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

	@Test
	public void testCANegatifMoinsMoinsImpossible() {
		
		try {
			// On enregistre un mois inexistant et un CA négatif, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.enregistrerCA(-5, -10000f);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un moins inexistant et un CA négatif doivent générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

	@Test
	public void testMoisPlusSalaireMensuel() {
		
		try {
			// On enregistre un mois inexistant, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.salaireMensuel(15, 50);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois inexistant doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}@Test
	public void testMoisMoinsSalaireMensuel() {
		
		try {
			// On enregistre un mois inexistant, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.salaireMensuel(-5, 50);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois inexistant doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

	@Test
	public void testPourcentageSalaireMensuel() {
		
		try {
			// On enregistre un pourcentage impossible, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.salaireMensuel(0, 123);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un pourcentage impossible doit générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}

	@Test
	public void testMoisPlusPourcentageSalaireMensuel() {
		
		try {
			// On enregistre un mois inexistant et un pourcentage impossible, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.salaireMensuel(15, 123);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois inexistant et un pourcentage impossible doivent générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}@Test
	public void testMoisMoinsPourcentageSalaireMensuel() {
		
		try {
			// On enregistre un mois inexistant et un pourcentage impossible, que doit-il se passer ?
			// On s'attend à recevoir une exception
			r.salaireMensuel(-5, 123);
			// Si on arrive ici, c'est une erreur, le test doit échouer
			fail("Un mois inexistant et un pourcentage impossible doivent générer une exception"); // Forcer l'échec du test			
		} catch (IllegalArgumentException e) {
			// Si on arrive ici, c'est normal, c'est le comportement attendu
		}

	}
	
	
}
