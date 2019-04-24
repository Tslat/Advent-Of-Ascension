package net.tslat.aoa3.utils.skills;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.library.TieredWeightedRandomizedCollection;

public class HaulingUtil {
	public static final TieredWeightedRandomizedCollection<HaulingDrop> lootTable = new TieredWeightedRandomizedCollection<HaulingDrop>();

	public static int getSkillCrystalPossibilities(final int lvl) {
		if (lvl < 25)
			return 1;

		if (lvl < 50)
			return 2;

		if (lvl < 75)
			return 3;

		return 4;
	}

	public static int getTreasureBoxRolls(final int lvl) {
		if (lvl < 11)
			return 1;

		if (lvl < 26)
			return 2;

		if (lvl < 41)
			return 3;

		if (lvl < 56)
			return 4;

		if (lvl < 71)
			return 5;

		if (lvl < 86)
			return 6;

		if (lvl < 96)
			return 7;

		return 8;
	}

	public static int getRunesLootCount(final int lvl) {
		if (lvl <= 15)
			return 1;

		if (lvl <= 25)
			return 3;

		if (lvl <= 35)
			return 5;

		if (lvl <= 55)
			return 8;

		if (lvl <= 65)
			return 11;

		if (lvl <= 75)
			return 15;

		if (lvl <= 85)
			return 17;

		if (lvl <= 95)
			return 19;

		return 21;
	}

	public static HaulingDrop getLoot(final int lvl) {
		return lootTable.getRandomEntry(lvl);
	}

	public static class HaulingDrop {
		private final ItemStack lootStack;
		public final float xp;
		private final int minAmount;
		private final int maxAmount;

		public HaulingDrop(ItemStack lootStack, float xp) {
			this.lootStack = lootStack;
			this.xp = xp;
			this.minAmount = 1;
			this.maxAmount = 1;
		}

		public HaulingDrop(ItemStack lootStack, float xp, int minimumAmount, int maximumAmount) {
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
		lootTable.addEntry(1, 200, new HaulingDrop(new ItemStack(Items.FISH), 30));
		lootTable.addEntry(1, 200, new HaulingDrop(new ItemStack(ItemRegister.fingerfish), 60));
		lootTable.addEntry(1, 1, new HaulingDrop(new ItemStack(ItemRegister.oldBoot), 60));
		lootTable.addEntry(3, 200, new HaulingDrop(new ItemStack(ItemRegister.pearlStripefish), 100));
		lootTable.addEntry(5, 200, new HaulingDrop(new ItemStack(ItemRegister.limefish), 130));
		lootTable.addEntry(9, 200, new HaulingDrop(new ItemStack(ItemRegister.sailback), 180));
		lootTable.addEntry(14, 200, new HaulingDrop(new ItemStack(ItemRegister.goldenGullfish), 250));
		lootTable.addEntry(18, 200, new HaulingDrop(new ItemStack(ItemRegister.turquoiseStripefish), 380));
		lootTable.addEntry(25, 100, new HaulingDrop(new ItemStack(ItemRegister.runeBox), 1000));
		lootTable.addEntry(30, 200, new HaulingDrop(new ItemStack(ItemRegister.violetSkipper), 600));
		lootTable.addEntry(35, 200, new HaulingDrop(new ItemStack(ItemRegister.rocketfish), 840));
		lootTable.addEntry(42, 200, new HaulingDrop(new ItemStack(ItemRegister.crimsonStripefish), 1090));
		lootTable.addEntry(50, 80, new HaulingDrop(new ItemStack(ItemRegister.fishCase), 8000));
		lootTable.addEntry(56, 200, new HaulingDrop(new ItemStack(ItemRegister.crimsonSkipper), 1900));
		lootTable.addEntry(61, 200, new HaulingDrop(new ItemStack(ItemRegister.sapphireStrider), 2420));
		lootTable.addEntry(65, 200, new HaulingDrop(new ItemStack(ItemRegister.candlefish), 3010));
		lootTable.addEntry(70, 60, new HaulingDrop(new ItemStack(ItemRegister.weaponsCase), 15000));
		lootTable.addEntry(74, 200, new HaulingDrop(new ItemStack(ItemRegister.darkHatchetfish), 5260));
		lootTable.addEntry(79, 200, new HaulingDrop(new ItemStack(ItemRegister.ironback), 7800));
		lootTable.addEntry(85, 40, new HaulingDrop(new ItemStack(ItemRegister.treasureBox), 30000));
		lootTable.addEntry(90, 200, new HaulingDrop(new ItemStack(ItemRegister.rainbowfish), 11950));
		lootTable.addEntry(94, 20, new HaulingDrop(new ItemStack(ItemRegister.crystalBox), 50000));
		lootTable.addEntry(98, 200, new HaulingDrop(new ItemStack(ItemRegister.razorfish), 18030));
	}
}
