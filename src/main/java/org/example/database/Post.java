package org.example.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  long postId;
  @Column(name = "user_id")
  int userId;
  String title;
  String body;
}
