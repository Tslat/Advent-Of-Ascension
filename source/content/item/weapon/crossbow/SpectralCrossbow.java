package net.tslat.aoa3.content.item.weapon.crossbow;

import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SpectralCrossbow extends BaseCrossbow {
	public SpectralCrossbow(double damage, int durability) {
		super(damage, durability);
	}

	@Override
	protected ItemStack findAmmo(ItemStack crossbowStack, LivingEntity player, boolean infiniteAmmo) {
		return new ItemStack(Items.ARROW);
	}

	@Override
	public void doArrowMods(CustomArrowEntity arrow, Entity shooter, int useTicksRemaining) {
		arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.damage.arrows", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, new TextComponent(Double.toString(getDamage()))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		List<ItemStack> projectiles = getChargedProjectiles(stack);

		if (isCharged(stack) && !projectiles.isEmpty()) {
			ItemStack projectile = projectiles.get(0);
			tooltip.add((new TranslatableComponent("item.minecraft.crossbow.projectile")).append(" ").append(projectile.getDisplayName()));

			if (flag.isAdvanced() && projectile.getItem() == Items.FIREWORK_ROCKET) {
				List<Component> list1 = Lists.newArrayList();
				Items.FIREWORK_ROCKET.appendHoverText(projectile, worldIn, list1, flag);

				if (!list1.isEmpty()) {
					for(int i = 0; i < list1.size(); ++i) {
						list1.set(i, (new TextComponent("  ")).append(list1.get(i)).withStyle(ChatFormatting.GRAY));
					}

					tooltip.addAll(list1);
				}
			}
		}
	}
}
