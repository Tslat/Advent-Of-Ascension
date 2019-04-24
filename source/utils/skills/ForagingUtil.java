package net.tslat.aoa3.utils.skills;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.TieredWeightedRandomizedCollection;

public class ForagingUtil {
	public static final TieredWeightedRandomizedCollection<ForagingDrop> lootTable = new TieredWeightedRandomizedCollection<ForagingDrop>();

	public static boolean shouldGetLoot(final int lvl) {
		if (lvl < 20)
			return AdventOfAscension.rand.nextInt(65) == 0;

		if (lvl < 40)
			return AdventOfAscension.rand.nextInt(55) == 0;

		if (lvl < 60)
			return AdventOfAscension.rand.nextInt(48) == 0;

		if (lvl < 80)
			return AdventOfAscension.rand.nextInt(36) == 0;

		return AdventOfAscension.rand.nextInt(29) == 0;
	}

	public static ForagingDrop getLoot(final int lvl) {
		return lootTable.getRandomEntry(lvl);
	}

	public static class ForagingDrop {
		private final ItemStack lootStack;
		public final float xp;
		private final int minAmount;
		private final int maxAmount;

		public ForagingDrop(ItemStack lootStack, float xp) {
			this.lootStack = lootStack;
			this.xp = xp;
			this.minAmount = 1;
			this.maxAmount = 1;
		}

		public ForagingDrop(ItemStack lootStack, float xp, int minimumAmount, int maximumAmount) {
			this.lootStack = lootStack;
			this.xp = xp;
			this.minAmount = minimumAmount;
			this.maxAmount = maximumAmount;
		}

		public ItemStack getLootStack() {
			ItemStack loot = lootStack.copy();

			loot.setCount(minAmount + AdventOfAscension.rand.nextInt((maxAmount - minAmount) + 1));
			return loot;
		}
	}

	public static void initDrops() {
		lootTable.addEntry(1, 100, new ForagingDrop(new ItemStack(Items.COAL),20, 1, 3));
		lootTable.addEntry(1, 100, new ForagingDrop(new ItemStack(Items.GOLD_NUGGET), 20, 1, 2));
		lootTable.addEntry(5, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceWeak), 30, 1,16));
		lootTable.addEntry(10, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceMolten), 60, 1,16));
		lootTable.addEntry(15, 100, new ForagingDrop(new ItemStack(Items.ENDER_PEARL), 100, 1,2));
		lootTable.addEntry(20, 100, new ForagingDrop(new ItemStack(Items.GUNPOWDER), 150, 1,16));
		lootTable.addEntry(25, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceCharged), 270, 1,16));
		lootTable.addEntry(30, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceOminous), 400, 1,16));
		lootTable.addEntry(35, 100, new ForagingDrop(new ItemStack(Items.IRON_INGOT), 650, 1,3));
		lootTable.addEntry(40, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceEmpowered), 850, 1,16));
		lootTable.addEntry(50, 100, new ForagingDrop(new ItemStack(Items.EXPERIENCE_BOTTLE), 1000, 1,4));
		lootTable.addEntry(55, 50, new ForagingDrop(new ItemStack(ItemRegister.realmstoneIromine), 1500, 1,1));
		lootTable.addEntry(60, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceLuminate), 2250, 1,16));
		lootTable.addEntry(65, 100, new ForagingDrop(new ItemStack(Items.DIAMOND), 3550, 1,2));
		lootTable.addEntry(70, 100, new ForagingDrop(new ItemStack(ItemRegister.skillCrystalSmall), 4000, 1,2));
		lootTable.addEntry(75, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceAncient), 6500, 1,16));
		lootTable.addEntry(80, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceDark), 8400, 1,16));
		lootTable.addEntry(85, 100, new ForagingDrop(new ItemStack(ItemRegister.ingotRosite), 10100, 1,1));
		lootTable.addEntry(88, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceEthereal), 14950, 1,16));
		lootTable.addEntry(90, 100, new ForagingDrop(new ItemStack(ItemRegister.coinGold), 19800, 1,1));
		lootTable.addEntry(95, 100, new ForagingDrop(new ItemStack(ItemRegister.essenceDivine), 24050, 1,16));
	}
}
