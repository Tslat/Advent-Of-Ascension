package net.tslat.aoa3.entity.tablet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.tablet.TabletItem;

import java.util.ArrayList;

public class CleansingTabletEntity extends SoulTabletEntity {
	public CleansingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world) {
		this(entityType, world, null);
	}

	public CleansingTabletEntity(EntityType<? extends SoulTabletEntity> entityType, World world, ServerPlayerEntity placer) {
		super(entityType, world, placer);
	}

	@Override
	protected void doTickEffect() {
		for (PlayerEntity pl : getTargetsWithinRadius(PlayerEntity.class, player -> player != null && player.isAlive() && !player.getActivePotionMap().isEmpty())) {
			ArrayList<EffectInstance> removeList = new ArrayList<EffectInstance>(pl.getActivePotionEffects().size());

			for (EffectInstance effect : pl.getActivePotionEffects()) {
				if (!effect.getPotion().isBeneficial())
					removeList.add(effect);
			}

			for (EffectInstance effect : removeList) {
				pl.removePotionEffect(effect.getPotion());
			}
		}
	}

	@Override
	public TabletItem getRelevantItem() {
		return (TabletItem)AoAItems.CLEANSING_TABLET.get();
	}
}
