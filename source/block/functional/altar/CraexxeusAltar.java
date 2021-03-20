package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.boss.CraexxeusEntity;
import net.tslat.aoa3.util.LocaleUtil;

public class CraexxeusAltar extends BossAltarBlock {
	public CraexxeusAltar() {
		super(MaterialColor.COLOR_CYAN);
	}

	@Override
	protected void doActivationEffect(PlayerEntity player, Hand hand, BlockState state, BlockPos blockPos) {
		CraexxeusEntity craexxeus = new CraexxeusEntity(AoAEntities.Mobs.CRAEXXEUS.get(), player.level);

		int offsetX = player.getRandom().nextBoolean() ? -11 : 11;
		int offsetZ = player.getRandom().nextBoolean() ? -11 : 11;

		craexxeus.moveTo(blockPos.getX() + offsetX, blockPos.getY(), blockPos.getZ() + offsetZ, 0, 0);
		player.level.addFreshEntity(craexxeus);
		sendSpawnMessage(player, LocaleUtil.getLocaleMessage("message.mob.craexxeus.spawn", player.getDisplayName()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return AoAItems.ANCIENT_RING.get();
	}
}
