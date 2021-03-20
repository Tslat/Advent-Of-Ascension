package net.tslat.aoa3.item.minionslab;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.minion.PenguinEntity;
import net.tslat.aoa3.util.AdvancementUtil;

public class PenguinSlab extends BaseSlab {
	public PenguinSlab() {
		super(1, 120, 1, 9);
	}

	@Override
	public AoAMinion activateSlab(PlayerEntity pl, ItemStack stack) {
		PenguinEntity penguin = new PenguinEntity(AoAEntities.Minions.PENGUIN.get(), pl.level);

		penguin.setPos(pl.getX(), pl.getY(), pl.getZ());
		penguin.tame(pl);
		pl.level.addFreshEntity(penguin);
		if (pl instanceof ServerPlayerEntity && pl.level.getBiome(pl.blockPosition()).shouldSnow(pl.level, pl.blockPosition()))
			AdvancementUtil.completeAdvancement((ServerPlayerEntity)pl, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/happy_feet"), "snowy_penguin_summon");

		return penguin;
	}
}
