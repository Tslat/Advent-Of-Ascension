package net.tslat.aoa3.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.arrow.EntityTippedHollyArrow;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

import javax.annotation.Nullable;
import java.util.List;

public class TippedHollyArrow extends HollyArrow {
	public TippedHollyArrow(String name, String registryName) {
		super(name, registryName);
		setHasSubtypes(true);
	}

	@Override
	public EntityHollyArrow createArrow(World world, BaseBow bow, ItemStack ammo, EntityLivingBase shooter, double baseDamage) {
		EntityTippedHollyArrow arrow = new EntityTippedHollyArrow(world, bow, shooter, baseDamage);
		arrow.setPotionEffect(ammo);
		return arrow;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab)) {
			for (PotionType potiontype : PotionType.REGISTRY) {
				if (!potiontype.getEffects().isEmpty())
					items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potiontype));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		PotionUtils.addPotionTooltip(stack, tooltip, 0.5F);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String translated = I18n.translateToLocal(("item.TippedHollyArrow.") + ".name");

		if (translated.startsWith("item."))
			translated = PotionUtils.getPotionFromItem(stack).getNamePrefixed(I18n.translateToLocal("item.TippedHollyArrow.generic.name"));

		return translated;
	}
}
