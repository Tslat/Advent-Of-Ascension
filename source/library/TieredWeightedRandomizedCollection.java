package net.tslat.aoa3.library;

import net.minecraft.util.Tuple;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class TieredWeightedRandomizedCollection<A> {
    private static final Random rand = new Random();

    private ArrayList<Integer> tiers = new ArrayList<Integer>();
    private ArrayList<Integer> weights = new ArrayList<Integer>();
    private ArrayList<A> objects = new ArrayList<A>();

    private TreeMap<Integer, Tuple<Integer, Integer>> tierIndex = new TreeMap<Integer, Tuple<Integer, Integer>>();

    public void addEntry(int tier, int weight, A object) {
        if (object == null)
            return;

        int totalWeight = 0;
        int i;

        for (i = 0; i < tiers.size(); i++) {
            totalWeight += weights.get(i);

            if (tiers.get(i) > tier)
                break;
        }

        tiers.add(i, tier);
        weights.add(i, weight);
        objects.add(i, object);
        tierIndex.put(tier, new Tuple<Integer, Integer>(i, totalWeight + weight));
    }

    public A getRandomEntry(int tier) {
        int index = 1;

        for (Integer i : tierIndex.keySet()) {
            if (i > tier)
                break;

            index = i;
        }

        Tuple<Integer, Integer> tierInfo = tierIndex.get(index);
        int totalWeight = tierInfo.getSecond();
        int selection = rand.nextInt(totalWeight);

        int previousWeight = 0;
        int result = -1;

        for (int i = 0; i <= tierInfo.getFirst(); i++) {
            int currentWeight = previousWeight + weights.get(i);

            if (selection >= previousWeight && selection < currentWeight) {
                result = i;
                break;
            }

            previousWeight = currentWeight;
        }

        return objects.get(result);
    }
}
