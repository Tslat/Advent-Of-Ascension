package net.tslat.aoa3.api;

import net.minecraft.item.Item;
import net.tslat.aoa3.item.weapon.staff.FungalStaff;

public final class AoAItemAPI {
	/**
	 * Adds a given food item to the blacklist for the Fungal Staff's food generation effect.<br>
	 * Food items on this list will not be given to the player when casting with the fungal staff.<br>
	 * This method <u>must</u> be called before the FMLPostInitializationEvent
	 *
	 * @param item the item to add to the blacklist. All sub-items will also be blocked
	 */
	public static void addBlacklistFoodToFungalStaff(Item item) {
		if (item == null)
			return;

		FungalStaff.blacklistFoods.add(item);
	}
}
