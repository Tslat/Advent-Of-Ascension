package net.tslat.aoa3.object.entity.projectile.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.boss.NethengeicWitherEntity;

public class NethengeicWitherShotEntity extends BaseMobProjectile {
	public final boolean cataclysmic;

	public NethengeicWitherShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);

		this.cataclysmic = false;
	}

	public NethengeicWitherShotEntity(NethengeicWitherEntity wither, boolean cataclysmic) {
		super(AoAEntities.Projectiles.NETHENGEIC_WITHER_SHOT.get(), wither.level, wither, Type.PHYSICAL);

		this.cataclysmic = cataclysmic;
	}

	public NethengeicWitherShotEntity(World world) {
		super(AoAEntities.Projectiles.NETHENGEIC_WITHER_SHOT.get(), world);

		this.cataclysmic = false;
	}
}
