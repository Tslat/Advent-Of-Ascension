package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class VoxPondsEvents {
	public static void doPlayerTick(PlayerEntity pl) {
		if (pl instanceof ServerPlayerEntity && PlayerUtil.shouldPlayerBeAffected(pl) && !DamageUtil.isPlayerEnvironmentallyProtected((ServerPlayerEntity)pl)) {
			pl.attackEntityFrom(new DamageSource("vox_ponds").setDamageBypassesArmor().setDamageIsAbsolute(), 1f);
			AdvancementUtil.completeAdvancement((ServerPlayerEntity)pl, new ResourceLocation(AdventOfAscension.MOD_ID, "vox_ponds/oops"), "vox_ponds_atmosphere_damage");
		}
	}
}
