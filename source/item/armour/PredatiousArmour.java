package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
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

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_PREDATIOUS;

public class PredatiousArmour extends AdventArmour {
	public PredatiousArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_PREDATIOUS, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.PREDATIOUS;
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && EntityUtil.isRangedDamage(event.getSource(), event.getEntityLiving(), event.getAmount()))
			event.setAmount(event.getAmount() * (1f + (0.1f * (float)slots.size())));
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null && event.getEntityLiving() != null && EntityUtil.isMeleeDamage(event.getSource()) && event.getSource().getImmediateSource() instanceof EntityLivingBase)
			event.getSource().getImmediateSource().attackEntityFrom(DamageSource.causeThornsDamage(plData.player()), 1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.PredatiousArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.PredatiousArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
