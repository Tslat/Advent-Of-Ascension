package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.FakePlayer;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Pickmax extends BasePickaxe {
	public Pickmax() {
		super(ItemUtil.customItemTier(3000, 8.0f, 6.0f, 6, 10, null));
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {
		super.onBlockDestroyed(stack, world, state, pos, entity);

		if (!world.isRemote && entity instanceof PlayerEntity && !(entity instanceof FakePlayer) && state.isIn(Tags.Blocks.STONE)) {
			for (int i = pos.getX() - 1; i < pos.getX() + 2; i++) {
				for (int j = pos.getY() - 1; j < pos.getY() + 2; j++) {
					for (int k = pos.getZ() - 1; k < pos.getZ() + 2; k++) {
						if (pos.getX() == i && pos.getY() == j && pos.getZ() == k)
							continue;

						PlayerEntity pl = (PlayerEntity)entity;
						BlockPos breakPos = new BlockPos(i, j, k);
						BlockState extraBlock = world.getBlockState(breakPos);

						if (extraBlock.isIn(Tags.Blocks.STONE) && world.getBlockState(pos).getPlayerRelativeBlockHardness(pl, world, pos) / extraBlock.getPlayerRelativeBlockHardness(pl, world, breakPos) < 10f)
							WorldUtil.harvestAdditionalBlock(world, (PlayerEntity)entity, breakPos, true);
					}
				}
			}
		}

		return true;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
