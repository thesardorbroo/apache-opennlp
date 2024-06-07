package uz.sardorbroo.opennlp.name.finder.trainer;

import uz.sardorbroo.opennlp.name.finder.trainer.impl.ProgrammingTechnologyFinderModelTrainer;

public class TrainerRunner {

    public static void main(String[] args) {

        NameFinderModelTrainer trainer = new ProgrammingTechnologyFinderModelTrainer(null, "tech");
        trainer.train();
    }

}
