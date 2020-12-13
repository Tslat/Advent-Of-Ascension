package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.CrystocoreEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class PowerStation extends BossAltarBlock {
	public PowerStation() {
		super(MaterialColor.YELLOW);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		CrystocoreEntity crystocore = new CrystocoreEntity(AoAEntities.Mobs.CRYSTOCORE.get(), player.world);

		crystocore.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 3, blockPos.getZ(), 0, 0);
		player.world.addEntity(crystocore);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.crystocore.spawn", player.getDisplayName().getFormattedText()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.GIANT_CRYSTAL.get();
	}
}
