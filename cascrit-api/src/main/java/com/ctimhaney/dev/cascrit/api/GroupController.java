package com.ctimhaney.dev.cascrit.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctimhaney.dev.cascrit.model.CritiqueCollection;
import com.ctimhaney.dev.cascrit.model.CritiqueGroup;

@RestController
public class GroupController {

  private CritiqueCollection collection;

  public GroupController() {
    // TODO put this in a file
    this.collection = new CritiqueCollection();
    this.collection.addCritiqueGroup("wine");
  }

  @GetMapping("/groups")
  public List<CritiqueGroup> getCritiqueGroups() {
    return (List<CritiqueGroup>)(this.collection.getCritiqueGroups());
  }




}
