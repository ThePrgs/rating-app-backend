package com.nsoft.ratingappbackend.emoji;

import com.nsoft.ratingappbackend.emoji.payload.EmojiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * EmojiController - a rest controller for emojis.
 *
 * @see Emoji
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/api/emoji")
public class EmojiController {

	private final EmojiService emojiService;

	/**
	 * API endpoint - gets all currently used emojis.
	 *
	 * @return ResponseEntity with EmojiResponse and a HttpStatus code.
	 */
	@GetMapping
	public ResponseEntity<EmojiResponse> getEmojis() {
		EmojiResponse response = new EmojiResponse();
		try {
			log.info("Getting emoji list...");
			response = emojiService.getEmojis();

			if (!response.getEmojiList().isEmpty()) {
				log.info("Emojis successfully retrieved from the database.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.warn("Emojis not found!");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("An error has occurred!");
			response.setMessage("An error has occurred!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
