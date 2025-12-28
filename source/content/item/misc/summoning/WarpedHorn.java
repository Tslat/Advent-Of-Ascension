package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMonsters;
import net.tslat.aoa3.content.entity.boss.skeletron.SkeletronEntity;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarpedHorn extends BossSpawningItem<SkeletronEntity> {
	public WarpedHorn() {
		super(0, new Properties().rarity(Rarity.RARE).stacksTo(1));
	}

	@Override
	public SkeletronEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack, int playerCount) {
		SkeletronEntity skeletron = EntitySpawningUtil.spawnEntity(level, AoAMonsters.SKELETRON.get(), position, MobSpawnType.TRIGGERED);


		applyMultiplayerHealthBoost(skeletron, playerCount);

		return skeletron;
	}

	@Override
	@Nullable
	public EntityType<SkeletronEntity> getEntityType(ItemStack stack) {
		return AoAMonsters.SKELETRON.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
	}
}
