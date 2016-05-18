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
//        service.setUsernameAndPassword("b0ae0f7a-be9b-438c-a74d-154034e908e4", "2h3qtDtiSruc");
        final TranslationResult translationResult = service.translate(text, String.format("%s-%s", from, to)).execute();
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
