package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
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

public class VoidSword extends BaseSword implements AdventWeapon {
	public VoidSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("VoidSword");
		setRegistryName("aoa3:void_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (itemRand.nextFloat() < 0.1f * attackCooldown)
			target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30,20));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.VoidSword.desc.1", TextFormatting.DARK_GREEN));
	}
}
