package by.kelstar.testtask.service;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;
import org.springframework.stereotype.Service;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final LanguageTranslation languageTranslation = new LanguageTranslation();

    @Override
    public String translate(String text, String from, String to) {
        final TranslationResult translationResult = languageTranslation.translate(text, String.format("%s-%s", from, to))
                .execute();
        return translationResult.getFirstTranslation();
    }
    
}
