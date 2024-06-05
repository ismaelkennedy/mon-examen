package Entities;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private int id;
    private String nom;
    // OnetoMany
    private List<Cours> cours = new ArrayList<>();

    public Module() {
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
