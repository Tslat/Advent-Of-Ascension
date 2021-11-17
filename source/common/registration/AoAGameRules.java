package net.tslat.aoa3.common.registration;

import net.minecraft.entity.Entity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.tslat.aoa3.util.WorldUtil;

import java.util.function.Consumer;

public final class AoAGameRules {
	private static GameRules.RuleKey<GameRules.BooleanValue> DESTRUCTIVE_WEAPON_PHYSICS = null;
	private static GameRules.RuleKey<GameRules.BooleanValue> STRONGER_MOB_GRIEFING = null;

	public static void registerGameRules() {
		registerBooleanGameRule(key -> DESTRUCTIVE_WEAPON_PHYSICS = key, "destructiveWeaponPhysics", GameRules.Category.PLAYER, false);
		registerBooleanGameRule(key -> STRONGER_MOB_GRIEFING = key, "doStrongerMobGriefing", GameRules.Category.MOBS, false);
	}

	private static void registerBooleanGameRule(Consumer<GameRules.RuleKey<GameRules.BooleanValue>> valueConsumer, String name, GameRules.Category category, boolean defaultValue) {
		DeferredWorkQueue.runLater(() -> {
			try {
				valueConsumer.accept(GameRules.register(name, category, (GameRules.RuleType< GameRules.BooleanValue>)ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class).invoke(null, defaultValue)));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static boolean checkStrongerMobGriefing(World world, Entity entity) {
		EntityMobGriefingEvent event = new EntityMobGriefingEvent(entity);
		MinecraftForge.EVENT_BUS.post(event);

		Event.Result result = event.getResult();
		return result == Event.Result.DEFAULT ? world.getGameRules().getBoolean(STRONGER_MOB_GRIEFING) : result == Event.Result.ALLOW;
	}

	public static boolean checkDestructiveWeaponPhysics(World world) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key))
			return false;

		return world.getGameRules().getBoolean(DESTRUCTIVE_WEAPON_PHYSICS);
	}
}
