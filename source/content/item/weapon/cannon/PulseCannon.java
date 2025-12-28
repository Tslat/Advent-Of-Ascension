package net.tslat.aoa3.content.item.weapon.cannon;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;

public class PulseCannon extends AoACannon {
	public PulseCannon(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onGunFire(ServerLevel level, WeaponFiringContext context, WeaponProjectile projectile) {
		if (context.getShooter() instanceof LivingEntity shooter) {
			for (LivingEntity entity : EntityRetrievalUtil.getEntities(projectile.asEntity(), 2.5, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, shooter))) {
				EntityUtil.pushEntityAway(shooter, entity, 0.75f);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
