package net.tslat.aoa3.object.item.weapon.crossbow;

import com.google.common.collect.Lists;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.object.entity.projectile.arrow.CustomArrowEntity;
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
		arrow.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.damage.arrows", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, new StringTextComponent(Double.toString(getDamage()))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		List<ItemStack> projectiles = getChargedProjectiles(stack);

		if (isCharged(stack) && !projectiles.isEmpty()) {
			ItemStack projectile = projectiles.get(0);
			tooltip.add((new TranslationTextComponent("item.minecraft.crossbow.projectile")).append(" ").append(projectile.getDisplayName()));

			if (flag.isAdvanced() && projectile.getItem() == Items.FIREWORK_ROCKET) {
				List<ITextComponent> list1 = Lists.newArrayList();
				Items.FIREWORK_ROCKET.appendHoverText(projectile, worldIn, list1, flag);

				if (!list1.isEmpty()) {
					for(int i = 0; i < list1.size(); ++i) {
						list1.set(i, (new StringTextComponent("  ")).append(list1.get(i)).withStyle(TextFormatting.GRAY));
					}

					tooltip.addAll(list1);
				}
			}
		}
	}
}
