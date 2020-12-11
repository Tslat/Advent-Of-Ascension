package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.entity.misc.OccultBlockEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.SidedUtil;

import javax.annotation.Nullable;
import java.util.List;

public class OccultPickaxe extends BasePickaxe {
	public OccultPickaxe() {
		super(ItemUtil.customItemTier(3000, 11.0f, 6.0f, 6, 10, null));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		if (world.isRemote()) {
			for (int i = (int)(player.getPosX() - 4.0); i < (int)(player.getPosX() + 4.0); ++i) {
				for (int j = (int)(player.getPosY() - 4.0); j < (int)(player.getPosY() + 4.0); ++j) {
					for (int k = (int)(player.getPosZ() - 4.0); k < (int)(player.getPosZ() + 4.0); ++k) {
						if (world.getBlockState(new BlockPos(i, j, k)).isIn(Tags.Blocks.ORES)) {
							OccultBlockEntity entity = new OccultBlockEntity(world, new BlockPos(i, j, k));

							SidedUtil.spawnClientOnlyEntity(entity);
						}
					}
				}
			}
		}

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
