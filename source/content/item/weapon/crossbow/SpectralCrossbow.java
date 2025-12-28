package net.tslat.aoa3.content.item.weapon.crossbow;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class SpectralCrossbow extends AoACrossbow {
	public SpectralCrossbow(Item.Properties properties) {
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
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.ARROW_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(Double.toString(getCrossbowDamage(stack)))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		List<ItemStack> projectiles = stack.get(DataComponents.CHARGED_PROJECTILES).getItems();

		if (isCharged(stack) && !projectiles.isEmpty()) {
			ItemStack projectile = projectiles.get(0);
			tooltip.add((Component.translatable("item.minecraft.crossbow.projectile")).append(" ").append(projectile.getDisplayName()));

			if (flag.isAdvanced() && projectile.getItem() == Items.FIREWORK_ROCKET) {
				List<Component> list1 = Lists.newArrayList();
				Items.FIREWORK_ROCKET.appendHoverText(projectile, context, list1, flag);

				if (!list1.isEmpty()) {
                    list1.replaceAll(element -> (Component.literal("  ")).append(element).withStyle(ChatFormatting.GRAY));

					tooltip.addAll(list1);
				}
			}
		}
	}
}
