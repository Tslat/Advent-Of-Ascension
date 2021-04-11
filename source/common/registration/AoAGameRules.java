package net.tslat.aoa3.common.registration;

import net.minecraft.world.GameRules;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.function.Consumer;

public final class AoAGameRules {
	public static GameRules.RuleKey<GameRules.BooleanValue> DESTRUCTIVE_WEAPON_PHYSICS = null;
	public static GameRules.RuleKey<GameRules.BooleanValue> STRONGER_MOB_GRIEFING = null;

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
}
