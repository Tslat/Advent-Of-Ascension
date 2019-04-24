package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURSEALORD;

public class SealordHelmet extends AdventArmour {
	public SealordHelmet(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURSEALORD, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ALL;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		if (cap.getPlayer().isInWater()) {
			EntityPlayer pl = cap.getPlayer();

			pl.motionX *= 1.2000000476837158;
			pl.motionX = MathHelper.clamp(pl.motionX, -1.0, 1.0);

			pl.motionZ *= 1.2000000476837158;
			pl.motionZ = MathHelper.clamp(pl.motionZ, -1.0, 1.0);

			if (pl.isSneaking()) {
				pl.motionX /= 1.4;
				pl.motionZ /= 1.4;
			}

			if (pl.motionY > 0.0d && pl.motionY <= 0.4d)
				pl.motionY *= 1.3d;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.3", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.SealordHelmet.desc.4", TextFormatting.DARK_GREEN));
	}
}
