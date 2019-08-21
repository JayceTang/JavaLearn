package com.jayce.thinkinginjava.typein.pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName PetCreator_14_3
 * @description
 * @date 2018/10/25 22:40
 */
public abstract class PetCreator_14_3 {
    private Random ran = new Random(47);

    public abstract List<Class<? extends Pet>> types() ;

    public Pet randomPet() {
        int i = ran.nextInt(types().size());
        try {
            return types().get(i).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for(int i=0; i<size; i++) {
            result[i] = randomPet();
        }
        return result;
    }

    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> arrayList = new ArrayList<>();
        Pet[] pets = createArray(size);
        Collections.addAll(arrayList, pets);
        return arrayList;
    }
}
