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
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.player.ServerPlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class VoidArmour extends AdventArmour {
	public VoidArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:void", 25, new int[] {3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 2), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.VOID;
	}

	@Override
	public void onPostAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingDamageEvent event) {
		if (slots == null || plData.equipment().getCurrentFullArmourSet() != setType()) {
			if (!plData.player().level.isClientSide && event.getSource().getEntity() instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity)event.getSource().getEntity();

				if (DamageUtil.isMeleeDamage(event.getSource())) {
					if (slots == null) {
						if (RandomUtil.oneInNChance(5))
							attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 30, 20, true, true));
					}
					else {
						if (random.nextFloat() < 0.025f * slots.size())
							attacker.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 30, 20, true, true));
					}
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.void_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.void_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
