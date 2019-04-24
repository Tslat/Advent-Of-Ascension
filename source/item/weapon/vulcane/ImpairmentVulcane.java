package net.tslat.aoa3.item.weapon.vulcane;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class ImpairmentVulcane extends BaseVulcane implements AdventWeapon {
	public ImpairmentVulcane(double dmg, int durability) {
		super(dmg, durability);
		setUnlocalizedName("ImpairmentVulcane");
		setRegistryName("aoa3:impairment_vulcane");
	}

	@Override
	public void doAdditionalEffect(EntityLivingBase target, EntityPlayer attacker) {
		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 2, true, false));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.slow", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
