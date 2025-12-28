package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class SpectralBow extends AoABow {
	public SpectralBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack findAmmo(LivingEntity shooter, ItemStack weaponStack, boolean infiniteAmmo) {
		ItemStack ammo = super.findAmmo(shooter, weaponStack, infiniteAmmo);

		return ammo.isEmpty() ? Items.ARROW.getDefaultInstance() : ammo;
	}

	@Override
	public List<ItemStack> drawProjectileItems(ItemStack bowStack, ItemStack ammoStack, LivingEntity shooter, float power, boolean intangibleProjectiles) {
		return super.drawProjectileItems(bowStack, ammoStack, shooter, power, true);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getBowDamage(stack)))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BOW_DRAW_TIME, LocaleUtil.ItemDescriptionType.NEUTRAL, Component.literal(Double.toString(((int)(72000 / getDrawSpeedMultiplier(stack)) / 720) / (double)100))));
	}
}
