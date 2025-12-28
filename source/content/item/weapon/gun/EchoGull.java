package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.object.RayTrace;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public class EchoGull extends AoAGun {
	public EchoGull(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		if (level instanceof ServerLevel serverLevel)
			doWave(serverLevel, rayTrace.hitPos(), projectile.getShooter());
	}

	@Override
	protected void onHitBlock(Level level, WeaponProjectile projectile, RayTrace<Void> rayTrace, BlockState hitBlock) {
		if (level instanceof ServerLevel serverLevel)
			doWave(serverLevel, rayTrace.hitPos(), projectile.getShooter());
	}

	private void doWave(ServerLevel level, Vec3 pos, @Nullable Entity shooter) {
		doWaveTick(shooter, EntityRetrievalUtil.getEntities(level, pos, 35, LivingEntity.class, target -> EntityUtil.areProbablyEnemies(target, shooter)), pos, 0);
	}

	private void doWaveTick(@Nullable Entity shooter, List<LivingEntity> entities, Vec3 origin, int tick) {
		float distance = tick * 30 / 40f;
		distance *= distance;
		EffectBuilder builder = new EffectBuilder(MobEffects.GLOWING, 7);

		for (Iterator<LivingEntity> iterator = entities.iterator(); iterator.hasNext();) {
			LivingEntity entity = iterator.next();

			if (entity.distanceToSqr(origin) <= distance) {
				EntityUtil.applyPotions(entities, shooter, builder);
				iterator.remove();
			}
		}

		if (!entities.isEmpty() && tick < 40)
			AoAScheduler.schedule(1, tick2 -> doWaveTick(shooter, entities, origin, tick + 1));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
