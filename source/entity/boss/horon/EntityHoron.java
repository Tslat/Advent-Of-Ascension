package net.tslat.aoa3.entity.boss.horon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityHoron extends AoAMeleeMob implements BossEntity, SpecialPropertyEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/horon.png");
	public static final float entityWidth = 1.5f;

	public EntityHoron(World world) {
		super(world, entityWidth, 2.6875f);

		this.mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 2.28125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 24;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobHoronLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobHoronDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobHoronHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueHoron), 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.greatbladeLuxonScythe, 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.cannonLuxonStickler, 1);

		if (rand.nextInt(10) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerLuxon), 1);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return EntityUtil.isRangedDamage(source, this, 1);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayer) {
			AdventPlayerCapability cap = PlayerUtil.getAdventPlayer((EntityPlayer)target);

			cap.consumeResource(Enums.Resources.ENERGY, 60, true);
			cap.consumeResource(Enums.Resources.CREATION, 20, true);
			cap.consumeResource(Enums.Resources.SOUL, 20, true);
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (rand.nextInt(60) == 0)
			world.createExplosion(this, posX, posY, posZ, 2, false);

		if (getAttackTarget() != null && rand.nextInt(400) == 0) {
			setPositionAndUpdate(getAttackTarget().posX, getAttackTarget().posY, getAttackTarget().posZ);
			getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 120, 3, true, true));
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
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
