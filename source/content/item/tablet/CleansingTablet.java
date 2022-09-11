/*
package net.tslat.aoa3.content.item.tablet;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.tablet.CleansingTabletEntity;
import net.tslat.aoa3.content.entity.tablet.SoulTabletEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class CleansingTablet extends TabletItem {
	public CleansingTablet() {
		super(30f, 0.16f, 16, 7);
	}

	@Override
	protected SoulTabletEntity getTabletEntity(World world, ServerPlayerEntity placer) {
		return new CleansingTabletEntity(AoAEntities.Misc.CLEANSING_TABLET.get(), world, placer);
	}


	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
*/
