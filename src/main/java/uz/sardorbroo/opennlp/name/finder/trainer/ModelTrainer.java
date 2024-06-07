package uz.sardorbroo.opennlp.name.finder.trainer;

public interface ModelTrainer {

    /**
     * Returns ID of model trainer.
     * It is used for recognizing trainer
     *
     * @return {@link String}
     */
    String getId();

}
