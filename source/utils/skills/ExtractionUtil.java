package net.tslat.aoa3.utils.skills;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.library.TieredWeightedRandomizedCollection;

public class ExtractionUtil {
    public static final TieredWeightedRandomizedCollection<ExtractionDrop> lootTable = new TieredWeightedRandomizedCollection<ExtractionDrop>();

    public static boolean shouldGetLoot(int lvl) {
        return AdventOfAscension.rand.nextInt(100) >= Math.pow(((102 - lvl) / 6), 1.34);
    }

    public static int getXpDenominator(final int level) {
        if (level < 3)
            return 5;

        if (level < 9)
            return 10;

        if (level < 17)
            return 20;

        if (level < 26)
            return 35;

        if (level < 41)
            return 60;

        if (level < 61)
            return 90;

        if (level < 76)
            return 150;

        if (level < 99)
            return 300;

        return 500;
    }

    public static ExtractionDrop getLoot(final int lvl) {
        return lootTable.getRandomEntry(lvl);
    }

    public static class ExtractionDrop {
        private final ItemStack lootStack;
        public final String feedbackLocale;
        private final int minAmount;
        private final int maxAmount;

        public ExtractionDrop(ItemStack lootStack, String feedbackLocale) {
            this.lootStack = lootStack;
            this.feedbackLocale = feedbackLocale;
            this.minAmount = 1;
            this.maxAmount = 1;
        }

        public ExtractionDrop(ItemStack lootStack, String feedbackLocale, int minimumAmount, int maximumAmount) {
            this.lootStack = lootStack;
            this.feedbackLocale = feedbackLocale;
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
        lootTable.addEntry(1, 100, new ExtractionDrop(new ItemStack(Items.AIR),"message.feedback.extraction.nothing", 1, 1));
        lootTable.addEntry(1, 100, new ExtractionDrop(new ItemStack(Items.COAL),"message.feedback.extraction.coal", 1, 4));
        lootTable.addEntry(1, 100, new ExtractionDrop(new ItemStack(Items.FLINT),"message.feedback.extraction.flint", 1, 4));
        lootTable.addEntry(5, 100, new ExtractionDrop(new ItemStack(ItemRegister.hollyArrow),"message.feedback.extraction.hollyArrow", 3, 9));
        lootTable.addEntry(10, 100, new ExtractionDrop(new ItemStack(Items.BLAZE_POWDER),"message.feedback.extraction.blazePowder", 1, 4));
        lootTable.addEntry(15, 100, new ExtractionDrop(new ItemStack(ItemRegister.ingotLimonite),"message.feedback.extraction.limoniteIngot", 1, 3));
        lootTable.addEntry(20, 100, new ExtractionDrop(new ItemStack(Items.BONE),"message.feedback.extraction.bone", 3, 10));
        lootTable.addEntry(25, 100, new ExtractionDrop(new ItemStack(ItemRegister.coinSilver),"message.feedback.extraction.silverCoin", 1, 2));
        lootTable.addEntry(30, 100, new ExtractionDrop(new ItemStack(Items.EMERALD),"message.feedback.extraction.emerald", 1, 1));
        lootTable.addEntry(35, 100, new ExtractionDrop(new ItemStack(Items.EXPERIENCE_BOTTLE),"message.feedback.extraction.experienceBottle", 1, 5));
        lootTable.addEntry(40, 100, new ExtractionDrop(new ItemStack(ItemRegister.ingotRosite),"message.feedback.extraction.rositeIngot", 1, 1));
        lootTable.addEntry(45, 100, new ExtractionDrop(new ItemStack(Items.BLAZE_ROD),"message.feedback.extraction.blazeRod", 1, 4));
        lootTable.addEntry(50, 100, new ExtractionDrop(new ItemStack(ItemRegister.bulletLimonite),"message.feedback.extraction.limoniteBullet", 1, 32));
        lootTable.addEntry(55, 100, new ExtractionDrop(new ItemStack(Items.DYE, 1, 0),"message.feedback.extraction.inkSac", 1, 10));
        lootTable.addEntry(60, 100, new ExtractionDrop(new ItemStack(ItemRegister.fragmentedAnimaStone),"message.feedback.extraction.fragmentedAnimaStone", 1, 4));
        lootTable.addEntry(65, 100, new ExtractionDrop(new ItemStack(Items.MAGMA_CREAM),"message.feedback.extraction.magmaCream", 2, 6));
        lootTable.addEntry(70, 100, new ExtractionDrop(new ItemStack(WeaponRegister.throwableGrenade),"message.feedback.extraction.grenade", 1, 8));
        lootTable.addEntry(75, 100, new ExtractionDrop(new ItemStack(Items.DIAMOND),"message.feedback.extraction.diamond", 1, 2));
        lootTable.addEntry(80, 100, new ExtractionDrop(new ItemStack(ItemRegister.gemBag),"message.feedback.extraction.gemBag", 1, 2));
        lootTable.addEntry(85, 100, new ExtractionDrop(new ItemStack(ItemRegister.shinyBox),"message.feedback.extraction.shinyBox", 1, 2));
        lootTable.addEntry(90, 100, new ExtractionDrop(new ItemStack(ItemRegister.coinGold),"message.feedback.extraction.goldCoin", 1, 1));
        lootTable.addEntry(95, 100, new ExtractionDrop(new ItemStack(ItemRegister.magicRepairDust),"message.feedback.extraction.magicRepairDust", 1, 1));
    }
}
