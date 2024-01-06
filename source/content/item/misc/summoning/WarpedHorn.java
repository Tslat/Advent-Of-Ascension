package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
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
	public SkeletronEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack) {
		return EntitySpawningUtil.spawnEntity(level, AoAMobs.SKELETRON.get(), position, MobSpawnType.TRIGGERED);
	}

	@Override
	@Nullable
	public EntityType<SkeletronEntity> getEntityType(ItemStack stack) {
		return AoAMobs.SKELETRON.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
	}
}
