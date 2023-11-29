package org.example.chain;


public class CommentNotEmpty extends ChainValidation {

  @Override
  public void validate(String comment) {
    if (comment.isEmpty()) {
      addProblem("Comment cannot be empty");
    }
    checkNext(comment);
  }
}
