package net.tslat.aoa3.item.food;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Lunacrike extends Item {
	public Lunacrike() {
		super(new Item.Properties().group(AoAItemGroups.FOOD).food(
				new Food.Builder()
						.hunger(0)
						.saturation(0)
						.setAlwaysEdible()
						.effect(new PotionUtil.EffectBuilder(Effects.JUMP_BOOST, 40).level(2).build(), 1)
						.build()));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		if (!world.isRemote)
			entity.addVelocity(entity.getMotion().getX(), 2, entity.getMotion().getZ());

		return super.onItemUseFinish(stack, world, entity);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}