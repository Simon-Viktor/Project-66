package Model;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Functional interface for random element picking from sets
 */
public interface IRandomSetElement {

    /**
     * Returns a random element from a given set.
     * @param set The set from which the random element will be picked.
     * @return a random element from the set.
     */
    static <E> E getRandomElement(Set<? extends E> set){
        Random random = new Random();
        int randomNumber = random.nextInt(set.size());
        Iterator<? extends E> iterator = set.iterator();

        int currentIndex = 0;
        E randomElement = null;
        while(iterator.hasNext()){

            randomElement = iterator.next();
            if(currentIndex == randomNumber)
                return randomElement;
            currentIndex++;
        }

        return randomElement;
    }
}
