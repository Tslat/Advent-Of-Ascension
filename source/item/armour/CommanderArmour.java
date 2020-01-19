package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_COMMANDER;

public class CommanderArmour extends AdventArmour {
	public CommanderArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_COMMANDER, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.COMMANDER;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (slots == null || plData.equipment().getCurrentFullArmourSet() != setType() && plData.player().world.getTotalWorldTime() % 20 == 0) {
			for (EntityLivingBase entity : plData.player().world.getEntitiesWithinAABB(EntityLivingBase.class, plData.player().getEntityBoundingBox().grow(2 * (slots == null ? 4 : slots.size())))) {
				if (entity != plData.player() && (entity instanceof EntityPlayer || (entity instanceof EntityTameable && ((EntityTameable)entity).getOwner() == plData.player())))
					entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 25, slots == null ? 1 : 0, false, true));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CommanderArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CommanderArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CommanderArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}
