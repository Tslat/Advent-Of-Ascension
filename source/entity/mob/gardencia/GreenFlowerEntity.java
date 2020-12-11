package net.tslat.aoa3.entity.mob.gardencia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.VinocorneEntity;

public class GreenFlowerEntity extends AoAMeleeMob {
	public GreenFlowerEntity(VinocorneEntity vinocorne) {
		this(AoAEntities.Mobs.GREEN_FLOWER.get(), vinocorne.world);

		setLocationAndAngles(vinocorne.getPosX(), vinocorne.getPosY(), vinocorne.getPosZ(), rand.nextFloat() * 360, 0);
	}

	public GreenFlowerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.5f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}
}
