package com.ctimhaney.dev.cascrit.generator;

import java.util.ArrayList;

import com.ctimhaney.dev.cascrit.model.CritiqueObject;

public class IdGeneratorFactory {
  public static IdGenerator getInstance(ArrayList<CritiqueObject> list) {
    // TODO determine the ID type of the list. Do we need a list class?
    return new SequentialIdGenerator(list);
  }
}
