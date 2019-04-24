package net.tslat.aoa3.entity.boss.skeletalarmy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ModUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntitySkeletron extends AoAMeleeMob implements BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/skeletron.png");
	public static final float entityWidth = 1.2f;

	public EntitySkeletron(World world, BlockPos armyBlockPos) {
		this(world);

		this.setLocationAndAngles(armyBlockPos.getX(), armyBlockPos.getY() + 2, armyBlockPos.getZ(), rand.nextFloat() * 360, 0);
	}

	public EntitySkeletron(World world) {
		super(world, entityWidth, 1.25f);
	}

	@Override
	public float getEyeHeight() {
		return 1f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1100;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 70;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSkeletronLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSkeletronDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSkeletronHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.heavyStep;
	}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(Item.getItemFromBlock(BlockRegister.statueSkeletron), 1);

		switch (rand.nextInt(4)) {
			case 0:
				dropItem(WeaponRegister.swordSkeletal, 1);
				break;
			case 1:
				dropItem(WeaponRegister.bowSkeletal, 1);
				break;
			case 2:
				dropItem(WeaponRegister.staffLightning, 1);
				break;
			case 3:
				dropItem(WeaponRegister.blasterBoneBlaster, 1);
				break;
		}

		switch (rand.nextInt(4)) {
			case 0:
				dropItem(ArmourRegister.CommanderHelmet, 1);
				break;
			case 1:
				dropItem(ArmourRegister.CommanderBody, 1);
				break;
			case 2:
				dropItem(ArmourRegister.CommanderLegs, 1);
				break;
			case 3:
				dropItem(ArmourRegister.CommanderBoots, 1);
				break;
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			Entity source = cause.getTrueSource();
			EntityPlayer killer = null;

			if (source != null) {
				if (source instanceof EntityPlayer) {
					killer = (EntityPlayer)source;
				}
				else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
					killer = (EntityPlayer)((EntityTameable)source).getOwner();
				}
			}

			if (killer != null) {
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.skeletalArmy.kill", killer.getDisplayNameString()), this, 50);

				if (killer instanceof EntityPlayerMP && killer.getHeldItemMainhand().getItem() == WeaponRegister.swordSkeletal) {
					for (ItemStack stack : killer.getArmorInventoryList()) {
						if (!(stack.getItem() instanceof AdventArmour) || ((AdventArmour)stack.getItem()).setType() != Enums.ArmourSets.SKELETAL)
							return;
					}

					ModUtil.completeAdvancement((EntityPlayerMP)killer, "precasia/spooky_scary_skeletons", "full_set_kill");
				}
			}
		}
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
