package net.tslat.aoa3.content.item.weapon.thrown;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.thrown.SliceStarEntity;
import org.jetbrains.annotations.Nullable;

public class SliceStar extends AoAThrowableWeapon {
	public SliceStar(Item.Properties properties) {
		super(properties);
	}

	@Nullable
	@Override
	public SoundEvent getThrowSound() {
		return SoundEvents.WITCH_THROW;
	}

	@Override
	public WeaponProjectile createProjectileEntity(Level level, WeaponFiringContext context) {
		return new SliceStarEntity(level, context);
	}
}
