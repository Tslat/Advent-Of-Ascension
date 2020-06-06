package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;

public class EntityBugeye extends AoAMeleeMob {
	public static final float entityWidth = 0.95f;

	public EntityBugeye(World world) {
		super(world, entityWidth, 1.125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.65f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 15;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 3;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.29d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_BUGEYE_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_BUGEYE_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_BUGEYE_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityBugeye;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketScreenOverlay(150, Enums.ScreenOverlays.CIRCLES), (EntityPlayerMP)target);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
}
