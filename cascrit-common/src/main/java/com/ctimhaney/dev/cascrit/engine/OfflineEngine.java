package com.ctimhaney.dev.cascrit.engine;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;

import com.ctimhaney.dev.cascrit.model.CritiqueObject;
import com.ctimhaney.dev.cascrit.model.CritiqueCollection;
import com.ctimhaney.dev.cascrit.model.CritiqueGroup;

public class OfflineEngine extends Engine{
  private ObjectMapper mapper;
  public OfflineEngine() {
    mapper = new ObjectMapper();
    // mapper.activateDefaultTyping(new DefaultBaseTypeLimitingValidator());
  }
  // helper methods
  public CritiqueCollection createCollection(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file")) {
      CritiqueCollection newCollection = new CritiqueCollection();
      if (writeCollectionToFile(newCollection, intake.getProperty("file"), intake.getFlag("overwrite"))) {
        return newCollection;
      }
    }
    return null;
  }

  public CritiqueCollection readCollection(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file")) {
      return readCollectionfromFile(intake.getProperty("file"));
    }
    return null;
  }
  // This is just a cp but whatever reduction (regression? I dunno man college isn't that useful)
  public CritiqueCollection updateCollection(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file", "new")) {
      CritiqueCollection newCollection = readCollectionfromFile(intake.getProperty("file"));
      if (newCollection != null && writeCollectionToFile(newCollection, intake.getProperty("new"), true)) {
        return newCollection;
      }
    }
    return null;
  }

  // This is just an rm but whatever
  public CritiqueCollection deleteCollection(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file")) {
      CritiqueCollection forsakenCollection = readCollection(intake);
      if (forsakenCollection != null) {
        File forsakenFile = new File(intake.getProperty("file"));
        if (forsakenFile.delete()) {
          return forsakenCollection;
        }
      }
    }
    return null;
  }

  public CritiqueGroup createGroup(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file", "name")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        CritiqueGroup newGroup = thisCollection.addCritiqueGroup(intake.getProperty("name"));
        if (newGroup != null) {
          writeCollectionToFile(thisCollection, intake.getProperty("file"), true);
          return newGroup;
        }
      }
    }
    return null;
  }

  public ArrayList<CritiqueGroup> readGroup(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        if (intake.getProperty("name") != null) {
          ArrayList<CritiqueGroup> oneList = new ArrayList<CritiqueGroup>();
          oneList.add(thisCollection.getCritiqueGroup(intake.getProperty("name")));
          return oneList;
        } else {
          return thisCollection.getCritiqueGroups();
        }
      }
    }
    return null;
  }

  public CritiqueGroup updateGroup(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file", "name", "new")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        CritiqueGroup newGroup = thisCollection.updateCritiqueGroup(intake.getProperty("name"), intake.getProperty("new"));
        if (newGroup != null) {
          writeCollectionToFile(thisCollection, intake.getProperty("file"), true);
          return newGroup;
        }
      }
    }
    return null;
  }

  public CritiqueGroup deleteGroup(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file", "name")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        CritiqueGroup forsakenGroup = thisCollection.deleteCritiqueGroup(intake.getProperty("name"));
        if (forsakenGroup != null) {
          writeCollectionToFile(thisCollection, intake.getProperty("file"), true);
          return forsakenGroup;
        }
      }
    }
    return null;
  }

  boolean validateIntakeProperties(EngineIntake intake, String... requiredProps) {
    boolean validProps = true;
    for (String s: requiredProps) {
      if (intake.getProperty(s) == null) {
        validProps = false;
        // TODO logger?
        System.out.printf("'%s' is a required property\n", s);
      }
    }
    return validProps;
  }

  boolean writeCollectionToFile(CritiqueCollection collection, String fileName, boolean overwrite) {
    try {
      File newfile = new File(fileName);
      if (!newfile.exists() || overwrite) {
        mapper.writeValue(newfile, collection);
        return true;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  CritiqueCollection readCollectionfromFile(String fileName) {
    try {
      return mapper.readValue(new File(fileName), CritiqueCollection.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
