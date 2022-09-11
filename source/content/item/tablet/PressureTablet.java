/*
package net.tslat.aoa3.content.item.tablet;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.tablet.PressureTabletEntity;
import net.tslat.aoa3.content.entity.tablet.SoulTabletEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class PressureTablet extends TabletItem {
	public PressureTablet() {
		super(37.5f, 0.302f, 71, 10);
	}

	@Override
	protected SoulTabletEntity getTabletEntity(World world, ServerPlayerEntity placer) {
		return new PressureTabletEntity(AoAEntities.Misc.PRESSURE_TABLET.get(), world, placer);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
*/
