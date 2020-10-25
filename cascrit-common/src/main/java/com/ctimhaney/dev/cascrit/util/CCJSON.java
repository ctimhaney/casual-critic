package com.ctimhaney.dev.cascrit.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;

import com.ctimhaney.dev.cascrit.model.CritiqueCollection;

public class CCJSON {
  // TODO this class probably shouldn't be all static methods to improve performance
  public static String getObjectAsJSON(Object o) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.activateDefaultTyping(new DefaultBaseTypeLimitingValidator());
    return mapper.writeValueAsString(o);
  }

  public static void storeObjectAsFile(Object o, String filePath, boolean overwrite) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.activateDefaultTyping(new DefaultBaseTypeLimitingValidator());
    File newfile = new File(filePath);
    if ((!overwrite && !newfile.exists()) || overwrite)
      mapper.writeValue(new File(filePath), o);
  }

  public static CritiqueCollection loadCollectionFromFile(String filePath) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.activateDefaultTyping(new DefaultBaseTypeLimitingValidator());
    return mapper.readValue(new File(filePath), CritiqueCollection.class);
  }
}
