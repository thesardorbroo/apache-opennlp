package uz.sardorbroo.opennlp.name.finder.trainer;

import opennlp.tools.langdetect.Language;
import uz.sardorbroo.opennlp.name.finder.trainer.impl.ProgrammingTechnologyFinderModelTrainer;

public class NameFinderTrainerRunner {

    public static void main(String[] args) {

        var language = new Language("eng");
        var entity = "tech";
        NameFinderModelTrainer trainer = new ProgrammingTechnologyFinderModelTrainer(language, entity);
        trainer.train();
    }
}
