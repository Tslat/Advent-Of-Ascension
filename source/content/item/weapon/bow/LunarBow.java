package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LunarBow extends AoABow {
	public LunarBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Projectile applyArrowMods(Projectile projectile, @Nullable Entity shooter, ItemStack stack) {
		projectile.setNoGravity(true);
		projectile.setDeltaMovement(projectile.getDeltaMovement().scale(1.15f));

		return super.applyArrowMods(projectile, shooter, stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
