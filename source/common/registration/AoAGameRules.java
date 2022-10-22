package net.tslat.aoa3.common.registration;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;

import java.util.function.Consumer;

public final class AoAGameRules {
	private static GameRules.Key<GameRules.BooleanValue> DESTRUCTIVE_WEAPON_PHYSICS = null;

	public static void lateInit() {
		registerBooleanGameRule(key -> DESTRUCTIVE_WEAPON_PHYSICS = key, "destructiveWeaponPhysics", GameRules.Category.PLAYER, false);
	}

	private static void registerBooleanGameRule(Consumer<GameRules.Key<GameRules.BooleanValue>> valueConsumer, String name, GameRules.Category category, boolean defaultValue) {
		try {
			valueConsumer.accept(GameRules.register(name, category, (GameRules.Type< GameRules.BooleanValue>)ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "m_46250_", boolean.class).invoke(null, defaultValue)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkDestructiveWeaponPhysics(Level world) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key))
			return false;

		return world.getGameRules().getBoolean(DESTRUCTIVE_WEAPON_PHYSICS);
	}
}
