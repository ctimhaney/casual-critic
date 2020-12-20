package com.ctimhaney.dev.cascrit.model;

public class CasualCritique extends CritiqueObject{
  private int critiqueGroupId;
  private String critiqueTitle;
  private Adequacy adequacy;
  private double rating;
  private String critiqueBody;

  public CasualCritique() {
    super();
  }

  public CasualCritique(int objectId, int critiqueGroupId, String critiqueTitle) {
    super(objectId);
    this.critiqueGroupId = critiqueGroupId;
    this.critiqueTitle = critiqueTitle;
  }

  public CasualCritique(int objectId, int critiqueGroupId, String critiqueTitle, Adequacy adequacy, double rating, String critiqueBody) {
    super(objectId);
    this.critiqueGroupId = critiqueGroupId;
    this.critiqueTitle = critiqueTitle;
    this.adequacy = adequacy;
    this.rating = rating;
    this.critiqueBody = critiqueBody;
  }

  public int getCritiqueGroupId() { return this.critiqueGroupId; }
  public void setCritiqueGroupId(int critiqueGroupId) { this.critiqueGroupId = critiqueGroupId; }

  public String getCritiqueTitle() { return this.critiqueTitle; }
  public void setCritiqueTitle(String critiqueTitle) { this.critiqueTitle = critiqueTitle; }

  public Adequacy getAdequacy() { return this.adequacy; }
  public void setAdequacy(Adequacy adequacy) { this.adequacy = adequacy; }

  public double getRating() { return this.rating; }
  public void setRating(double rating) { this.rating = rating; }

  public String getCritiqueBody() { return this.critiqueBody; }
  public void setCritiqueBody(String critiqueBody) { this.critiqueBody = critiqueBody; }

  public String toString() {
    return String.format("{objectId: %s, critiqueGroupId: %s, critiqueTitle: %s, adequacy: %s, rating: %s, critiqueBody: %s}", this.objectId, this.critiqueGroupId, this.critiqueTitle, this.adequacy, this.rating, this.critiqueBody);
  }
}
