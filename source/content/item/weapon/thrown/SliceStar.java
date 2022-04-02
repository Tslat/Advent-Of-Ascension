package net.tslat.aoa3.content.item.weapon.thrown;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.thrown.SliceStarEntity;

import javax.annotation.Nullable;

public class SliceStar extends BaseThrownWeapon {
	public SliceStar() {
		super(4.5f, 7);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundEvents.WITCH_THROW;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, InteractionHand hand) {
		return new SliceStarEntity(shooter, this);
	}
}
