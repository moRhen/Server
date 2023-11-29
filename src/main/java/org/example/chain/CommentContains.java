package org.example.chain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CommentContains extends ChainValidation {

  private final List<String> forbiddenPhrase;
  @Override
  public void validate(String comment) {
    if (forbiddenPhrase.contains(comment)) {
      addProblem("Comment contain phrase on the forbidden list: " + forbiddenPhrase);
    }
    checkNext(comment);
  }
}
