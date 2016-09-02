package by.kelstar.testtask.service;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class TextToSpeechServiceImpl implements TextToSpeechService {

    private final TextToSpeech textToSpeech = new TextToSpeech();
    private Map<String, Voice> voicesByLang = new HashMap<String, Voice>() {{
        put("en", Voice.EN_ALLISON);
        put("es", Voice.ES_ENRIQUE);
        put("fr", Voice.FR_RENEE);
        put("it", Voice.IT_FRANCESCA);
        put("pt", new Voice("pt-BR_IsabelaVoice", "female", "pt-BR"));
    }};

    @Override
    public InputStream synthesise(String text, String lang) {
        Voice voice = voicesByLang.get(lang);
        if (voice == null) {
            throw new IllegalArgumentException("There is no voice for this language");
        }
        return textToSpeech.synthesize(text, voice, AudioFormat.WAV).execute();
    }
}
