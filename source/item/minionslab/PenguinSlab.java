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
		PenguinEntity penguin = new PenguinEntity(AoAEntities.Minions.PENGUIN.get(), pl.world);

		penguin.setPosition(pl.getPosX(), pl.getPosY(), pl.getPosZ());
		penguin.setTamedBy(pl);
		pl.world.addEntity(penguin);
		if (pl instanceof ServerPlayerEntity && pl.world.getBiome(pl.getPosition()).doesSnowGenerate(pl.world, pl.getPosition()))
			AdvancementUtil.completeAdvancement((ServerPlayerEntity)pl, new ResourceLocation(AdventOfAscension.MOD_ID, "overworld/happy_feet"), "snowy_penguin_summon");

		return penguin;
	}
}
