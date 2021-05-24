package Organisms;

import java.util.Comparator;

public class InitiativeComparator implements Comparator<Organism> {

    @Override
    public int compare(Organism o1, Organism o2) {
        return Integer.compare(o1.getInitiative(),o2.getInitiative());
    }
}
