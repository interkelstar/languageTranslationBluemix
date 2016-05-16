package by.kelstar.testtask.controller;

import by.kelstar.testtask.dto.TextDto;
import by.kelstar.testtask.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TranslationController {
    
    @Autowired
    private TranslationService translationService;
    
    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    public TextDto translate(@RequestBody TextDto textDto, @PathVariable String from, @PathVariable String to) {
        final String translation = translationService.translate(textDto.getText(), from, to);
        final TextDto resultDto = new TextDto();
        resultDto.setText(translation);
        return resultDto;
    }
    
}
