package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_MERCURIAL;

public class MercurialArmour extends AdventArmour {
	public MercurialArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_MERCURIAL, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.MERCURIAL;
	}

	@Override
	public void onPreAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingAttackEvent event) {
		if (slots == null && !event.getEntity().world.isRemote && event.getSource().isExplosion() && event.getAmount() > 0) {
			float remainingDamage = event.getAmount() * 0.4f;

			plData.player().inventory.damageArmor(10 + remainingDamage * 2 * itemRand.nextFloat());
			event.setCanceled(true);
		}
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && event.getSource().isExplosion() && event.getAmount() > 0)
			event.setAmount(event.getAmount() * (1 - 0.15f * slots.size()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.MercurialArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.MercurialArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
