package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;

import java.util.ArrayList;

public class CleansingTabletEntity extends SoulTabletEntity {
	public CleansingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public CleansingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (Player pl : getTargetsWithinRadius(Player.class, player -> player != null && player.isAlive() && !player.getActiveEffectsMap().isEmpty())) {
			ArrayList<MobEffectInstance> removeList = new ArrayList<MobEffectInstance>(pl.getActiveEffects().size());

			for (MobEffectInstance effect : pl.getActiveEffects()) {
				if (!effect.getEffect().isBeneficial())
					removeList.add(effect);
			}

			for (MobEffectInstance effect : removeList) {
				pl.removeEffect(effect.getEffect());
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.CLEANSING_TABLET.get();
	}
}
