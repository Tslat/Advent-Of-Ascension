package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class HauntedGreatblade extends BaseGreatblade {
	public HauntedGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("HauntedGreatblade");
		setRegistryName("aoa3:haunted_greatblade");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!world.isRemote && isSelected && entity instanceof EntityPlayer && itemRand.nextInt(12000) == 0) {
			Potion potion = Potion.getPotionById(itemRand.nextInt(Potion.REGISTRY.getKeys().size()));

			while (potion == null) {
				potion = Potion.getPotionById(itemRand.nextInt(Potion.REGISTRY.getKeys().size()));
			}

			((EntityPlayer)entity).addPotionEffect(new PotionEffect(potion, 600, 0, false, true));

			TextComponentTranslation component = StringUtil.getColourLocale("item.HauntedGreatblade.desc." + (2 + itemRand.nextInt(16)), TextFormatting.DARK_PURPLE);

			component.getStyle().setItalic(true);

			entity.sendMessage(component);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HauntedGreatblade.desc.1", Enums.ItemDescriptionType.UNIQUE));
	}
}
