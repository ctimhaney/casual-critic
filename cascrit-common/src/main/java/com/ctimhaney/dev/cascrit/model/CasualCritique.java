package com.ctimhaney.dev.cascrit.model;

public class CasualCritique extends CritiqueObject{
  private int critiqueGroupId;
  private String critiqueTitle; // our "key" for now
  private boolean adequacy;
  private int rating;
  private String critiqueBody;

  public CasualCritique() {
    super();
  }

  public CasualCritique(int objectId, int critiqueGroupId, String critiqueTitle) {
    super(objectId);
    this.critiqueGroupId = critiqueGroupId;
    this.critiqueTitle = critiqueTitle;
  }

  public CasualCritique(int objectId, int critiqueGroupId, String critiqueTitle, boolean adequacy, int rating, String critiqueBody) {
    super(objectId);
    this.critiqueGroupId = critiqueGroupId;
    this.critiqueTitle = critiqueTitle;
    this.adequacy = adequacy;
    this.rating = rating;
    this.critiqueBody = critiqueBody;
  }

  public int getCritiqueGroupId() { return this.critiqueGroupId; }
  public void setCritiqueGroup(int critiqueGroupId) { this.critiqueGroupId = critiqueGroupId; }

  public String getCritiqueTitle() { return this.critiqueTitle; }
  public void setCritiqueTitle(String critiqueTitle) { this.critiqueTitle = critiqueTitle; }

  public boolean getAdequacy() { return this.adequacy; }
  public void setAdequacy(boolean adequacy) { this.adequacy = adequacy; }

  public int getRating() { return this.rating; }
  public void setRating(int rating) { this.rating = rating; }

  public String getCritiqueBody() { return this.critiqueBody; }
  public void setCritiqueBody(String critiqueBody) { this.critiqueBody = critiqueBody; }

  public String toString() {
    return String.format("{objectId: %s, critiqueGroupId: %s, critiqueTitle: %s, adequacy: %s, rating: %s, critiqueBody: %s}", this.objectId, this.critiqueGroupId, this.critiqueTitle, this.adequacy, this.rating, this.critiqueBody);
  }
}
