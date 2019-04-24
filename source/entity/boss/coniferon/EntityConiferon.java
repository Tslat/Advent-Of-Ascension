package net.tslat.aoa3.entity.boss.coniferon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityConiferon extends AoAMeleeMob implements BossEntity, SpecialPropertyEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/coniferon.png");
	public static final float entityWidth = 1.5f;

	public EntityConiferon(World world) {
		super(world, entityWidth, 6.375f);

		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 3.625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 4000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobConiferonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobConiferonDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobConiferonHit;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isGunDamage(source);
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueConiferon), 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.greatbladeSelyanScythe, 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.cannonSelyanStickler, 1);

		if (rand.nextInt(10) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerSelyan), 1);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		EntityLivingBase target = getAttackTarget();

		if (target == null)
			return;

		if (rand.nextInt(150) == 0) {
			if (!world.isRemote)
				target.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0, true, true));

			if (target instanceof EntityPlayerMP)
				PacketUtil.network.sendTo(new PacketScreenOverlay(70, Enums.ScreenOverlays.CONIFERON_VINES), (EntityPlayerMP)target);
		}
		else if (rand.nextInt(250) == 0) {
			target.setPositionAndUpdate(posX, posY, posZ);
		}

		if (!target.isSneaking())
			target.addVelocity(Math.signum(posX - target.posX) * 0.029, 0, Math.signum(posZ - target.posZ) * 0.029);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 140, 2, true, true));
			target.addVelocity(motionX * 10.5 * resist, motionY * 0.5 * resist, motionZ * 10.5 * resist);
			target.velocityChanged = true;
		}
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void checkMusicStatus() {}
}
