package com.ctimhaney.dev.cascrit.model;

public abstract class CritiqueObject {
  protected int objectId;

  public CritiqueObject() {}
  public CritiqueObject(int objectId) {
    this.objectId = objectId;
  }

  public int getObjectId() { return this.objectId; }
  public void setObjectId(int objectId) { this.objectId = objectId; }
}
