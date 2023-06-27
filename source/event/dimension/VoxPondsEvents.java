package net.tslat.aoa3.event.dimension;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class VoxPondsEvents {
	public static void doPlayerTick(Player pl) {
		if (pl instanceof ServerPlayer player && PlayerUtil.shouldPlayerBeAffected(player) && !DamageUtil.isPlayerEnvironmentallyProtected(player)) {
			player.hurt(DamageUtil.miscDamage(AoADamageTypes.SUFFOCATION, player.level()), 1f);
			AdvancementUtil.completeAdvancement(player, new ResourceLocation(AdventOfAscension.MOD_ID, "vox_ponds/oops"), "vox_ponds_atmosphere_damage");
		}
	}
}
