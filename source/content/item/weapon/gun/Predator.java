package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.RayTrace;

import java.util.List;

public class Predator extends AoAGun {
	public Predator(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (level instanceof ServerLevel serverLevel && RandomUtil.oneInNChance(5)) {
			List<LivingEntity> entities = EntityRetrievalUtil.getEntities(level, rayTrace.hitPos(), 7, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, projectile.getShooter()));

			if (!entities.isEmpty()) {
				LivingEntity entity = RandomUtil.selection(entities);

				WorldUtil.spawnLightning(serverLevel, projectile.getShooter() instanceof ServerPlayer pl ? pl : null, entity.getX(), entity.getY(), entity.getZ(), true, false);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
