package net.tslat.aoa3.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tablet.TabletItem;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

public class SanctityTabletEntity extends SoulTabletEntity {
	public SanctityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public SanctityTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (LivingEntity entity : getTargetsWithinRadius(LivingEntity.class, entity -> entity instanceof IMob && entity.isAlive())) {
			if (!entity.isImmuneToFire()) {
				entity.setFire(1);
			}
			else {
				EntityUtil.applyPotions(entity, new PotionUtil.EffectBuilder(Effects.WITHER, 40).isAmbient());
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.SANCTITY_TABLET.get();
	}
}
