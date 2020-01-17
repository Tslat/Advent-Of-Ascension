package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.minions.EntityRosid;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_ROSIDIAN;

public class RosidianArmour extends AdventArmour {
	public RosidianArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_ROSIDIAN, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ROSIDIAN;
	}

	@Override
	public void onPostAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingDamageEvent event) {
		if (slots == null) {
			if (event.getAmount() >= 4)
				spawnRosid(plData.player());
		}
		else {
			if (itemRand.nextFloat() < 0.04 * slots.size())
				spawnRosid(plData.player());
		}
	}

	private void spawnRosid(EntityPlayer pl) {
		EntityRosid rosid = new EntityRosid(pl.world);

		rosid.setTamedBy(pl);
		rosid.setPosition(pl.posX, pl.posY, pl.posZ);
		pl.world.spawnEntity(rosid);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RosidianArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.RosidianArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
