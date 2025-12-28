package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;

public class VortexBlaster extends AoABlaster<Void> {
	public VortexBlaster(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		if (context.getShooter() instanceof LivingEntity shooter) {
			doFiringEffects(level, null, shooter.position(), context);

			for (LivingEntity target : EntityRetrievalUtil.getEntities(level, shooter.getBoundingBox().expandTowards(shooter.getViewVector(1).scale(8)), LivingEntity.class)) {
				DamageUtil.doScaledKnockback(target, shooter, 7f, 1, 1, 1);
			}
		}
	}

	/*@Override
	protected void doFiringEffects(ServerLevel level, Void effect, Vec3 pos, WeaponFiringContext context) {
		super.doFiringEffects(level, effect, pos, context);
	}*/

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
