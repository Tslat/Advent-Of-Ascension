package net.tslat.aoa3.content.entity.tablet;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.item.tablet.TabletItem;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.EntityUtil;

public class SightTabletEntity extends SoulTabletEntity {
	public SightTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public SightTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (Player pl : getTargetsWithinRadius(Player.class, player -> player != null && player.isAlive())) {
			MobEffectInstance nightVision = pl.getEffect(MobEffects.NIGHT_VISION);

			if (nightVision == null || nightVision.getDuration() < 250)
				EntityUtil.applyPotions(pl, new EffectBuilder(MobEffects.NIGHT_VISION, 300).isAmbient());
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.SIGHT_TABLET.get();
	}
}
