package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;

public class EntityGhostineAncient extends AoAMeleeMob {
	public static final float entityWidth = 0.9f;

	public EntityGhostineAncient(World world) {
		super(world, entityWidth, 2.1f);
	}

	@Override
	public float getEyeHeight() {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 3;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_GHOSTINE_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_GHOSTINE_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_GHOSTINE_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityGhostineAncient;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayer) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)target);

			plData.stats().consumeResource(Enums.Resources.SOUL, plData.stats().getResourceValue(Enums.Resources.SOUL) / 1.5f, true);
			plData.sendThrottledChatMessage("message.mob.ghostineAncient.attack");
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
}
