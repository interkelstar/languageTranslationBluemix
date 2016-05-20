package by.kelstar.testtask.service;

import java.io.InputStream;

public interface TextToSpeechService {
    InputStream synthesise(String text, String lang);
}
