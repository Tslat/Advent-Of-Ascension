package net.tslat.aoa3.block.generation.stone;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.animal.ShikEntity;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;

public class DenseStone extends Block {
	public DenseStone() {
		super(BlockUtil.generateBlockProperties(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY, 1.5f, 10f, ToolType.PICKAXE, 0));
	}

	@Override
	public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		super.playerWillDestroy(world, pos, state, player);

		if (!world.isClientSide && RandomUtil.oneInNChance(50) && ItemUtil.hasEnchantment(Enchantments.SILK_TOUCH, player.getItemInHand(Hand.MAIN_HAND))) {
			ShikEntity shik = new ShikEntity(AoAEntities.Animals.SHIK.get(), world);

			shik.teleportTo(pos.getX() + 0.5f, pos.getY() + 0.1f, pos.getZ() + 0.5f);
			world.addFreshEntity(shik);
		}
	}
}
