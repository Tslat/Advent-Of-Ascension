package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.minions.EntityOrbling;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_SPACEKING;

public class SpacekingArmour extends AdventArmour {
	public SpacekingArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_SPACEKING, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.SPACEKING;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null && !EntityUtil.isEnvironmentalDamage(event.getSource()) && !EntityUtil.isPoisonDamage(event.getSource(), plData.player(), event.getAmount())) {
			EntityPlayer pl = plData.player();

			if (!pl.world.isRemote && pl.getHealth() > 0 && itemRand.nextInt(3) == 0) {
				EntityOrbling orbling = new EntityOrbling(pl.world);

				orbling.setPosition(pl.posX, pl.posY + 1.5, pl.posZ);
				orbling.setTamedBy(pl);
				pl.world.spawnEntity(orbling);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SpacekingArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SpacekingArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
