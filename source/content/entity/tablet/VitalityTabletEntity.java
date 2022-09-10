package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

public class VitalityTabletEntity extends SoulTabletEntity {
	public VitalityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public VitalityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		if (level.getGameTime() % 10 == 0) {
			for (Player pl : getTargetsWithinRadius(Player.class, player -> player != null && player.isAlive())) {
				if (!pl.hasEffect(MobEffects.REGENERATION))
					EntityUtil.applyPotions(pl, new EffectBuilder(MobEffects.REGENERATION, 50).level(2).isAmbient());
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.VITALITY_TABLET.get();
	}
}
