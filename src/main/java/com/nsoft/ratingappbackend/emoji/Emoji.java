package com.nsoft.ratingappbackend.emoji;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "emoji")
public class Emoji {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "color")
  private String color;

  @Column(name = "image")
  private String image;

  public Emoji(String name, String color, String image) {

    this.name = name;
    this.color = color;
    this.image = image;
  }
}
