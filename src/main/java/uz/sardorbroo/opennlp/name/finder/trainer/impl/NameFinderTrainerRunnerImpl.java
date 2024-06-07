package uz.sardorbroo.opennlp.name.finder.trainer.impl;

import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.opennlp.name.finder.trainer.NameFinderModelTrainer;
import uz.sardorbroo.opennlp.name.finder.trainer.NameFinderTrainerRunner;

@Slf4j
public class NameFinderTrainerRunnerImpl implements NameFinderTrainerRunner {

    private final NameFinderModelTrainer trainer;

    public NameFinderTrainerRunnerImpl(NameFinderModelTrainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean train() {
        log.info("Abs trainer start to train models");

        try {
            trainer.train();
        } catch (Exception e) {
            log.error("Error while training model. Trainer: {}", trainer);
            log.error("Exception", e);
            return false;
        }

        log.debug("Model is trained successfully. Trainer: {}", trainer);
        return true;
    }
}
