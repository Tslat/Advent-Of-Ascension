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
import net.tslat.aoa3.content.entity.boss.king_bambambam.KingBamBamBamEntity;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExplosiveIdol extends BossSpawningItem<KingBamBamBamEntity> {
	public ExplosiveIdol() {
		super(0, new Properties().rarity(Rarity.RARE).stacksTo(1));
	}

	@Override
	public KingBamBamBamEntity spawnBoss(ServerLevel level, Vec3 position, ItemStack stack, int playerCount) {
		KingBamBamBamEntity kingBamBamBam = EntitySpawningUtil.spawnEntity(level, AoAMonsters.KING_BAMBAMBAM.get(), position, MobSpawnType.TRIGGERED);


		applyMultiplayerHealthBoost(kingBamBamBam, playerCount);

		return kingBamBamBam;
	}

	@Override
	@Nullable
	public EntityType<KingBamBamBamEntity> getEntityType(ItemStack stack) {
		return AoAMonsters.KING_BAMBAMBAM.get();
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
	}
}
