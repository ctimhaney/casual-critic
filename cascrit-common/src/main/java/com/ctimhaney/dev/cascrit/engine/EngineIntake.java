package com.ctimhaney.dev.cascrit.engine;

import java.util.HashMap;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

public class EngineIntake {
  // TODO enums?
  private String entity;
  private String action;
  private HashMap<String, String> properties;
  private ArrayList<String> flags;

  public EngineIntake() {
    this.properties = new HashMap<String, String>();
    this.flags = new ArrayList<String>();
  }

  public EngineIntake(String entity, String action, HashMap<String, String> properties, ArrayList<String> flags) {
    this.entity = entity;
    this.action = action;
    this.properties = properties;
    this.flags = flags;
  }

  public String getEntity() { return this.entity; }
  public void setEntity(String entity) { this.entity = entity; }

  public String getAction() { return this.action; }
  public void setAction(String action) { this.action = action; }

  public HashMap<String, String> getProperties() { return this.properties; }
  public void setProperties(HashMap<String, String> properties) { this.properties = properties; }

  public ArrayList<String> getFlags() { return this.flags; }
  public void setFlags(ArrayList<String> flags) { this.flags = flags; }

  public void addProperty(String key, String value) {
    this.properties.put(key, value);
  }

  public String getProperty(String key) {
    return this.properties.get(key);
  }

  public void addFlag(String flag) {
    this.flags.add(flag);
  }

  public boolean getFlag(String flag) {
    for (String s: this.flags) {
      if (s.equals(flag)) {
        return true;
      }
    }
    return false;
  }

  public String toString() {
    String returnme = String.format("Entity: %s\nAction: %s\n", this.entity, this.action);
    if (this.properties != null) {
      returnme += "properties:\n";
      for (String s: this.properties.keySet()) {
        returnme += String.format("  %s: %s\n", s, this.properties.get(s));
      }
    }
    if (this.flags != null) {
      returnme += "flags:\n";
      for (String s: this.flags) {
        returnme += String.format("  %s\n", s);
      }
    }
    return returnme;
  }

  // TODO needed?
  public String toJSON() {
    JSONObject jproperties = new JSONObject();
    if (this.properties != null) {
      for (String s: this.properties.keySet()) {
        jproperties.put(s, this.properties.get(s));
      }
    }

    JSONArray jflags = new JSONArray();
    if (this.flags != null) {
      for (String s: this.flags) {
        jflags.put(s);
      }
    }

    return new JSONObject()
      .put("entity", this.entity)
      .put("action", this.action)
      .put("properties", jproperties)
      .put("flags", jflags).toString();
  }
}
