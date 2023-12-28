package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.blaster.PartyPopperEntity;
import org.jetbrains.annotations.Nullable;


public class PartyPopper extends BaseBlaster {
	public PartyPopper(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_PARTY_POPPER_FIRE.get();
	}

	@Override
	public void fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster) {
		shooter.level().addFreshEntity(new PartyPopperEntity(shooter, this, 60));
	}
}
