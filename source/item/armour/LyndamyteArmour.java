package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_LYNDAMYTE;

public class LyndamyteArmour extends AdventArmour {
	public LyndamyteArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_LYNDAMYTE, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.LYNDAMYTE;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (slots != null && !plData.player().world.isRemote && EntityUtil.isMeleeDamage(event.getSource()) && itemRand.nextFloat() < 0.25f * slots.size())
			event.getSource().getTrueSource().setFire(5);
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots == null && EntityUtil.isMeleeDamage(event.getSource()))
			event.getEntityLiving().setFire(5);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyndamyteArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyndamyteArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
