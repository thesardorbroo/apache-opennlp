package uz.sardorbroo.opennlp.name.finder.trainer.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import opennlp.tools.langdetect.Language;
import opennlp.tools.namefind.*;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import uz.sardorbroo.opennlp.name.finder.trainer.NameFinderModelTrainer;
import uz.sardorbroo.opennlp.utils.FileUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Slf4j
public class ProgrammingTechnologyFinderModelTrainer implements NameFinderModelTrainer {
    private final TokenNameFinderFactory factory;
    private final Language language;
    private final File trainFile;
    private final File trainedModelFile;
    private final String entity;

    @SneakyThrows
    public ProgrammingTechnologyFinderModelTrainer(Language language, String entity) {
        this.language = language;
        this.entity = entity;
        this.trainFile = new File(FileUtils.getFileUrlWithResources("/opennlp-trains/en-ner-programming-technology.train").getFile());
        this.trainedModelFile = new File("src/main/resources/opennlp-models/en-ner-programming-technology.bin");
        this.factory = TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec());
    }

    @SneakyThrows
    public ProgrammingTechnologyFinderModelTrainer(Language language, String entity, String trainingFileName, String trainedModelName) {
        this.language = language;
        this.entity = entity;
        this.trainFile = new File(FileUtils.getFileUrlWithResources(trainingFileName).getFile());
        this.trainedModelFile = new File(FileUtils.getFileUrlWithResources(trainedModelName).getFile());
        this.factory = TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec());
    }

    @SneakyThrows
    @Override
    public void train() {
        log.info("Start training new programming technology model");

        ObjectStream<String> lineStream =
                new PlainTextByLineStream(new MarkableFileInputStreamFactory(trainFile), StandardCharsets.UTF_8);

        TokenNameFinderModel trainedModel;
        try (ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream)) {
            trainedModel = NameFinderME.train("en", entity, sampleStream, TrainingParameters.defaultParams(), factory);
        }

        try (OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(this.trainedModelFile))) {
            trainedModel.serialize(modelOut);
        }

        log.info("New programming technology model is trained successfully. Trained model name: {}", trainedModel);
    }
}
