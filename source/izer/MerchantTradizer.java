package net.nevermine.izer;

import net.minecraft.village.MerchantRecipeList;
import net.nevermine.common.nevermine;
import net.nevermine.npc.entity.RestockedRecipe;

import java.util.ArrayList;
import java.util.Collections;

public class MerchantTradizer {
	public static void getRandomTrades(final MerchantRecipeList list, ArrayList<RestockedRecipe> recipePool) {
		int size = Math.max(nevermine.rand.nextInt(recipePool.size()), 1);

		for (int i = 0; i < size; i++) {
			int pick = nevermine.rand.nextInt(recipePool.size() - i);

			list.add(recipePool.get(pick));
			Collections.swap(recipePool, i, size - 1);
		}
	}
}
