package net.tslat.aoa3.item.tablet;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.misc.tablet.EntityEnergyTablet;
import net.tslat.aoa3.entity.misc.tablet.EntitySoulTablet;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EnergyTablet extends TabletItem {
	public EnergyTablet(String name, String registryName, float placementCost, float tickSoulDrain, int levelReq, int effectRadius) {
		super(name, registryName, placementCost, tickSoulDrain, levelReq, effectRadius);
	}

	@Override
	protected EntitySoulTablet getTabletEntity(World world, EntityPlayer placer) {
		return new EntityEnergyTablet(world, placer);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getLocaleString("item.EnergyTablet.desc.1"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
