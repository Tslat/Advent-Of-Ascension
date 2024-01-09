package net.tslat.aoa3.common.registration;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;

public final class AoAGameRules {
	private static final GameRules.Key<GameRules.BooleanValue> DESTRUCTIVE_WEAPON_PHYSICS = GameRules.register("destructiveWeaponPhysics", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));

	public static void lateInit() {}

	public static boolean checkDestructiveWeaponPhysics(Level world) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE.key))
			return false;

		return world.getGameRules().getBoolean(DESTRUCTIVE_WEAPON_PHYSICS);
	}
}
