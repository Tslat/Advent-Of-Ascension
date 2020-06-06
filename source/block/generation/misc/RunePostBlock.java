package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.UnbreakableBlock;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.utils.ItemUtil;

public class RunePostBlock extends UnbreakableBlock {
	private RuneItem rune;
	private final int lvl;
	private final float xp;

	public RunePostBlock(String name, String registryName, int level, float xp) {
		super(name, registryName, Material.ROCK);
		this.lvl = level;
		this.xp = xp;
	}

	public int getLevelReq() {
		return lvl;
	}

	public void setRune(RuneItem rune) {
		this.rune = rune;
	}

	public float getXpGain() {
		return xp;
	}

	public RuneItem getRune() {
		return rune;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && player.getHeldItem(hand).getItem() == Items.WATER_BUCKET) {
			if (!player.capabilities.isCreativeMode)
				player.getHeldItem(hand).shrink(1);

			ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.PURE_WATER_STONE));
			world.setBlockToAir(pos);

			return true;
		}

		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
}
