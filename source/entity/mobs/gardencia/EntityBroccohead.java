package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityBroccohead extends AoAMeleeMob {
	public static final float entityWidth = 0.5625f;

	public EntityBroccohead(World world) {
		super(world, entityWidth, 2.3625f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.plantThump;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.plantThump;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY > 66 && super.getCanSpawnHere();
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(4) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerRosidian), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));

		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.seedsRosidon, 1);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			target.addVelocity(motionX * 6.5 * resist, motionY * 0.5 * resist, motionZ * 6.5 * resist);
			target.velocityChanged = true;
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (world.isRaining() && getHealth() > 0)
			heal(0.2f);
	}
}
