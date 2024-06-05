package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entities.Professeur;

public class ProfesseurRepository {
    public List<Professeur> selectAll() {
        List<Professeur> professeurs = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/mon examen", "root", "root");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM professeurs");
            while (rs.next()) {
                Professeur prof = new Professeur();
                prof.setId(rs.getInt("id_professeur"));
                prof.setNom(rs.getString("nom"));
                prof.setPrenom(rs.getString("prenom"));
                prof.setTel(rs.getString("tel"));
                professeurs.add(prof);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        } catch (SQLException e) {
            System.out.println("Erreur de Connexion a la BD");
        }
        return professeurs;
    }

    public void insert(Professeur professeur) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/mon examen", "root", "root");
            Statement statement = conn.createStatement();

            String sql = String.format("INSERT INTO professeurs (nom, prenom, tel) " +
                    "VALUES ('%s', '%s', '%s')",
                    professeur.getNom(), professeur.getPrenom(), professeur.getTel());

            int nbreLigne = statement.executeUpdate(sql);
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        } catch (SQLException e) {
            System.out.println("Erreur de Connexion a la BD");
        }
    }

}