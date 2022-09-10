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

public class DistortionTabletEntity extends SoulTabletEntity {
	public DistortionTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world) {
		this(entityType, world, null);
	}

	public DistortionTabletEntity(EntityType<? extends SoulTabletEntity> entityType, Level world, ServerPlayer placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		EntityUtil.applyPotions(getTargetsWithinRadius(Player.class, player -> player != null && player.isAlive()), new EffectBuilder(MobEffects.INVISIBILITY, 10).isAmbient());
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.DISTORTION_TABLET.get();
	}
}
