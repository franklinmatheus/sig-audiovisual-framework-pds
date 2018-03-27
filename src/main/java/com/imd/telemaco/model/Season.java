/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

import java.util.ArrayList;

/**
 *
 * @author valmir
 */
class Season {
    public String name;
    public ArrayList<Episode> episodes;
    
    /**
     * Default constructor 
     */
    public Season() { }
    
    /**
     * Parametric constructor 
     */
    public Season(String name, ArrayList<Episode> episodes) { 
        this.name = name;
        this.episodes = episodes;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the episodes
     */
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * @param episodes the episodes to set
     */
    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
    
}
