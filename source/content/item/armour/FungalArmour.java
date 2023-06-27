package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.*;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class FungalArmour extends AdventArmour {
	public FungalArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:fungal", 50, new int[] {5, 6, 8, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public Type setType() {
		return Type.FUNGAL;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		if (slots != null && DamageUtil.isMeleeDamage(event.getSource())) {
			if (RandomUtil.percentChance(0.2f * slots.size())) {
				if (event.getSource().getEntity() instanceof LivingEntity)
					((LivingEntity)event.getSource().getEntity()).addEffect(new MobEffectInstance(MobEffects.POISON, 60, 1, true, true));

				if (PlayerUtil.isWearingFullSet(plData.player(), this.setType()) && RandomUtil.oneInNChance(4)) {
					for (LivingEntity mob : plData.player().level().getEntitiesOfClass(LivingEntity.class, plData.player().getBoundingBox().inflate(5), EntityUtil.Predicates.HOSTILE_MOB)) {
						mob.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0, true, true));
					}
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.fungal_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
