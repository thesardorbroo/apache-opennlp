package uz.sardorbroo.opennlp.name.finder.provider;

import opennlp.tools.util.Span;

public interface NameFinderProvider {

    Span[] findNames(String sentence);

}
