package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class CommanderArmour extends AdventArmour {
	public CommanderArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:commander", 62, new int[] {4, 9, 9, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 7), slot);
	}

	@Override
	public Type getSetType() {
		return Type.COMMANDER;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		if (slots == null || plData.equipment().getCurrentFullArmourSet() != getSetType() && plData.player().level().getGameTime() % 20 == 0) {
			for (LivingEntity entity : plData.player().level().getEntitiesOfClass(LivingEntity.class, plData.player().getBoundingBox().inflate(2 * (slots == null ? 4 : slots.size())))) {
				if (entity != plData.player() && (entity instanceof Player || (entity instanceof TamableAnimal && ((TamableAnimal)entity).getOwner() == plData.player())))
					entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 25, slots == null ? 1 : 0, false, true));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.commander_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.commander_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.commander_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
