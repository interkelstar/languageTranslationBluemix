package by.kelstar.testtask.controller;

import by.kelstar.testtask.dto.TextDto;
import by.kelstar.testtask.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TranslationController {
    
    private final TranslationService translationService;

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public TextDto translate(@RequestBody TextDto textDto, @RequestParam String from, @RequestParam String to) {
        final String translation = translationService.translate(textDto.getText(), from, to);
        final TextDto resultDto = new TextDto();
        resultDto.setText(translation);
        return resultDto;
    }
    
}
