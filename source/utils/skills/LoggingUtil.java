package net.tslat.aoa3.utils.skills;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.TieredWeightedRandomizedCollection;

public class LoggingUtil {
	public static final TieredWeightedRandomizedCollection<LoggingDrop> lootTable = new TieredWeightedRandomizedCollection<LoggingDrop>();

	public static boolean shouldGetLoot(final int lvl) {
		if (lvl < 20)
			return AdventOfAscension.rand.nextInt(25) == 0;

		if (lvl < 40)
			return AdventOfAscension.rand.nextInt(20) == 0;

		if (lvl < 60)
			return AdventOfAscension.rand.nextInt(16) == 0;

		if (lvl < 80)
			return AdventOfAscension.rand.nextInt(12) == 0;

		return AdventOfAscension.rand.nextInt(9) == 0;
	}

	public static LoggingDrop getLoot(final int lvl) {
		return lootTable.getRandomEntry(lvl);
	}
	
	public static class LoggingDrop {
		private final ItemStack lootStack;
		public final float xp;
		private final int minAmount;
		private final int maxAmount;

		public LoggingDrop(ItemStack lootStack, float xp) {
			this.lootStack = lootStack;
			this.xp = xp;
			this.minAmount = 1;
			this.maxAmount = 1;
		}

		public LoggingDrop(ItemStack lootStack, float xp, int minimumAmount, int maximumAmount) {
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
		lootTable.addEntry(1, 100, new LoggingDrop(new ItemStack(Items.STICK), 15, 1, 16));
		lootTable.addEntry(1, 100, new LoggingDrop(new ItemStack(Items.APPLE), 25, 1, 4));
		lootTable.addEntry(10, 100, new LoggingDrop(new ItemStack(ItemRegister.seedsTea), 40, 1, 2));
		lootTable.addEntry(20, 100, new LoggingDrop(new ItemStack(Items.EXPERIENCE_BOTTLE), 90, 3, 9));
		lootTable.addEntry(30, 100, new LoggingDrop(new ItemStack(ItemRegister.essenceAncient), 150, 1, 16));
		lootTable.addEntry(40, 100, new LoggingDrop(new ItemStack(ItemRegister.fragmentedAnimaStone), 360, 1, 2));
		lootTable.addEntry(50, 100, new LoggingDrop(new ItemStack(ItemRegister.gemBag), 500, 1, 3));
		lootTable.addEntry(60, 100, new LoggingDrop(new ItemStack(ItemRegister.essenceDark), 950, 1, 16));
		lootTable.addEntry(70, 100, new LoggingDrop(new ItemStack(ItemRegister.essenceEthereal), 1650, 1, 16));
		lootTable.addEntry(80, 100, new LoggingDrop(new ItemStack(ItemRegister.essenceDivine), 2450, 1, 16));
		lootTable.addEntry(90, 100, new LoggingDrop(new ItemStack(ItemRegister.skillCrystalMedium), 6200, 1, 2));
		lootTable.addEntry(95, 100, new LoggingDrop(new ItemStack(ItemRegister.ingotRosite), 9500, 1, 1));
	}
}
