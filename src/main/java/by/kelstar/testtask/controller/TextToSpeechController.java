package by.kelstar.testtask.controller;

import by.kelstar.testtask.service.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class TextToSpeechController {
    
    private final TextToSpeechService textToSpeechService;

    @Autowired
    public TextToSpeechController(TextToSpeechService textToSpeechService) {
        this.textToSpeechService = textToSpeechService;
    }

    @RequestMapping(value = "/textToSpeech", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> textToSpeech(@RequestParam String text, @RequestParam String lang) {
        final InputStream speech = textToSpeechService.synthesise(text, lang);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/wav"))
                .body(new InputStreamResource(speech));
    }
    
}
