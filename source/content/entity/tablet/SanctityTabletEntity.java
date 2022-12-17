package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;

public class SanctityTabletEntity extends SoulTabletEntity {
	public SanctityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public SanctityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (LivingEntity entity : getTargetsWithinRadius(LivingEntity.class, entity -> entity instanceof Enemy && entity.isAlive())) {
			if (!entity.fireImmune()) {
				entity.setSecondsOnFire(1);
			}
			else {
				EntityUtil.applyPotions(entity, new EffectBuilder(MobEffects.WITHER, 40).isAmbient());
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.SANCTITY_TABLET.get();
	}
}
