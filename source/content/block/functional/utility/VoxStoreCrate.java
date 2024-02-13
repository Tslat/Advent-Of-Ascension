package net.tslat.aoa3.content.block.functional.utility;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.aoa3.content.entity.npc.trader.StoreKeeperEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

public class VoxStoreCrate extends Block {
	public VoxStoreCrate(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		if (!world.isClientSide && !ItemUtil.hasEnchantment(player.getMainHandItem(), Enchantments.SILK_TOUCH)) {
			StoreKeeperEntity storeKeeper = new StoreKeeperEntity(AoANpcs.STORE_KEEPER.get(), world);

			storeKeeper.moveTo(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			storeKeeper.finalizeSpawn((ServerLevel)world, world.getCurrentDifficultyAt(pos), MobSpawnType.EVENT, null, null);
			world.addFreshEntity(storeKeeper);
			player.sendSystemMessage(LocaleUtil.getLocaleMessage(AoANpcs.STORE_KEEPER.get().getDescriptionId() + ".spawn"));
		}

		return state;
	}
}
