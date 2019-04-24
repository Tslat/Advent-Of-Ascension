package net.nevermine.item.weapon.sword;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.common.nevermine;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

public class CrystalMaul extends ItemSword implements SwordInterface {
	public CrystalMaul(final Item.ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setCreativeTab(Weaponizer.SwordsTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public boolean hitEntity(final ItemStack stack, final EntityLivingBase livingBase1, final EntityLivingBase livingBase2) {
		stack.damageItem(1, livingBase2);

		if (!(livingBase1 instanceof IEntityMultiPart) && nevermine.rand.nextInt(5) == 4) {
			double resist = 1 - livingBase1.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();

			if (resist != 0.0) {
				final float f4 = MathHelper.sqrt_double(livingBase2.motionX * livingBase2.motionX + livingBase2.motionZ * livingBase2.motionZ);
				livingBase1.addVelocity((livingBase2.motionX * 1.5 * 0.6000000238418579 / f4) * resist, 0.3 * resist, (livingBase2.motionZ * 1.5 * 0.6000000238418579 / f4) * resist);
				livingBase1.velocityChanged = true;
			}
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.CrystalMaul.desc.1", EnumChatFormatting.DARK_GREEN));
	}
}
