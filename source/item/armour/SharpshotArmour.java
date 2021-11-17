package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class SharpshotArmour extends AdventArmour {
	public SharpshotArmour(EquipmentSlotType slot) {
		super(ItemUtil.customArmourMaterial("aoa3:sharpshot", 54, new int[] {4, 6, 9, 5}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 5), slot);
	}

	@Override
	public AdventArmour.Type setType() {
		return AdventArmour.Type.SHARPSHOT;
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EquipmentSlotType> slots, LivingHurtEvent event) {
		Item gun;

		if (DamageUtil.isGunDamage(event.getSource()) && ((gun = plData.player().getMainHandItem().getItem()) instanceof BaseGun || (gun = plData.player().getOffhandItem().getItem()) instanceof BaseGun)) {
			if (slots == null) {
				if (gun instanceof BaseSniper)
					event.setAmount(event.getAmount() * 1.1f);
			}
			else {
				event.setAmount(event.getAmount() * (1 + (0.07f * slots.size())));
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.sharpshot_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.sharpshot_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
