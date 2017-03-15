package com.example.manon.appli_mobile_bdd_manon_hassnaoui;

/**
 * Created by Manon on  15/03/2017.
 * La classe contient tous les éléments qui constituent un Chapitre, à savoir, un id, un nom et une description dans notre cas.
 */

public class Chapter {
    private long id;
    private String name;
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //sera utilisé dans l'arrayadapter dans la listview
    @Override
    public String toString()
    {
        String affichage="Nom: "+ getName()+", Description: "+getDescription();
        return affichage;
    }
}
