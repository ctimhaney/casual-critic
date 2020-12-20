package com.ctimhaney.dev.cascrit.engine;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;

import com.ctimhaney.dev.cascrit.model.CritiqueCollection;
import com.ctimhaney.dev.cascrit.model.CritiqueGroup;
import com.ctimhaney.dev.cascrit.model.CasualCritique;
import com.ctimhaney.dev.cascrit.model.Adequacy;

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
        ArrayList<CritiqueGroup> oneList = new ArrayList<CritiqueGroup>();
        if (intake.getProperty("name") != null) {
          oneList.add(thisCollection.getCritiqueGroup(intake.getProperty("name")));
          return oneList;
        } else if (intake.getProperty("id") != null) {
          try {
            oneList.add(thisCollection.getCritiqueGroup(Integer.parseInt(intake.getProperty("id"))));
          } catch (NumberFormatException e) {}
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

  public CasualCritique createCritique(EngineIntake intake) {
    // TODO allow group ID? (group,groupid) or just default to all ids. The multifunctional approach is a drag
    if (validateIntakeProperties(intake, "file", "group", "title")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        Adequacy adequacy = Adequacy.UNDECIDED;
        if (intake.getProperty("adequacy") != null) {
          if (intake.getProperty("adequacy").equals("true") || intake.getProperty("adequacy").equals("yes")) {
            adequacy = Adequacy.ADEQUATE;
          } else if (intake.getProperty("adequacy").equals("false") || intake.getProperty("adequacy").equals("no")) {
            adequacy = Adequacy.INADEQUATE;
          } else if (intake.getProperty("adequacy").equals("undecided") || intake.getProperty("adequacy").equals("none")) {
            adequacy = Adequacy.UNDECIDED;
          }
        }
        double rating = 0;
        if (intake.getProperty("rating") != null) {
          try {
            rating = Double.parseDouble(intake.getProperty("rating"));
          } catch (NumberFormatException e) {}
        }
        CasualCritique newCritique = thisCollection.addCasualCritique(intake.getProperty("group"), intake.getProperty("title"), adequacy, rating, intake.getProperty("body"));
        if (newCritique != null) {
          writeCollectionToFile(thisCollection, intake.getProperty("file"), true);
        }
        return newCritique;
      }
    }
    return null;
  }

  public ArrayList<CasualCritique> readCritique(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        ArrayList<CasualCritique> oneList = new ArrayList<CasualCritique>();
        if (intake.getProperty("title") != null) {
          oneList.add(thisCollection.getCasualCritique(intake.getProperty("title")));
          return oneList;
        } else if (intake.getProperty("id") != null) {
          try {
            oneList.add(thisCollection.getCasualCritique(Integer.parseInt(intake.getProperty("id"))));
          } catch (NumberFormatException e) {}
          return oneList;
        } else {
          return thisCollection.getCasualCritiques();
        }
      }
    }
    return null;
  }

  public CasualCritique updateCritique(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        // defaults for values we need to parse
        int newGroupID = -1;
        Adequacy newAdequacy = Adequacy.NOACTION;
        double newRating = -1;

        // input validation here
        if (intake.getProperty("groupid") != null) {
          try {
            newGroupID = Integer.parseInt(intake.getProperty("groupid"));
          } catch (NumberFormatException e) {}
        }
        if (intake.getProperty("adequacy") != null) {
          if (intake.getProperty("adequacy").equals("true") || intake.getProperty("adequacy").equals("yes")) {
            newAdequacy = Adequacy.ADEQUATE;
          } else if (intake.getProperty("adequacy").equals("false") || intake.getProperty("adequacy").equals("no")) {
            newAdequacy = Adequacy.INADEQUATE;
          } else if (intake.getProperty("adequacy").equals("undecided") || intake.getProperty("adequacy").equals("none")) {
            newAdequacy = Adequacy.UNDECIDED;
          }
        }
        if (intake.getProperty("rating") != null) {
          try {
            newRating = Double.parseDouble(intake.getProperty("rating"));
          } catch (NumberFormatException e) {}
        }

        CasualCritique thisCritique = null;
        if (intake.getProperty("title") != null) {
          thisCritique = thisCollection.updateCritique(intake.getProperty("title"), newGroupID, intake.getProperty("new"), newAdequacy, newRating, intake.getProperty("body"));
        } else if (intake.getProperty("id") != null) {
          thisCritique = thisCollection.updateCritique(Integer.parseInt(intake.getProperty("id")), newGroupID, intake.getProperty("new"), newAdequacy, newRating, intake.getProperty("body"));
        }

        if (thisCritique != null) {
          writeCollectionToFile(thisCollection, intake.getProperty("file"), true);
        }
        return thisCritique;
      }
    }
    return null;
  }
  public CasualCritique deleteCritique(EngineIntake intake) {
    if (validateIntakeProperties(intake, "file")) {
      CritiqueCollection thisCollection = readCollectionfromFile(intake.getProperty("file"));
      if (thisCollection != null) {
        CasualCritique forsakenCritique = null;
        if (intake.getProperty("title") != null) {
          forsakenCritique = thisCollection.deleteCasualCritique(intake.getProperty("title"));
        } else if (intake.getProperty("id") != null) {
          try {
            forsakenCritique = thisCollection.deleteCasualCritique(Integer.parseInt(intake.getProperty("id")));
          } catch (NumberFormatException e) {}
        }
        if (forsakenCritique != null) {
          writeCollectionToFile(thisCollection, intake.getProperty("file"), true);
        }
        return forsakenCritique;
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
