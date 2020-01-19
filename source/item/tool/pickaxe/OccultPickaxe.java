package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.BlockOre;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.MaterialsRegister;
import net.tslat.aoa3.entity.misc.EntityOccultBlock;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class OccultPickaxe extends BasePickaxe {
	public OccultPickaxe() {
		super("OccultPickaxe", "occult_pickaxe", MaterialsRegister.TOOL_OCCULT);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (world.isRemote) {
			for (int i = (int)(player.posX - 4.0); i < (int)(player.posX + 4.0); ++i) {
				for (int j = (int)(player.posY - 4.0); j < (int)(player.posY + 4.0); ++j) {
					for (int k = (int)(player.posZ - 4.0); k < (int)(player.posZ + 4.0); ++k) {
						if (world.getBlockState(new BlockPos(i, j, k)).getBlock() instanceof BlockOre)
							world.spawnEntity(new EntityOccultBlock(world, new BlockPos(i, j, k)));
					}
				}
			}
		}

		return super.onItemRightClick(world, player, hand);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.OccultPickaxe.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
