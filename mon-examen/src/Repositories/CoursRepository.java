package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Entities.Cours;
import Entities.Module;
import Entities.Professeur;

public class CoursRepository {
    public List<Cours> selectAll() {
        List<Cours> cours = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/mon examen", "root", "root");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cours");
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id_cours"));
                c.setDate(rs.getObject("date", LocalDate.class));
                c.setHeureDebut(rs.getObject("heureDebut", LocalTime.class));
                c.setHeureFin(rs.getObject("heureFin", LocalTime.class));

                Professeur prof = new Professeur();
                prof.setId(rs.getInt("id_professeur"));
                c.setProfesseur(prof);

                Module module = new Module();
                module.setId(rs.getInt("id_module"));
                c.setModule(module);

                cours.add(c);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        } catch (SQLException e) {
            System.out.println("Erreur de Connexion a la BD");
        }
        return cours;
    }

    public void insert(Cours cours) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/mon examen", "root", "root");
            Statement statement = conn.createStatement();

            String sql = String.format("INSERT INTO cours (date, heureDebut, heureFin, id_professeur, id_module) " +
                    "VALUES ('%s', '%s', '%s', '%d', '%d')",
                    cours.getDate(), cours.getHeureDebut(), cours.getHeureFin(),
                    cours.getProfesseur().getId(), cours.getModule().getId());

            int nbreLigne = statement.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        } catch (SQLException e) {
            System.out.println("Erreur de Connexion a la BD");
        }
    }

    public List<Cours> selectCoursByModuleAndProfesseur(Module module, Professeur professeur) {
        List<Cours> cours = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/mon examen", "root", "root");
            Statement statement = conn.createStatement();
            String sql = String.format("SELECT * FROM cours WHERE id_module = %d AND id_professeur = %d",
                    module.getId(), professeur.getId());
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id_cours"));
                c.setDate(rs.getObject("date", LocalDate.class));
                c.setHeureDebut(rs.getObject("heureDebut", LocalTime.class));
                c.setHeureFin(rs.getObject("heureFin", LocalTime.class));

                c.setProfesseur(professeur);
                c.setModule(module);

                cours.add(c);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        } catch (SQLException e) {
            System.out.println("Erreur de Connexion a la BD");
        }
        return cours;
    }

    public List<Cours> selectCoursByModuleAndProfesseur(int idModule, int idProfesseur) {
        return null;
    }
}

