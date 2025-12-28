package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;

public class GravityBlaster extends AoABlaster<WeaponProjectile> {
	public GravityBlaster(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		Entity shooter = context.getShooter();

		if (shooter != null) {
			for (LivingEntity mob : EntityRetrievalUtil.getEntities(level, shooter.getBoundingBox().inflate(2, 0, 2), LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, shooter))) {
				DamageUtil.doMiscEnergyAttack(shooter, mob, context.damage(), null);
				EntityUtil.pushEntityAway(shooter, mob, 0.5f);
			}

			shooter.push(0, 2f, 0);
			shooter.hurtMarked = true;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
