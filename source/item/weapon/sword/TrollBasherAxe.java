package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class TrollBasherAxe extends BaseSword {
	public TrollBasherAxe() {
		super(ItemUtil.customItemTier(1800, AttackSpeed.NORMAL, 12.0f, 4, 10, null));
	}

	@Override
	public int getHarvestLevel(ItemStack stack, ToolType tool, @Nullable PlayerEntity player, @Nullable BlockState blockState) {
		if (tool == ToolType.AXE)
			return 3;

		return super.getHarvestLevel(stack, tool, player, blockState);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();

		return material != Material.WOOD && material != Material.PLANT && material != Material.REPLACEABLE_PLANT ? super.getDestroySpeed(stack, state) : 4.0f;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.BLOCK_EFFICIENCY || enchantment == Enchantments.BLOCK_FORTUNE || super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
