package net.tslat.aoa3.object.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class BoreicArmour extends AdventArmour {
	public BoreicArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:boreic", 62, new int[] {4, 8, 10, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.BOREIC;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		PlayerEntity pl = plData.player();

		if (pl.isInWater() && !DamageUtil.isEnvironmentalDamage(event.getSource())) {
			if (slots != null) {
				WorldUtil.createExplosion(pl, pl.level, pl.blockPosition() , 0.7f + 0.3f * slots.size());
			}
			else {
				for (LivingEntity entity : pl.level.getEntitiesOfClass(LivingEntity.class, pl.getBoundingBox().inflate(4), EntityUtil.Predicates.HOSTILE_MOB)) {
					entity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 1, false, true));
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
