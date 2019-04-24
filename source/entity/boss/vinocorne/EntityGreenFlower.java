package net.tslat.aoa3.entity.boss.vinocorne;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import java.util.TreeSet;

public class EntityGreenFlower extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.7f;

	public EntityGreenFlower(EntityVinocorne vinocorne) {
		this(vinocorne.world);

		setLocationAndAngles(vinocorne.posX, vinocorne.posY, vinocorne.posZ, rand.nextFloat() * 360, 0);
	}

	public EntityGreenFlower(World world) {
		super(world, entityWidth, 2.3125f);

		this.mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
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

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isMeleeDamage(source);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
