package net.tslat.aoa3.content.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class GhastlyArmour extends AdventArmour {
	public GhastlyArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:ghastly", 62, new int[] {5, 8, 8, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.GHASTLY;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots) {
		if (slots != null && plData.player().level.getGameTime() % 5 == 0 && plData.player().isShiftKeyDown()) {
			for (LivingEntity entity : plData.player().level.getEntitiesOfClass(LivingEntity.class, plData.player().getBoundingBox().inflate(4 * slots.size()), EntityUtil.Predicates.HOSTILE_MOB)) {
				entity.addEffect(new EffectInstance(Effects.GLOWING, 6, 0, true, false));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ghastly_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.ghastly_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
