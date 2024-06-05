package uz.sardorbroo.opennlp.sentence.detector.provider.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.apache.commons.lang3.StringUtils;
import uz.sardorbroo.opennlp.sentence.detector.provider.SentenceDetectorProvider;
import uz.sardorbroo.opennlp.utils.ModelUtils;

@Slf4j
public class SampleSentenceDetectorProviderImpl implements SentenceDetectorProvider {
    private final SentenceDetector detector;
    private String model = "/opennlp-models/en-sent.bin";

    @SneakyThrows
    public SampleSentenceDetectorProviderImpl() {
        this.detector = new SentenceDetectorME(new SentenceModel(ModelUtils.getModelInputStream(this.model)));
    }

    @SneakyThrows
    public SampleSentenceDetectorProviderImpl(String model) {
        this.model = model;
        this.detector = new SentenceDetectorME(new SentenceModel(ModelUtils.getModelInputStream(this.model)));
    }

    @Override
    public String[] detect(String text) {
        log.info("Detecting sentences from whole text. Text: {}", text);

        if (StringUtils.isBlank(text)) {
            log.warn("Invalid argument is passed! Text model must not be blank!");
            throw new IllegalArgumentException("Invalid argument is passed! Text model must not be blank!");
        }

        String[] sentences = detector.sentDetect(text);
        log.info("Sentences are detected successfully. Sentences count: {}", sentences.length);
        return sentences;
    }
}
