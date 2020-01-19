package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_SHARPSHOT;

public class SharpshotArmour extends AdventArmour {
	public SharpshotArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_SHARPSHOT, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.SHARPSHOT;
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		Item gun;

		if (EntityUtil.isGunDamage(event.getSource()) && ((gun = plData.player().getHeldItemMainhand().getItem()) instanceof BaseGun || (gun = plData.player().getHeldItemOffhand().getItem()) instanceof BaseGun)) {
			if (slots == null) {
				if (gun instanceof BaseSniper)
					event.setAmount(event.getAmount() * 1.38f);
			}
			else {
				event.setAmount(event.getAmount() * (1 + (0.07f * slots.size())));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SharpshotArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SharpshotArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
