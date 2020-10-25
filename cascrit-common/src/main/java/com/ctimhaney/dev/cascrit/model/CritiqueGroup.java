package com.ctimhaney.dev.cascrit.model;

public class CritiqueGroup{
  private String groupName; //our "key" for now. nothing else here but whatever. object refs ftw I guess

  public CritiqueGroup() {}

  public CritiqueGroup(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupName() { return this.groupName;}
  public void setGroupName(String groupName) { this.groupName = groupName; }

  public String toString() {
    return this.groupName;
  }
}
