package net.tslat.aoa3.entity.boss.goldorth;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;

import javax.annotation.Nullable;

public class EntityGoldorth extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/goldorth.png");
	public static final float entityWidth = 2f;

	public EntityGoldorth(World world) {
		super(world, entityWidth, 3f);

		this.setSlipperyMovement();
		this.setAIMoveSpeed(1.6f);
	}

	@Override
	public float getEyeHeight() {
		return 2.8125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 17;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobGoldorthLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobGoldorthDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobGoldorthHit;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueGoldorth), 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.greatbladePlutonScythe, 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.cannonPlutonStickler, 1);

		if (rand.nextInt(10) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerPluton), 1);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getAttackTarget() != null && rand.nextInt(250) == 0)
			getAttackTarget().setPositionAndUpdate(posX, posY, posZ);
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
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
