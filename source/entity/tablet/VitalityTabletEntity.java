package net.tslat.aoa3.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tablet.TabletItem;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

public class VitalityTabletEntity extends SoulTabletEntity {
	public VitalityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public VitalityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		if (level.getGameTime() % 10 == 0) {
			for (PlayerEntity pl : getTargetsWithinRadius(PlayerEntity.class, player -> player != null && player.isAlive())) {
				if (!pl.hasEffect(Effects.REGENERATION))
					EntityUtil.applyPotions(pl, new PotionUtil.EffectBuilder(Effects.REGENERATION, 50).level(2).isAmbient());
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.VITALITY_TABLET.get();
	}
}
