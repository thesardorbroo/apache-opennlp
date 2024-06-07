package uz.sardorbroo.opennlp.shell;

import lombok.extern.slf4j.Slf4j;
import opennlp.tools.langdetect.Language;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import uz.sardorbroo.opennlp.name.finder.trainer.NameFinderModelTrainer;
import uz.sardorbroo.opennlp.name.finder.trainer.NameFinderTrainerRunner;
import uz.sardorbroo.opennlp.name.finder.trainer.enumeration.Trainers;
import uz.sardorbroo.opennlp.name.finder.trainer.impl.NameFinderTrainerRunnerImpl;
import uz.sardorbroo.opennlp.name.finder.trainer.impl.ProgrammingTechnologyFinderModelTrainer;

import java.util.Arrays;
import java.util.List;

@Slf4j
@ShellComponent
public class ModelTrainerComponent {

    private static final List<Trainers> TRAINERS = Arrays.stream(Trainers.values()).toList();

    @ShellMethod(key = "train")
    public void trainModel(@ShellOption String trainer, @ShellOption String entity, @ShellOption(defaultValue = "eng") String language) {
        log.info("Trainer: {} | Entity: {} | Language: {}", trainer, entity, language);

        NameFinderModelTrainer modelTrainer = new ProgrammingTechnologyFinderModelTrainer(
                new Language(language),
                entity,
                "model-trains/en-ner-programming-technology.train",
                "models/en-ner-programming-technology.bin"
                );
        NameFinderTrainerRunner runner = new NameFinderTrainerRunnerImpl(modelTrainer);
        runner.train();
    }

    @ShellMethod(key = "all-trainers")
    public List<String> getAllTrainers() {
        return TRAINERS.stream()
                .map(Trainers::getName)
                .toList();
    }
}
