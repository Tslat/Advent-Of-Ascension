package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class BoreicArmour extends AdventArmour {
	public BoreicArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:boreic", 62, new int[] {4, 8, 10, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public Type setType() {
		return Type.BOREIC;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		Player pl = plData.player();

		if (pl.isInWater() && !DamageUtil.isEnvironmentalDamage(event.getSource())) {
			if (slots != null) {
				WorldUtil.createExplosion(pl, pl.level, pl.blockPosition() , 0.7f + 0.3f * slots.size());
			}
			else {
				for (LivingEntity entity : pl.level.getEntitiesOfClass(LivingEntity.class, pl.getBoundingBox().inflate(4), EntityUtil.Predicates.HOSTILE_MOB)) {
					entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1, false, true));
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.boreic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
