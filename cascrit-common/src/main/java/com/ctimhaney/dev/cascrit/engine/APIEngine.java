package com.ctimhaney.dev.cascrit.engine;

import java.util.ArrayList;

import com.ctimhaney.dev.cascrit.model.CritiqueCollection;
import com.ctimhaney.dev.cascrit.model.CritiqueGroup;
import com.ctimhaney.dev.cascrit.model.CasualCritique;

public class APIEngine extends Engine{
  public APIEngine () {}

  public CritiqueCollection createCollection(EngineIntake intake) {return null;}
  public CritiqueCollection readCollection(EngineIntake intake) {return null;}
  public CritiqueCollection updateCollection(EngineIntake intake) {return null;}
  public CritiqueCollection deleteCollection(EngineIntake intake) {return null;}

  public CritiqueGroup createGroup(EngineIntake intake) {return null;}
  public ArrayList<CritiqueGroup> readGroup(EngineIntake intake) {return null;}
  public CritiqueGroup updateGroup(EngineIntake intake) {return null;}
  public CritiqueGroup deleteGroup(EngineIntake intake) {return null;}

  public CasualCritique createCritique(EngineIntake intake) {return null;}
  public ArrayList<CasualCritique> readCritique(EngineIntake intake) {return null;}
  public CasualCritique updateCritique(EngineIntake intake) {return null;}
  public CasualCritique deleteCritique(EngineIntake intake) {return null;}

}
