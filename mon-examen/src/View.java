import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

import Entities.Module;
import Entities.Professeur;
import Entities.Cours;
import Services.ModuleService;
import Services.ProfesseurService;
import Services.CoursService;

public class View {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ModuleService moduleService = new ModuleService();
        ProfesseurService professeurService = new ProfesseurService();
        CoursService coursService = new CoursService();

        int choix;
        do {
            System.out.println("1- Ajouter un Module");
            System.out.println("2- Lister les Modules");
            System.out.println("3- Créer un Cours");
            System.out.println("4- Lister tous les Cours");
            System.out.println("5- Lister les Cours d'un Module et d'un Professeur");
            System.out.println("6- Quitter");
            System.out.println("Faites un choix:");
            System.out.println("***********************************");
            choix = sc.nextInt();
            sc.nextLine(); 

            switch (choix) {
                case 1:
                    System.out.println("Entrez le nom du module:");
                    String nomModule = sc.nextLine();
                    Module module = new Module();
                    module.setNom(nomModule);
                    moduleService.ajouterModule(module);
                    break;
                case 2:
                    List<Module> modules = moduleService.listerModules();
                    for (Module m : modules) {
                        System.out.println(m.getId() + ": " + m.getNom());
                    }
                    break;
                case 3:
                    System.out.println("Entrez la date du cours (YYYY-MM-DD):");
                    String dateStr = sc.nextLine();
                    LocalDate date = LocalDate.parse(dateStr);

                    System.out.println("Entrez l'heure de début du cours (HH:MM):");
                    String heureDebutStr = sc.nextLine();
                    LocalTime heureDebut = LocalTime.parse(heureDebutStr);

                    System.out.println("Entrez l'heure de fin du cours (HH:MM):");
                    String heureFinStr = sc.nextLine();
                    LocalTime heureFin = LocalTime.parse(heureFinStr);

                    System.out.println("Entrez l'ID du professeur:");
                    int idProfesseur = sc.nextInt();
                    Professeur professeur = professeurService.trouverProfesseurParId(idProfesseur);

                    System.out.println("Entrez l'ID du module:");
                    int idModule = sc.nextInt();
                    Module moduleCours = moduleService.trouverModuleParId(idModule);

                    Cours cours = new Cours();
                    cours.setDate(date);
                    cours.setHeureDebut(heureDebut);
                    cours.setHeureFin(heureFin);
                    cours.setProfesseur(professeur);
                    cours.setModule(moduleCours);

                    coursService.creerCours(cours);
                    break;
                case 4:
                    List<Cours> tousCours = coursService.listerTousCours();
                    for (Cours c : tousCours) {
                        System.out.println("Date: " + c.getDate() + ", Heure de début: " + c.getHeureDebut() +
                                ", Heure de fin: " + c.getHeureFin() + ", Professeur: " + c.getProfesseur().getNom() +
                                ", Module: " + c.getModule().getNom());
                    }
                    break;
                case 5:
                    System.out.println("Entrez l'ID du module:");
                    int idModuleCours = sc.nextInt();
                    System.out.println("Entrez l'ID du professeur:");
                    int idProfesseurCours = sc.nextInt();

                    List<Cours> coursModuleProf = coursService.listerCoursParModuleEtProfesseur(idModuleCours, idProfesseurCours);
                    for (Cours c : coursModuleProf) {
                        System.out.println("Date: " + c.getDate() + ", Heure de début: " + c.getHeureDebut() +
                                ", Heure de fin: " + c.getHeureFin() + ", Professeur: " + c.getProfesseur().getNom() +
                                ", Module: " + c.getModule().getNom());
                    }
                    break;
                case 6:
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 6);

        sc.close();
    }
}
