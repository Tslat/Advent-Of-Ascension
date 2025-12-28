package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.library.object.interfaces.ToFloatFunction;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;

public class WhimsyWinder extends AoABlaster<WeaponProjectile> {
	public WhimsyWinder(Item.Properties properties) {
		super(properties);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireBasicBlasterProjectile(level, context, AoAProjectiles.WINDER_SHOT);
	}

	@Override
	protected boolean doEntityImpact(Level level, WeaponProjectile effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity) {
		if (level instanceof ServerLevel serverLevel) {
			List<Entity> nearbyTargets = EntityRetrievalUtil.getEntities(hitEntity, 3, 1, 3, entity -> EntityUtil.areProbablyEnemies(entity, context.getShooter()));

			nearbyTargets.add(hitEntity);

			float splitDmg = (float)(context.damage() / nearbyTargets.size() * (Math.pow(1.05, nearbyTargets.size())));
			MutableFloat damage = new MutableFloat(splitDmg);
			ToFloatFunction<DamageSource> damageCalculator = source -> {
				modifyImpactDamage(serverLevel, effect, context, rayTrace, hitEntity, source, damage);

				return damage.floatValue();
			};
			boolean success = false;

			for (Entity entity : nearbyTargets) {
				if (DamageUtil.doEnergyProjectileAttack(context.getShooter(), effect.asEntity(), entity, damageCalculator)) {
					onDamageEntity(level, effect, context, rayTrace, entity, damage.floatValue());

					success = true;
				}
			}

			return success;
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
