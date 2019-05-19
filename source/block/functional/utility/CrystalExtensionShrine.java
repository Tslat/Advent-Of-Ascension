package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;

public class CrystalExtensionShrine extends Block {
	public CrystalExtensionShrine() {
		super(Material.ROCK);
		setUnlocalizedName("CrystalExtensionShrine");
		setRegistryName("aoa3:crystal_extension_shrine");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !player.getHeldItem(hand).isEmpty()) {
			ItemStack stack = player.getHeldItem(hand);
			Item item = stack.getItem();

			if (item == ItemRegister.crystalGreen || item == ItemRegister.crystalBlue || item == ItemRegister.crystalPurple || item == ItemRegister.crystalRed || item == ItemRegister.crystalYellow || item == ItemRegister.crystalWhite) {
				AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);
				int lvl = cap.getLevel(Enums.Skills.AUGURY);

				if (lvl < 36) {
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.essenceEmpowered, 8));
				}
				else if (lvl < 66) {
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.essenceDark, 8));
				}
				else {
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.essenceDivine, 8));
				}

				if (!player.capabilities.isCreativeMode)
					stack.shrink(1);

				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.crystalExtensionShrineUse, SoundCategory.BLOCKS, 1.0f, 1.0f);
			}
		}

		return true;
	}
}
