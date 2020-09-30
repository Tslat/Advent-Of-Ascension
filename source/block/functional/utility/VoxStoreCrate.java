package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.entity.npcs.trader.EntityStoreKeeper;
import net.tslat.aoa3.utils.StringUtil;

import java.util.Random;

public class VoxStoreCrate extends Block {
	public VoxStoreCrate() {
		super(Material.WOOD);
		setTranslationKey("VoxStoreCrate");
		setRegistryName("aoa3:vox_store_crate");
		setHardness(5.0f);
		setResistance(3f);
		setSoundType(SoundType.WOOD);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!world.isRemote && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItem(EnumHand.MAIN_HAND)) == 0) {
			EntityStoreKeeper storeKeeper = new EntityStoreKeeper(world);

			storeKeeper.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			world.spawnEntity(storeKeeper);
			player.sendMessage(StringUtil.getLocale("message.mob.voxStoreKeeper.spawn"));
		}
	}
}
