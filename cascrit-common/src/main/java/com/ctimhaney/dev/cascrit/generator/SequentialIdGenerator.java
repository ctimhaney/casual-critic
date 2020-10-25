package com.ctimhaney.dev.cascrit.generator;

import java.util.ArrayList;

import com.ctimhaney.dev.cascrit.model.CritiqueObject;

public class SequentialIdGenerator implements IdGenerator {
  private int last = -1;

  public SequentialIdGenerator() {}

  public SequentialIdGenerator(ArrayList<CritiqueObject> list) {
    for (CritiqueObject o: list) {
      if (o.getObjectId() > last) {
        last = o.getObjectId();
      }
    }
  }

  public int next() {
    return ++last;
  }
}
