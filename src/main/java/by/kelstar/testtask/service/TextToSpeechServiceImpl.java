package by.kelstar.testtask.service;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service("textToSpeech")
public class TextToSpeechServiceImpl implements TextToSpeechService {
    @Override
    public InputStream synthesise(String text) {
        TextToSpeech service = new TextToSpeech();
        return service.synthesize(text, Voice.EN_ALLISON).execute();
    }
}
