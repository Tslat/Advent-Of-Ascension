package net.nevermine.container;

import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.boss.cavern.*;
import net.nevermine.event.player.Ticker;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AncientBossesContainer {
	private static Random r = new Random();
	private static EntityHoron horon = null;
	private static EntityPenumbra penumbra = null;
	private static EntityGoldorth goldorth = null;
	private static EntityConiferon coniferon = null;

	private static boolean horonCooldown = false;
	private static boolean penumbraCooldown = false;
	private static boolean goldorthCooldown = false;
	private static boolean coniferonCooldown = false;

	public static void trySpawnHoron(World w) {
		if (w.provider.dimensionId == ConfigurationHelper.ancientcavern && w.difficultySetting != EnumDifficulty.PEACEFUL) {
			if (horon == null && !horonCooldown) {
				horon = new EntityHoron(w);
				horon.setLocationAndAngles(95, 18, -51, r.nextFloat() * 360.0f, 0.0f);
				w.spawnEntityInWorld(horon);
			}
		}
	}

	public static void trySpawnPenumbra(World w) {
		if (w.provider.dimensionId == ConfigurationHelper.ancientcavern && w.difficultySetting != EnumDifficulty.PEACEFUL) {
			if (penumbra == null && !penumbraCooldown) {
				penumbra = new EntityPenumbra(w);
				penumbra.setLocationAndAngles(-51, 18, 92, r.nextFloat() * 360.0f, 0.0f);
				w.spawnEntityInWorld(penumbra);
			}
		}
	}

	public static void trySpawnGoldorth(World w) {
		if (w.provider.dimensionId == ConfigurationHelper.ancientcavern && w.difficultySetting != EnumDifficulty.PEACEFUL) {
			if (goldorth == null && !goldorthCooldown) {
				goldorth = new EntityGoldorth(w);
				goldorth.setLocationAndAngles(97, 18, 88, r.nextFloat() * 360.0f, 0.0f);
				w.spawnEntityInWorld(goldorth);
			}
		}
	}

	public static void trySpawnConiferon(World w) {
		if (w.provider.dimensionId == ConfigurationHelper.ancientcavern && w.difficultySetting != EnumDifficulty.PEACEFUL) {
			if (coniferon == null && !coniferonCooldown) {
				coniferon = new EntityConiferon(w);
				coniferon.setLocationAndAngles(-56, 18, -52, r.nextFloat() * 360.0f, 0.0f);
				w.spawnEntityInWorld(coniferon);
			}
		}
	}

	public static void killHoron(EntityHoron entity) {
		if (entity.worldObj.provider.dimensionId == ConfigurationHelper.ancientcavern) {
			horon = null;
			horonCooldown = true;

			Ticker.scheduleTask(new CavernBossResetTask(0, entity.worldObj), 35, TimeUnit.SECONDS);
		}

		if (!entity.worldObj.isRemote && !entity.isDead)
			entity.setDead();
	}

	public static void killPenumbra(EntityPenumbra entity) {
		if (entity.worldObj.provider.dimensionId == ConfigurationHelper.ancientcavern) {
			penumbra = null;
			penumbraCooldown = true;

			Ticker.scheduleTask(new CavernBossResetTask(1, entity.worldObj), 35, TimeUnit.SECONDS);
		}

		if (!entity.worldObj.isRemote && !entity.isDead)
			entity.setDead();
	}

	public static void killGoldorth(EntityGoldorth entity) {
		if (entity.worldObj.provider.dimensionId == ConfigurationHelper.ancientcavern) {
			goldorth = null;
			goldorthCooldown = true;

			Ticker.scheduleTask(new CavernBossResetTask(2, entity.worldObj), 35, TimeUnit.SECONDS);
		}

		if (!entity.worldObj.isRemote && !entity.isDead)
			entity.setDead();
	}

	public static void killConiferon(EntityConiferon entity) {
		if (entity.worldObj.provider.dimensionId == ConfigurationHelper.ancientcavern) {
			coniferon = null;
			coniferonCooldown = true;

			Ticker.scheduleTask(new CavernBossResetTask(3, entity.worldObj), 35, TimeUnit.SECONDS);
		}

		if (!entity.worldObj.isRemote && !entity.isDead)
			entity.setDead();
	}

	public static void unloadAllBosses() {
		if (horon != null)
			horon.setDead();

		if (penumbra != null)
			penumbra.setDead();

		if (goldorth != null)
			goldorth.setDead();

		if (coniferon != null)
			coniferon.setDead();

		horon = null;
		penumbra = null;
		goldorth = null;
		coniferon = null;
		horonCooldown = false;
		penumbraCooldown = false;
		goldorthCooldown = false;
		coniferonCooldown = false;
	}

	public static void resetCooldown(int boss, World world) {
		switch (boss) {
			case 0:
				horonCooldown = false;
				trySpawnHoron(world);
				break;
			case 1:
				penumbraCooldown = false;
				trySpawnPenumbra(world);
				break;
			case 2:
				goldorthCooldown = false;
				trySpawnGoldorth(world);
				break;
			case 3:
				coniferonCooldown = false;
				trySpawnConiferon(world);
				break;
			default:
				break;
		}
	}
}
