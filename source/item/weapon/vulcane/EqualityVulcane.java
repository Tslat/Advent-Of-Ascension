package net.tslat.aoa3.item.weapon.vulcane;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class EqualityVulcane extends BaseVulcane implements AdventWeapon {
	public EqualityVulcane(double dmg, int durability) {
		super(dmg, durability);
		setUnlocalizedName("EqualityVulcane");
		setRegistryName("aoa3:equality_vulcane");
	}

	@Override
	public void doAdditionalEffect(EntityLivingBase target, EntityPlayer attacker) {
		EntityUtil.healEntity(attacker, 6.0f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.EqualityVulcane.desc.1", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
