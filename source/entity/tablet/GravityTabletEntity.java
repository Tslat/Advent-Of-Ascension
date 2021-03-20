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

public class GravityTabletEntity extends SoulTabletEntity {
	public GravityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public GravityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		EntityUtil.applyPotions(getTargetsWithinRadius(PlayerEntity.class, player -> player != null && player.isAlive()), new PotionUtil.EffectBuilder(Effects.JUMP, 10).level(5).isAmbient());
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.GRAVITY_TABLET.get();
	}
}
