package net.tslat.aoa3.content.item.armour;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class ExoplateArmour extends AdventArmour {
	public ExoplateArmour(ArmorItem.Type slot) {
		super(ItemUtil.customArmourMaterial("aoa3:exoplate", 46, new int[] {4, 6, 8, 4}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 3), slot);
	}

	@Override
	public Type setType() {
		return Type.EXOPLATE;
	}

	@Override
	public void onAttackReceived(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && !DamageUtil.isEnvironmentalDamage(event.getSource())) {
			LivingEntity entity = event.getEntity();
			BlockPos playerPos = entity.blockPosition();
			int lightLvl = Mth.clamp(2 + WorldUtil.getLightLevel((ServerLevel)entity.level(), playerPos, false, false), 2, 15);

			event.setAmount(event.getAmount() * (1 - (1 - (lightLvl / 15f)) * 0.0625f * slots.size()));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.exoplate_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
