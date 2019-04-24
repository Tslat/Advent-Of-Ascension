package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashSet;

public class EntityTrickster extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;
	private int invisCooldown = 160;
	private int cloneCooldown = 0;
	private HashSet<EntityTricksterClone> clones = new HashSet<EntityTricksterClone>();

	public EntityTrickster(World world) {
		super(world, entityWidth, 1.75f);
	}

	@Override
	public float getEyeHeight() {
		return 1.65f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 55;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 20 && super.getCanSpawnHere();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobTricksterLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobTricksterHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobTricksterHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(WeaponRegister.cannonMiniCannon, 1);

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerIllusion), 1);

		if (rand.nextInt(6) == 0)
			dropItem(ItemRegister.realmstoneAbyss, 1);

		if (rand.nextInt(5) == 0)
			dropItem(ItemRegister.realmstoneDeeplands, 1);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (invisCooldown > 0)
			--invisCooldown;

		if (cloneCooldown > 1)
			--cloneCooldown;

		if (!world.isRemote) {
			if (invisCooldown == 0) {
				cloneCooldown = 60;
				invisCooldown = 240;

				addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 60, 2, true, true));
				world.playSound(null, posX, posY, posZ, SoundsRegister.mobTricksterHide, SoundCategory.HOSTILE, 1.0f, 1.0f);
			}

			if (cloneCooldown == 1 && world.getEntitiesWithinAABB(EntityTricksterClone.class, getEntityBoundingBox().grow(10)).size() < 5) {
				EntityTricksterClone clone = new EntityTricksterClone(world, posX, posY, posZ);

				world.spawnEntity(clone);
				clones.add(clone);
				cloneCooldown = 0;
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			for (EntityTricksterClone clone : clones) {
				clone.setDead();
			}
		}
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
