package by.kelstar.testtask.service;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;
import org.springframework.stereotype.Service;

@Service("translationService")
public class TranslationServiceImpl implements TranslationService {
    
    @Override
    public String translate(String text, String from, String to) {
        final LanguageTranslation service = new LanguageTranslation();
        final TranslationResult translationResult = service.translate(text, languageFromTag(from), languageFromTag(to)).execute();
        return translationResult.getFirstTranslation();
    }

    private Language languageFromTag(String languageTag) {
        for (Language language : Language.values()) {
            if (language.toString().equals(languageTag)) {
                return language;
            }
        }
        return null;
    }
    
}
