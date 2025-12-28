package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class Wrecker extends AoAGun {
	public Wrecker(Item.Properties properties) {
		super(properties);
	}

	@Override
	public float getRecoilForShot(ItemStack stack, LivingEntity shooter) {
		if (shooter instanceof Player pl)
			return pl.getFoodData().getFoodLevel() / 20f * super.getRecoilForShot(stack, shooter);

		return super.getRecoilForShot(stack, shooter);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
