package Model;

import com.github.javafaker.DateAndTime;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public interface IRandomSetElement {

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
