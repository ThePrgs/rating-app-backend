package com.nsoft.ratingappbackend.emoji;

import com.nsoft.ratingappbackend.emoji.payload.EmojiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller holds api endpoints for getting emojis
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/emoji")
public class EmojiController {

	private final EmojiService emojiService;

	/**
	 * Getter for emojis on "api/emoji endpoint
	 *
	 * @return ResponseEntity
	 */
	@GetMapping
	public ResponseEntity<EmojiResponse> getEmojis() {
		EmojiResponse response = new EmojiResponse();
		try {
			response = emojiService.getEmojis();

			if (!response.getEmojiList().isEmpty()) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMessage("An error has occurred.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
