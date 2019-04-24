package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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

public class CandlefireSword extends BaseSword implements AdventWeapon {
	public CandlefireSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("CandlefireSword");
		setRegistryName("aoa3:candlefire_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote)
			return false;

		if (target.isBurning() && player.getCooledAttackStrength(0.0f) > 0.75f)
			EntityUtil.dealMeleeDamage(player, target, 11, false);

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.CandlefireSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
