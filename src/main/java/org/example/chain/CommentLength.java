package org.example.chain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentLength extends ChainValidation {
  private final int maxCommentLength;

  @Override
  public void validate(String comment) {
    if (comment.length() > maxCommentLength) {
      addProblem("Comment to long. Max length: " + maxCommentLength);
    }
    checkNext(comment);
  }
}
