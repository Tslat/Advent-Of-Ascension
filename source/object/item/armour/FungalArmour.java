package net.tslat.aoa3.object.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
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
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class FungalArmour extends AdventArmour {
	public FungalArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:fungal", 50, new int[] {5, 6, 8, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.FUNGAL;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots != null && DamageUtil.isMeleeDamage(event.getSource())) {
			if (RandomUtil.percentChance(0.2f * slots.size())) {
				if (event.getSource().getEntity() instanceof LivingEntity)
					((LivingEntity)event.getSource().getEntity()).addEffect(new EffectInstance(Effects.POISON, 60, 1, true, true));

				if (PlayerUtil.isWearingFullSet(plData.player(), this.setType()) && RandomUtil.oneInNChance(4)) {
					for (LivingEntity mob : plData.player().level.getEntitiesOfClass(LivingEntity.class, plData.player().getBoundingBox().inflate(5), EntityUtil.Predicates.HOSTILE_MOB)) {
						mob.addEffect(new EffectInstance(Effects.POISON, 60, 0, true, true));
					}
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
