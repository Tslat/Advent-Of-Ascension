package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.ItemUtil;

import java.util.function.Supplier;

public class RunePostBlock extends Block {
	private Supplier<RuneItem> rune;
	private final int lvl;
	private final float xp;

	public RunePostBlock(int level, float xp, Supplier<RuneItem> runeItem) {
		super(BlockUtil.generateBlockProperties(Material.ROCK, MaterialColor.BLACK, BlockUtil.UNBREAKABLE_HARDNESS, BlockUtil.UNBREAKABLE_RESISTANCE, SoundType.STONE));
		this.lvl = level;
		this.xp = xp;
		this.rune = runeItem;
	}

	public int getLevelReq() {
		return lvl;
	}

	public float getXpGain() {
		return xp;
	}

	public RuneItem getRune() {
		return rune.get();
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (player.getHeldItem(hand).getItem() == Items.WATER_BUCKET) {
			if (!world.isRemote) {
				if (!player.isCreative())
					player.getHeldItem(hand).shrink(1);

				ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.PURE_WATER_STONE.get()));
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}

			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}
