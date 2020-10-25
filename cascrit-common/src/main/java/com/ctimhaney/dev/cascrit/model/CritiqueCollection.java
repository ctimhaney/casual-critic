package com.ctimhaney.dev.cascrit.model;

import java.util.ArrayList;

public class CritiqueCollection {
  private ArrayList<CritiqueGroup> critiqueGroups;
  private ArrayList<CasualCritique> casusalCritiques;

  public CritiqueCollection(){
    this.critiqueGroups = new ArrayList<CritiqueGroup>();
    this.casusalCritiques = new ArrayList<CasualCritique>();
  }

  public CritiqueCollection(ArrayList<CritiqueGroup> critiqueGroups, ArrayList<CasualCritique> casusalCritiques) {
    this.critiqueGroups = critiqueGroups;
    this.casusalCritiques = casusalCritiques;
  }

  public ArrayList<CritiqueGroup> getCritiqueGroups() { return this.critiqueGroups; }
  public void setCritiqueGroups(ArrayList<CritiqueGroup> critiqueGroups) {
    this.critiqueGroups = critiqueGroups;
   }

  public ArrayList<CasualCritique> getCasualCritiques() { return this.casusalCritiques; }
  public void setCasualCritiques(ArrayList<CasualCritique> casusalCritiques) {
    this.casusalCritiques = casusalCritiques;
  }

  public CritiqueGroup addCritiqueGroup(String groupName) {
    CritiqueGroup newGroup = null;
    if (getCritiqueGroup(groupName) == null) {
      newGroup = new CritiqueGroup(groupName);
      critiqueGroups.add(newGroup);
    }
    return newGroup;
  }

  public CritiqueGroup getCritiqueGroup(String groupName) {
    for (CritiqueGroup i: critiqueGroups) {
      if (i.getGroupName().equals(groupName)) {
        return i;
      }
    }
    return null;
  }

  // TODO cascade to CRitique objects. Is using an object ref enough?
  public CritiqueGroup updateCritiqueGroup(String oldGroupName, String newGroupName) {
    CritiqueGroup o = getCritiqueGroup(oldGroupName);
    if (o != null) {
      o.setGroupName(newGroupName);
    }
    return o;
  }

  public CritiqueGroup deleteCritiqueGroup(String groupName) {
    CritiqueGroup o = getCritiqueGroup(groupName);
    if (o != null) {
      for (CasualCritique i: casusalCritiques) {
        // TODO compare object refs?
        if (i.getCritiqueGroup().getGroupName().equals(o.getGroupName())) {
          return null;
        }
      }
      this.critiqueGroups.remove(o);
    }
    return o;
  }

  // TODO casusalCritiques

  // toSTrang
  public String toString() {
    String returnme = "groups:\n";
    for (CritiqueGroup g: this.critiqueGroups) {
      returnme += String.format("  %s\n", g.toString());
    }
    returnme += "critiques:\n";
    for (CasualCritique c: this.casusalCritiques) {
      returnme += String.format("  %s\n", c.toString());
    }
    return returnme;
  }
}
