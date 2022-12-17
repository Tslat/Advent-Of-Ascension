package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class VileVanquisher extends BaseGun {
	public VileVanquisher(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_GENERIC_FIRE_3.get();
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (!target.isAlive() || (target instanceof LivingEntity && ((LivingEntity)target).getHealth() <= 0)) {
			AreaEffectCloud cloud = new AreaEffectCloud(bullet.level, (target.getX() + bullet.getX()) / 2d, (target.getY() + bullet.getY()) / 2d, (target.getZ() + bullet.getZ()) / 2d);

			cloud.setRadius(0.5f);
			cloud.setDuration(10);
			cloud.setRadiusPerTick(0.45f);
			cloud.setWaitTime(0);
			cloud.setFixedColor(ColourUtil.RGB(51, 102, 0));
			cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 150, 0, false, true));

			bullet.level.addFreshEntity(cloud);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}