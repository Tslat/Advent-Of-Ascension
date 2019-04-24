package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class RunicSword extends BaseSword implements AdventWeapon {
	public RunicSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("RunicSword");
		setRegistryName("aoa3:runic_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote)
			return false;

		if (ItemUtil.checkCooledItemProc(player, 0.2f))
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 60, 4));

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.RunicSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
