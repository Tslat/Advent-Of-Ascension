package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.MathUtil;

import java.util.List;
import java.util.Optional;

public class PoisonPlunger extends AoABlaster<WeaponProjectile> {
	public PoisonPlunger(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		Entity shooter = context.getShooter();

		if (shooter != null) {
			Vec3 position = shooter.position().add(MathUtil.getEyelineForward(shooter));
			AreaEffectCloud cloud = new AreaEffectCloud(level, position.x, position.y, position.z);

			cloud.setRadius(4);
			cloud.setWaitTime(0);
			cloud.setDuration(100);
			cloud.setPotionContents(new PotionContents(Optional.empty(), Optional.of(ColourUtil.RGB(51, 102, 0)), List.of(new MobEffectInstance(MobEffects.POISON, 200, 1, false, true))));

			if (shooter instanceof LivingEntity livingShooter)
				cloud.setOwner(livingShooter);

			level.addFreshEntity(cloud);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}