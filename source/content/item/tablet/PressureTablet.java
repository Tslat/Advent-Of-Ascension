package net.tslat.aoa3.content.item.tablet;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
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
	protected SoulTabletEntity getTabletEntity(Level world, ServerPlayer placer) {
		return new PressureTabletEntity(AoAMiscEntities.PRESSURE_TABLET.get(), world, placer);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
