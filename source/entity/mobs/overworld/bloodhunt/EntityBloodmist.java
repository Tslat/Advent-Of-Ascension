package net.tslat.aoa3.entity.mobs.overworld.bloodhunt;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

import static net.tslat.aoa3.library.Enums.Resources.RAGE;

public class EntityBloodmist extends AoAMeleeMob implements HunterEntity {
	public static final float entityWidth = 0.8f;

	public EntityBloodmist(World world) {
		super(world, entityWidth, 1.2f);
	}

	@Override
	public float getEyeHeight() {
		return 0.55f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobBloodmistLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBloodmistDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBloodmistHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneAbyss, 1);

		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerBlood), 1);
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayer)
			PlayerUtil.getAdventPlayer((EntityPlayer)target).consumeResource(RAGE, 20, true);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public int getHunterReq() {
		return 1;
	}

	@Override
	public float getHunterXp() {
		return 15;
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.BLOOD_HUNT;
	}
}
