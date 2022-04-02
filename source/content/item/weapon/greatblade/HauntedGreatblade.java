package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HauntedGreatblade extends BaseGreatblade {
	public HauntedGreatblade() {
		super(22.0f, AttackSpeed.GREATBLADE, 1370);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
		if (!world.isClientSide && isSelected && entity instanceof Player && RandomUtil.oneInNChance(12000)) {
			MobEffect effect = MobEffect.byId(RandomUtil.randomNumberUpTo(ForgeRegistries.POTIONS.getValues().size()));

			while (effect == null) {
				effect = MobEffect.byId(RandomUtil.randomNumberUpTo(ForgeRegistries.POTIONS.getValues().size()));
			}

			((Player)entity).addEffect(new MobEffectInstance(effect, 600, 0, false, true));

			TranslatableComponent component = LocaleUtil.getLocaleMessage("item.aoa3.haunted_greatblade.message." + RandomUtil.randomNumberBetween(1, 16), ChatFormatting.DARK_PURPLE);

			component.getStyle().withItalic(true);
			entity.sendMessage(component, Util.NIL_UUID);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
