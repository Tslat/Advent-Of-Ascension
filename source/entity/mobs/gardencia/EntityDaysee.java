package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import java.util.TreeSet;

public class EntityDaysee extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.5f;

	public EntityDaysee(World world) {
		super(world, entityWidth, 2.0625f);

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
		return 90;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	public int getHunterReq() {
		return 34;
	}

	@Override
	public float getHunterXp() {
		return 120;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isMeleeDamage(source);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.isRaining() && getHealth() > 0)
			heal(0.2f);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
