package net.tslat.aoa3.utils;

import net.minecraft.advancements.Advancement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.item.weapon.staff.FungalStaff;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ModUtil {
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static final HashSet<Runnable> scheduledTasks = new HashSet<Runnable>();

	private static final IAttribute MOD_MAX_HEALTH = (new RangedAttribute(null, "generic.MaxHealth", 20.0D, Float.MIN_VALUE, Integer.MAX_VALUE)).setDescription("Max Health").setShouldWatch(true);
	public static final AxisAlignedBB COLLIDABLE_BLOCK_AABB = new AxisAlignedBB(0.05, 0.05, 0.05, 0.95, 0.95, 0.95);

	public static void scrapeRegistries() {
		for (Item item : ForgeRegistries.ITEMS.getValuesCollection()) {
			if (item instanceof ItemFood && !FungalStaff.blacklistFoods.contains(item)) {
				NonNullList<ItemStack> subItems = NonNullList.create();
				item.getSubItems(CreativeTabs.SEARCH, subItems);

				for (ItemStack stack : subItems) {
					FungalStaff.validFoods.add(stack.getItem());
					FungalStaff.validFoodsMeta.add(stack.getMetadata());
				}
			}
		}
	}

	public static void patchMaxHP() {
		try {
			Field healthField = null;

			try {
				healthField = SharedMonsterAttributes.class.getDeclaredField("MAX_HEALTH");
			}
			catch (NoSuchFieldException e) {
				try {
					healthField = SharedMonsterAttributes.class.getDeclaredField("field_111267_a");
				}
				catch (NoSuchFieldException ex) {
					System.out.println("Unable to find max health field to patch, dropping");
				}
			}

			if (healthField != null) {
				healthField.setAccessible(true);

				Field modifiers = Field.class.getDeclaredField("modifiers");
				modifiers.setAccessible(true);
				modifiers.setInt(healthField, healthField.getModifiers() & ~Modifier.FINAL);

				healthField.set(null, MOD_MAX_HEALTH);
			}
		}
		catch (Exception e) {
			System.out.println("Unable to patch out vanilla MaxHP, dropping");
			e.printStackTrace();
		}
	}

	public static void scheduleRequiredTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
		scheduledTasks.add(run);
	}

	public static void scheduleTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
	}

	public static void serverShutdownTasks() {
		for (Runnable task : scheduledTasks) {
			task.run();
		}

		AdventOfAscension.proxy.worldShutdown();
	}

	public static Advancement getAdvancement(String resourcePath) {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getAdvancementManager().getAdvancement(new ResourceLocation("aoa3", resourcePath));
	}

	public static boolean completeAdvancement(EntityPlayerMP player, String resourcePath, String criterion) {
		Advancement adv = getAdvancement(resourcePath);

		if (adv != null)
			return player.getAdvancements().grantCriterion(adv, criterion);

		return false;
	}

	public static boolean checkAdvancementCompleted(EntityPlayerMP player, String resourcePath) {
		Advancement adv = getAdvancement(resourcePath);

		if (adv != null)
			return player.getAdvancements().getProgress(adv).isDone();

		return false;
	}
}
