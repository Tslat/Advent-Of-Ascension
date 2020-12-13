package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.VoxxulonEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class VoxxulonAltar extends BossAltarBlock {
	public VoxxulonAltar() {
		super(MaterialColor.GREEN_TERRACOTTA);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		VoxxulonEntity voxxulon = new VoxxulonEntity(AoAEntities.Mobs.VOXXULON.get(), player.world);

		voxxulon.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.addEntity(voxxulon);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.voxxulon.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.VILE_STONE.get();
	}
}
