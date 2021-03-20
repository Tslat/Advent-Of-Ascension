package net.tslat.aoa3.common.registration;

import net.minecraft.world.GameRules;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

public final class AoAGameRules {
	public static GameRules.RuleKey<GameRules.BooleanValue> DESTRUCTIVE_WEAPON_PHYSICS = null;
	public static GameRules.RuleKey<GameRules.BooleanValue> STRONGER_MOB_GRIEFING = null;

	public static void registerGameRules() {
		try {
			registerBooleanGameRule(AoAGameRules.class.getField("DESTRUCTIVE_WEAPON_PHYSICS"), "destructiveWeaponPhysics", GameRules.Category.PLAYER, false);
			registerBooleanGameRule(AoAGameRules.class.getField("STRONGER_MOB_GRIEFING"), "doStrongerMobGriefing", GameRules.Category.MOBS, false);
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	private static void registerBooleanGameRule(Field field, String name, GameRules.Category category, boolean defaultValue) {
		DeferredWorkQueue.runLater(() -> {
			try {
				field.set(null, GameRules.register(name, category, (GameRules.RuleType< GameRules.BooleanValue>)ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class).invoke(null, defaultValue)));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
