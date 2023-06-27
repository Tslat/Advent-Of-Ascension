package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class MercurialArmour extends AdventArmour {
	public MercurialArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:mercurial", 42, new int[] {3, 8, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public Type setType() {
		return Type.MERCURIAL;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingDamageEvent event) {
		if (!plData.player().level().isClientSide && event.getSource().is(DamageTypeTags.IS_EXPLOSION) && event.getAmount() > 0) {
			if (slots == null) {
				plData.player().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 320, 1, true, true));
			}
			else if (plData.equipment().getCurrentFullArmourSet() != setType()) {
				plData.player().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80 * slots.size(), 0, true, true));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.mercurial_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.mercurial_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.mercurial_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
