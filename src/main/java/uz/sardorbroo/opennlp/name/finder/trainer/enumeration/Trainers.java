package uz.sardorbroo.opennlp.name.finder.trainer.enumeration;

import lombok.Getter;

@Getter
public enum Trainers {
    PROGRAMMING_TECH("programming-tech"),
    ;

    private final String name;

    Trainers(String name) {
        this.name = name;
    }
}
