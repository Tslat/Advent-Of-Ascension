package net.tslat.aoa3.entity.mobs.overworld.soulscurry;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityGhostlyBugeye extends AoAMeleeMob {
	public static final float entityWidth = 0.9f;

	public EntityGhostlyBugeye(World world) {
		super(world, entityWidth, 1.125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.8125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 100;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobBugeyeLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobBugeyeDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobBugeyeHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(5) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerGhostly), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.ghostlyStone, 2 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityPlayerMP)
			PacketUtil.network.sendTo(new PacketScreenOverlay(150, Enums.ScreenOverlays.SPIKEY_CIRCLES), (EntityPlayerMP)target);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.SOUL_SCURRY;
	}
}
