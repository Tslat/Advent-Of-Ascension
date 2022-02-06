package net.tslat.aoa3.object.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.object.entity.projectile.thrown.GrenadeEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Baronator extends BaseGun {
	public Baronator(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_GENERIC_FIRE_1.get();
	}

	@Override

	protected boolean fireGun(LivingEntity shooter, ItemStack stack, Hand hand) {
		 if (super.fireGun(shooter, stack, hand)) {
			 if (!shooter.level.isClientSide && RandomUtil.oneInNChance(5)) {
				 shooter.level.addFreshEntity(new GrenadeEntity(shooter, this, hand, 120, 0));
				 shooter.level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_GUN_AIR_CANNON_FIRE.get(), SoundCategory.PLAYERS, 1.0f, getFiringSoundPitchAdjust() + (float)random.nextGaussian() * 0.075f);
			 }

			 return true;
		 }

		 return false;
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (!(bullet instanceof GrenadeEntity))
			super.doImpactDamage(target, shooter, bullet, bulletDmgMultiplier);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
