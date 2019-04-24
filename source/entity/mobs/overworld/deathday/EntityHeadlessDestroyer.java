package net.tslat.aoa3.entity.mobs.overworld.deathday;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import java.util.TreeSet;

public class EntityHeadlessDestroyer extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.2f;

	public EntityHeadlessDestroyer(World world) {
		super(world, entityWidth, 1.9f);
	}

	@Override
	public float getEyeHeight() {
		return 1.7f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 140;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 23;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSlimerDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSlimerHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.coinCopper, 1 + rand.nextInt(1 + lootingMod));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	public void onKillEntity(EntityLivingBase victim) {
		if (victim instanceof EntityPlayer && rand.nextInt(10) == 0) {
			ItemStack headStack = new ItemStack(Items.SKULL, 1, 3);

			headStack.setTagCompound(new NBTTagCompound());
			headStack.getTagCompound().setTag("SkullOwner", new NBTTagString(((EntityPlayer)victim).getGameProfile().getName()));

			EntityItem playerHead = new EntityItem(world, victim.posX, victim.posY, victim.posZ, headStack);
			world.spawnEntity(playerHead);
		}
	}

	@Override
	public int getHunterReq() {
		return 1;
	}

	@Override
	public float getHunterXp() {
		return 35;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.DEATH_DAY;
	}
}
