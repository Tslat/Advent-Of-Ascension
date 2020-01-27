package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityEnderCarrier extends AoAMinion {
	public static final float entityWidth = 1.1f;

	public EntityEnderCarrier(final World world){
		super(world, -1, entityWidth, 1.375f);
	}

	@Override
	public float getEyeHeight() {
		return 1.21875f;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3346d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 100;
	}

	@Override
	protected boolean isHostile() {
		return false;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (getOwnerId() != null && getOwnerId().equals(player.getUniqueID())) {
			player.displayGUIChest(player.getInventoryEnderChest());

			return true;
		}

		return super.processInteract(player, hand);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDyrehornLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDyrehornHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDyrehornDeath;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityEnderCarrier;
	}
}
