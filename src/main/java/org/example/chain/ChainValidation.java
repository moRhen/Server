package org.example.chain;

import java.util.ArrayList;
import java.util.List;
import org.example.exceptions.ValidationException;

public abstract class ChainValidation {
  private ChainValidation next;

  private static final List<String> problems = new ArrayList<>();

  public static ChainValidation link(ChainValidation first, ChainValidation... chainValidation) {
    ChainValidation head = first;
    for (ChainValidation nextInChain : chainValidation) {
      head.next = nextInChain;
      head = nextInChain;
    }

    return first;
  }

  public abstract void validate(String comment);

  protected void checkNext(String comment) {
    if (next == null) {
      if (!problems.isEmpty()) {
        throw new ValidationException(String.join("\n", problems));
      }
      return;
    }
    next.validate(comment);
  }

  protected void addProblem(String problem) {
    problems.add(problem);
  }
}
