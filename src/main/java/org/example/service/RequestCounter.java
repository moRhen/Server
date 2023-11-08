package org.example.service;

import lombok.Getter;

@Getter
public class RequestCounter {

  private static RequestCounter instance;

  private int requestCount = 0;

  private RequestCounter() {}

  public static RequestCounter getRequestCounter() {
    if (instance == null) {
      instance = new RequestCounter();
    }
    return instance;
  }

  public void addRequestCount() {
    requestCount++;
  }
}
