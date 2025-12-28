package net.tslat.aoa3.content.block.generation.stone;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.util.EnchantmentUtil;
import net.tslat.tme.api.util.RandomUtil;

public class DenseStone extends Block {
	public DenseStone(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		super.playerWillDestroy(level, pos, state, player);

		if (!level.isClientSide && RandomUtil.oneInNChance(50) && !EnchantmentUtil.hasEnchantment(level, player.getMainHandItem(), Enchantments.SILK_TOUCH)) {
			/*ShikEntity shik = new ShikEntity(AoAAnimals.SHIK.get(), world);

			shik.teleportTo(pos.getX() + 0.5f, pos.getY() + 0.1f, pos.getZ() + 0.5f);
			world.addFreshEntity(shik);*/
		}

		return state;
	}
}
