package uz.sardorbroo.opennlp.name.finder.trainer;

public interface NameFinderModelTrainer extends ModelTrainer {

    /**
     * Trains new model by given dataset(.train file)
     */
    void train();

}
