package com.nsoft.ratingappbackend.emoji;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/emoji")
public class EmojiController {

  private final EmojiService emojiService;

  @GetMapping
  public ResponseEntity<List<Emoji>> getEmojis() {
    try {
      List<Emoji> list = emojiService.getEmojis();
      return new ResponseEntity<>(list, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
