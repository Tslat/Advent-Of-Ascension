package net.tslat.aoa3.entity.projectiles.mob;

import net.minecraft.world.World;
import net.tslat.aoa3.entity.boss.nethengeicwither.EntityNethengeicWither;
import net.tslat.aoa3.library.Enums;

public class EntityNethengeicWitherShot extends BaseMobProjectile {
	public final boolean cataclysmic;

	public EntityNethengeicWitherShot(EntityNethengeicWither wither, boolean cataclysmic) {
		super(wither.world, wither, Enums.MobProjectileType.PHYSICAL);

		this.cataclysmic = cataclysmic;
	}

	public EntityNethengeicWitherShot(World world) {
		super(world);

		this.cataclysmic = false;
	}
}
