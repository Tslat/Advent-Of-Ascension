package net.tslat.aoa3.entity.boss.penumbra;

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
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.mobs.abyss.EntityApparition;
import net.tslat.aoa3.entity.mobs.abyss.EntityOcculent;
import net.tslat.aoa3.entity.mobs.dustopia.EntityBasilisk;
import net.tslat.aoa3.entity.mobs.dustopia.EntityDevourer;
import net.tslat.aoa3.entity.mobs.dustopia.EntityStalker;
import net.tslat.aoa3.entity.mobs.greckon.EntityShifter;
import net.tslat.aoa3.entity.mobs.nether.EntityHellspot;
import net.tslat.aoa3.entity.mobs.voxponds.EntityAlarmo;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityPenumbraShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityPenumbra extends AoARangedMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/penumbra.png");
	public static final float entityWidth = 0.9f;

	public EntityPenumbra(World world) {
		super(world, entityWidth, 3.1875f);
	}

	@Override
	public float getEyeHeight() {
		return 3.0625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 18;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobPenumbraLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobPenumbraDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobPenumbraHit;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statuePenumbra), 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.cannonErebonStickler, 1);

		if (rand.nextInt(10) == 0)
			dropItem(WeaponRegister.greatbladeErebonScythe, 1);

		if (rand.nextInt(10) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerErebon), 1);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityOcculent || target instanceof EntityAlarmo || target instanceof EntityShifter || target instanceof EntityDevourer || target instanceof EntityBasilisk
			|| target instanceof EntityApparition || target instanceof EntityStalker || target instanceof EntityHellspot)
			return;

		super.doProjectileEntityImpact(projectile, target);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		Entity entity = null;

		switch (rand.nextInt(8)) {
			case 0:
				entity = new EntityOcculent(world);
				break;
			case 1:
				entity = new EntityAlarmo(world);
				break;
			case 2:
				entity = new EntityShifter(world);
				break;
			case 3:
				entity = new EntityDevourer(world);
				break;
			case 4:
				entity = new EntityBasilisk(world);
				break;
			case 5:
				entity = new EntityApparition(world);
				break;
			case 6:
				entity = new EntityStalker(world);
				break;
			case 7:
				entity = new EntityHellspot(world);
				break;
		}

		entity.setPosition(projectile.posX, projectile.posY, projectile.posZ);
		world.spawnEntity(entity);
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityPenumbraShot(this, Enums.MobProjectileType.ENERGY);
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
