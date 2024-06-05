package uz.sardorbroo.opennlp.name.finder.provider;

public interface NameFinderProvider {

    String[][] findNames(String text);

    String[] findNamesInSingleSentence(String sentence);

}
