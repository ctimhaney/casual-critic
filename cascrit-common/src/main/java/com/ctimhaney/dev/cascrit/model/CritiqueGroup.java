package com.ctimhaney.dev.cascrit.model;

public class CritiqueGroup extends CritiqueObject{
  private String groupName; //our "key" for now. nothing else here but whatever. object refs ftw I guess

  public CritiqueGroup() {
    super();
  }

  public CritiqueGroup(int objectID, String groupName) {
    super(objectID);
    this.groupName = groupName;
  }

  public String getGroupName() { return this.groupName;}
  public void setGroupName(String groupName) { this.groupName = groupName; }

  public String toString() {
    return String.format("{objectId: %s, groupName: %s}", this.objectId, this.groupName);
  }
}
