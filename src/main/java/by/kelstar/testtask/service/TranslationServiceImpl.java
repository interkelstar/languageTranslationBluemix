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
        final TranslationResult translationResult = service.translate(text, Language.valueOf(from), Language.valueOf(to)).execute();
        return translationResult.getFirstTranslation();
    }
    
}
