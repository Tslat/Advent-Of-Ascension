package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.cannon.UltraGreenBallEntity;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import org.jetbrains.annotations.Nullable;


public class UltraCannon extends BaseCannon {
	public UltraCannon(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_UPPER_CANNON_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new UltraGreenBallEntity(shooter, this, hand, 120, 0);
	}
}
