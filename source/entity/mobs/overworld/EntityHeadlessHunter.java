package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.TreeSet;

public class EntityHeadlessHunter extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 1.2f;

	public EntityHeadlessHunter(World world) {
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
		return 70;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 16;
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
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerEnergy), 1);
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
		if (victim instanceof EntityPlayer && rand.nextInt(20) == 0) {
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
		return 20;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
