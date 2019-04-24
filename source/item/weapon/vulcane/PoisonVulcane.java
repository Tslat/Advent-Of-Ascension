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

public class PoisonVulcane extends BaseVulcane implements AdventWeapon {
	public PoisonVulcane(double dmg, int durability) {
		super(dmg, durability);
		setUnlocalizedName("PoisonVulcane");
		setRegistryName("aoa3:poison_vulcane");
	}

	@Override
	public void doAdditionalEffect(EntityLivingBase target, EntityPlayer attacker) {
		target.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0, true, false));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.poison", TextFormatting.DARK_GREEN));
		super.addInformation(stack, world, tooltip, flag);
	}
}
