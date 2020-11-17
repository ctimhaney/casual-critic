package com.ctimhaney.dev.cascrit.model;

import java.util.ArrayList;

public class CritiqueCollection {
  private ArrayList<CritiqueGroup> critiqueGroups;
  private ArrayList<CasualCritique> casualCritiques;

  public CritiqueCollection(){
    this.critiqueGroups = new ArrayList<CritiqueGroup>();
    this.casualCritiques = new ArrayList<CasualCritique>();
  }

  public CritiqueCollection(ArrayList<CritiqueGroup> critiqueGroups, ArrayList<CasualCritique> casualCritiques) {
    this.critiqueGroups = critiqueGroups;
    this.casualCritiques = casualCritiques;
  }

  public ArrayList<CritiqueGroup> getCritiqueGroups() { return this.critiqueGroups; }
  public void setCritiqueGroups(ArrayList<CritiqueGroup> critiqueGroups) {
    this.critiqueGroups = critiqueGroups;
   }

  public ArrayList<CasualCritique> getCasualCritiques() { return this.casualCritiques; }
  public void setCasualCritiques(ArrayList<CasualCritique> casualCritiques) {
    this.casualCritiques = casualCritiques;
  }

  public CritiqueGroup addCritiqueGroup(String groupName) {
    CritiqueGroup newGroup = null;
    if (getCritiqueGroup(groupName) == null) {
      newGroup = new CritiqueGroup(this.critiqueGroups.size(), groupName);
      this.critiqueGroups.add(newGroup);
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

  public CritiqueGroup getCritiqueGroup(int objectId) {
    for (CritiqueGroup i: critiqueGroups) {
      if (i.getObjectId() == objectId) {
        return i;
      }
    }
    return null;
  }

  public CritiqueGroup updateCritiqueGroup(String oldGroupName, String newGroupName) {
    CritiqueGroup o = getCritiqueGroup(oldGroupName);
    if (o != null) {
      o.setGroupName(newGroupName);
    }
    return o;
  }

  public CritiqueGroup updateCritiqueGroup(int objectId, String newGroupName) {
    CritiqueGroup o = getCritiqueGroup(objectId);
    if (o != null) {
      o.setGroupName(newGroupName);
    }
    return o;
  }

  public CritiqueGroup deleteCritiqueGroup(String groupName) {
    CritiqueGroup o = getCritiqueGroup(groupName);
    if (o != null) {
      for (CasualCritique i: this.casualCritiques) {
        // TODO compare object refs?
        if (i.getCritiqueGroupId() == o.getObjectId()) {
          return null;
        }
      }
      this.critiqueGroups.remove(o);
    }
    return o;
  }

  public CritiqueGroup deleteCritiqueGroup(int objectId) {
    CritiqueGroup o = getCritiqueGroup(objectId);
    if (o != null) {
      for (CasualCritique i: this.casualCritiques) {
        // TODO compare object refs?
        if (i.getCritiqueGroupId() == objectId) {
          return null;
        }
      }
      this.critiqueGroups.remove(o);
    }
    return o;
  }

  public CasualCritique addCasualCritique(int groupId, String title, boolean adequacy, int rating, String body) {
    CritiqueGroup o = getCritiqueGroup(groupId);
    CasualCritique newCritique = null;
    if (o != null && getCasualCritique(title) == null) {
      if (body == null) {
        body = "";
      }
      newCritique = new CasualCritique(this.casualCritiques.size(), groupId, title, adequacy, rating, body);
      this.casualCritiques.add(newCritique);
    }
    return newCritique;
  }

  public CasualCritique getCasualCritique(String title) {
    for (CasualCritique o: this.casualCritiques) {
      if (o.getCritiqueTitle().equals(title)) {
        return o;
      }
    }
    return null;
  }
  public CasualCritique getCasualCritique(int objectId) {
    for (CasualCritique o: this.casualCritiques) {
      if (o.getObjectId() == objectId) {
        return o;
      }
    }
    return null;
  }

  public CasualCritique deleteCasualCritique(String title) {
    CasualCritique o = getCasualCritique(title);
    if (o != null) {
      this.casualCritiques.remove(o);
    }
    return o;
  }

  public CasualCritique deleteCasualCritique(int objectId) {
    CasualCritique o = getCasualCritique(objectId);
    if (o != null) {
      this.casualCritiques.remove(o);
    }
    return o;
  }

  // toSTrang
  public String toString() {
    String returnme = "groups:\n";
    for (CritiqueGroup g: this.critiqueGroups) {
      returnme += String.format("%s\n", g.toString());
    }
    returnme += "critiques:\n";
    for (CasualCritique c: this.casualCritiques) {
      returnme += String.format("%s\n", c.toString());
    }
    return returnme;
  }
}
