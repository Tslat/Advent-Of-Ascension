package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class MercurialArmour extends AdventArmour {
	public MercurialArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:mercurial", 42, new int[] {3, 8, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.MERCURIAL;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (!plData.player().level.isClientSide && event.getSource().isExplosion() && event.getAmount() > 0) {
			if (slots == null) {
				plData.player().addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 320, 1, true, true));
			}
			else if (plData.equipment().getCurrentFullArmourSet() != setType()) {
				plData.player().addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 80 * slots.size(), 0, true, true));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.mercurial_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.mercurial_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.mercurial_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
