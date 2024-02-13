package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SweetSword extends BaseSword {
	public SweetSword() {
		super(AoATiers.SWEET);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (RandomUtil.percentChance(0.2f * attackCooldown)) {
			ItemStack drop = AoARegistries.ITEMS.registry().get()
					.getTag(AoATags.Items.CANDY)
					.map(tag -> tag.getRandomElement(target.level().random).map(Holder::value))
					.get().map(Item::getDefaultInstance)
					.orElseGet(() -> new ItemStack(Items.SUGAR, 3));

			target.spawnAtLocation(drop, target.getBbHeight() / 2f);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
