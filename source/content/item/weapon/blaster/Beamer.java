package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.content.entity.projectile.blaster.BeamerShotEntity;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class Beamer extends AoABlaster<WeaponProjectile> {
	public Beamer(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireBlasterProjectile(level, context, BeamerShotEntity::new, projectile -> projectile
				.fromArmPosWithOffset(0, -0.25f)
				.shootingAtTarget(context.velocity(), context.inaccuracy()));
		fireBlasterProjectile(level, context, BeamerShotEntity::new);
		fireBlasterProjectile(level, context, BeamerShotEntity::new, projectile -> projectile
				.fromArmPosWithOffset(0, 0.25f)
				.shootingAtTarget(context.velocity(), context.inaccuracy()));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
