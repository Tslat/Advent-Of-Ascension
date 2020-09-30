package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class CrystalExtensionShrine extends Block {
	public CrystalExtensionShrine() {
		super(Material.ROCK);
		setTranslationKey("CrystalExtensionShrine");
		setRegistryName("aoa3:crystal_extension_shrine");
		setHardness(-1f);
		setResistance(999999999f);
		setSoundType(SoundType.GLASS);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!player.getHeldItem(hand).isEmpty()) {
			ItemStack heldStack = player.getHeldItem(hand);

			if (heldStack.getItem() == ItemRegister.RAINBOW_DRUSE) {
				if (!world.isRemote ) {
					List<EntityItem> crystalList = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos.getX() - 5, pos.getY() - 1, pos.getZ() - 5, pos.getX() + 5, pos.getY() + 1, pos.getZ() + 5), entity -> isCrystal(entity.getItem().getItem()));
					int count = 0;

					for (EntityItem item : crystalList) {
						count += item.getItem().getCount();

						if (count >= 10)
							break;
					}

					if (count < 10) {
						PlayerUtil.notifyPlayer((EntityPlayerMP)player, "message.feedback.crystalExtensionShrine.crystals");

						return true;
					}

					for (int i = 10; i > 0; i++) {
						EntityItem entity = crystalList.get(0);
						ItemStack stack = entity.getItem();
						int size = stack.getCount();

						stack.shrink(i);

						if (stack.getCount() <= 0)
							crystalList.remove(0);

						i -= size;
					}

					if (!player.capabilities.isCreativeMode)
						heldStack.shrink(1);
					
					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.GIANT_CRYSTAL));
				}

				return true;
			}
		}

		return false;
	}

	private static boolean isCrystal(Item item) {
		return item == ItemRegister.BLUE_CRYSTAL || item == ItemRegister.GREEN_CRYSTAL || item == ItemRegister.PURPLE_CRYSTAL || item == ItemRegister.RED_CRYSTAL || item == ItemRegister.WHITE_CRYSTAL || item == ItemRegister.YELLOW_CRYSTAL;
	}
}
