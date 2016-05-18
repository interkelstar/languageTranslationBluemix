package by.kelstar.testtask.controller;

import by.kelstar.testtask.dto.TextDto;
import by.kelstar.testtask.service.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
public class TextToSpeechController {
    
    @Autowired
    private TextToSpeechService textToSpeechService;

    @RequestMapping(value = "/textToSpeech", method = RequestMethod.POST)
    public InputStream textToSpeech(@RequestParam TextDto textDto) {
        return textToSpeechService.synthesise(textDto.getText());
    }
}
