package Services;

import java.util.List;
import Entities.Cours;
import Repositories.CoursRepository;

public class CoursService {
    private CoursRepository coursRepository;

    public CoursService() {
        this.coursRepository = new CoursRepository();
    }

    public void creerCours(Cours cours) {
        coursRepository.insert(cours);
    }

    public List<Cours> listerTousCours() {
        return coursRepository.selectAll();
    }

    public List<Cours> listerCoursParModuleEtProfesseur(int idModule, int idProfesseur) {
        return coursRepository.selectCoursByModuleAndProfesseur(idModule, idProfesseur);
    }

}
