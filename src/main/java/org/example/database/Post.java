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
  int id;
  @Column(name = "user_id")
  int userId;
  String title;
  String body;
}
