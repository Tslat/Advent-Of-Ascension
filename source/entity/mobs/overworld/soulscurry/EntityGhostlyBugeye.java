package net.tslat.aoa3.entity.mobs.overworld.soulscurry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityGhostlyBugeye extends AoAMeleeMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.9f;

	public EntityGhostlyBugeye(World world) {
		super(world, entityWidth, 1.125f);

		mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
		mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
		mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 0.8125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
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
		return LootSystemRegister.entityGhostlyBugeye;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayerMP) {
			PacketUtil.network.sendTo(new PacketScreenOverlay(150, Enums.ScreenOverlays.SPIKEY_CIRCLES), (EntityPlayerMP)target);
			PlayerUtil.consumeResource((EntityPlayer)target, Enums.Resources.SOUL, 10f, true);
		}
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isGunDamage(source) || EntityUtil.isMeleeDamage(source) || EntityUtil.isRangedDamage(source, this, damage);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.SOUL_SCURRY;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
