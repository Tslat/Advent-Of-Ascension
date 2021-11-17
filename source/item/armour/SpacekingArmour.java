package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.player.PlayerDataManager;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class SpacekingArmour extends AdventArmour {
	public SpacekingArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:spaceking", 62, new int[] {4, 8, 9, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.SPACEKING;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots == null && !DamageUtil.isEnvironmentalDamage(event.getSource()) && !DamageUtil.isPoisonDamage(event.getSource(), plData.player(), event.getAmount())) {
			PlayerEntity pl = plData.player();

			if (!pl.level.isClientSide && pl.getHealth() > 0 && RandomUtil.oneInNChance(3)) {
				/*OrblingEntity orbling = new OrblingEntity(AoAEntities.Minions.ORBLING.get(), pl.level);

				orbling.setPos(pl.getX(), pl.getY() + 1.5, pl.getZ());
				orbling.tame(pl);
				pl.level.addFreshEntity(orbling);*/ // TODO
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.spaceking_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.spaceking_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
