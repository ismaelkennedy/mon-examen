package Services;

import java.util.List;
import Entities.Professeur;
import Repositories.ProfesseurRepository;

public class ProfesseurService {
    private ProfesseurRepository professeurRepository;

    public ProfesseurService() {
        this.professeurRepository = new ProfesseurRepository();
    }

    public void ajouterProfesseur(Professeur professeur) {
        professeurRepository.insert(professeur);
    }

    public List<Professeur> listerProfesseurs() {
        return professeurRepository.selectAll();
    }

    public Professeur trouverProfesseurParId(int idProfesseur) {
        return null;
    }

}
