package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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

public class RockbasherSword extends BaseSword implements AdventWeapon {
	public RockbasherSword(final ToolMaterial material, Float dmg, Double speed) {
		super(material, dmg, speed);
		setUnlocalizedName("RockbasherSword");
		setRegistryName("aoa3:rockbasher_sword");
	}

	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity target) {
		if (player.world.isRemote || !(target instanceof EntityLivingBase))
			return false;

		((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, (int)(40 * player.getCooledAttackStrength(0.0f)), 1));

		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.damage.weak", TextFormatting.DARK_GREEN));
	}
}
