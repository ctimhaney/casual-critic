package com.ctimhaney.dev.cascrit.model;

public class CasualCritique {
  private CritiqueGroup critiqueGroup;
  private String critiqueTitle; // our "key" for now
  private boolean adequacy;
  private int rating;
  private String critiqueBody;

  public CasualCritique() {}

  public CasualCritique(CritiqueGroup critiqueGroup, String critiqueTitle, boolean adequacy, int rating, String critiqueBody) {
    this.critiqueGroup = critiqueGroup;
    this.critiqueTitle = critiqueTitle;
    this.adequacy = adequacy;
    this.rating = rating;
    this.critiqueBody = critiqueBody;
  }

  public CritiqueGroup getCritiqueGroup() { return this.critiqueGroup; }
  public void setCritiqueGroup(CritiqueGroup critiqueGroup) { this.critiqueGroup = critiqueGroup; }

  public String getCritiqueTitle() { return this.critiqueTitle; }
  public void setCritiqueTitle(String critiqueTitle) { this.critiqueTitle = critiqueTitle; }

  public boolean getAdequacy() { return this.adequacy; }
  public void setAdequacy(boolean adequacy) { this.adequacy = adequacy; }

  public int getRating() { return this.rating; }
  public void setRating(int rating) { this.rating = rating; }

  public String getCritiqueBody() { return this.critiqueBody; }
  public void setCritiqueBody(String critiqueBody) { this.critiqueBody = critiqueBody; }

  public String toString() {
    return String.format("groupname: %s\ntitle: %s\nadequacy: %s\nrating: %s\nbody: %s\n", this.critiqueGroup.getGroupName(), this.critiqueTitle, this.adequacy, this.rating, this.critiqueBody);
  }
}
