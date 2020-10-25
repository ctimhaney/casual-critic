package com.ctimhaney.dev.cascrit.cli;

import java.util.Scanner;
import java.io.IOException;

import com.ctimhaney.dev.cascrit.model.CritiqueCollection;
import com.ctimhaney.dev.cascrit.model.CritiqueObject;
import com.ctimhaney.dev.cascrit.model.CritiqueGroup;
import com.ctimhaney.dev.cascrit.model.CasualCritique;
import com.ctimhaney.dev.cascrit.engine.EngineIntake;
import com.ctimhaney.dev.cascrit.engine.OfflineEngine;
import com.ctimhaney.dev.cascrit.util.CCJSON;

public class CasualCriticCLI {
  private static OfflineEngine offlineEngine;

  public static void main(String[] args) {
    offlineEngine = new OfflineEngine();
    if (args.length > 0) {
      EngineIntake engineIntake = interpretArgs(args);
      if (engineIntake.getEntity() != null && engineIntake.getEntity().equals("shell")) {
        shellInterpreter();
      } else {
        enactCommand(engineIntake);
      }
    } else {
      usage();
    }
  }

  static void shellInterpreter() {
    boolean done = false;
    Scanner in = new Scanner(System.in);
    while (!done) {
      System.out.print("ccsh$ ");
      String input = in.nextLine().trim();
      if (input.equals("exit"))
        done = true;
      else if (!input.equals(""))
        // TODO Can't just split by spaces! Find a shlex!
        enactCommand(interpretArgs(input.split(" ")));
    }
  }

  static void usage() {
    System.out.println("collection create -file <>");
    System.out.println("collection read -file <>");
    System.out.println("collection update -file <> -new <>");
    System.out.println("collection delete -file <>");
    System.out.println("group create -file <> -name <>");
    System.out.println("group read -file <> [-name <>]");
    System.out.println("group update -file <> -name <> -new <>");
    System.out.println("group delete -file <> -name <>");
  }

  static EngineIntake interpretArgs(String[] args){
    EngineIntake engineIntake = new EngineIntake();
    for (int i = 0; i < args.length; i++) {
      // we have a flag or an opt
      if (args[i].charAt(0) == '-' && args[i].length() >= 2) {
        if (args[i].charAt(1) != '-' && i + 1 < args.length) {
          // add opt
          engineIntake.addProperty(args[i].substring(1,args[i].length()), args[++i]);
        } else if (args[i].charAt(1) == '-' && args[i].length() >= 3) {
          // add flag
          engineIntake.addFlag(args[i].substring(2,args[i].length()));
        }
      } else if (args[i].charAt(0) != '-' && engineIntake.getEntity() == null) {
        // prioritize entity
        engineIntake.setEntity(args[i]);
      } else if (args[i].charAt(0) != '-' && engineIntake.getAction() == null) {
        // narg action
        engineIntake.setAction(args[i]);
      }
    }
    return engineIntake;
  }

  static void enactCommand(EngineIntake engineIntake) {
    if (engineIntake.getEntity() != null && engineIntake.getAction() != null) {
      if (engineIntake.getEntity().equals("help")) {
        usage();
      } else {
        // TODO this will never show file warnings from engine
        if (engineIntake.getProperty("file") == null) {
          engineIntake.addProperty("file", "default_collection.json");
        }
        if (engineIntake.getFlag("debug")) {
          System.out.println(engineIntake);
        }
        String enacted = offlineEngine.processIntake(engineIntake);
        if (enacted != null) {
          System.out.println(enacted);
        }
      }
    } else {
      usage();
    }
  }
}
