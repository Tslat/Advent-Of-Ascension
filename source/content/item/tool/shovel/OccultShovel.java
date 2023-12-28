package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OccultShovel extends BaseShovel {
	public OccultShovel() {
		super(AoATiers.OCCULT, -3.5f, AttackSpeed.SHOVEL, new Properties().durability(AoATiers.OCCULT.getUses()).rarity(Rarity.RARE));
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return super.getDestroySpeed(stack, state) * (1 + (stack.getDamageValue() / (float)stack.getMaxDamage()));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
