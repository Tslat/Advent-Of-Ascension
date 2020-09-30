package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShroomusSword extends BaseSword {
	public ShroomusSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("ShroomusSword");
		setRegistryName("aoa3:shroomus_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (attackCooldown > 0.75) {
			Collection<PotionEffect> effects = attacker.getActivePotionEffects();

			if (!effects.isEmpty()) {
				ArrayList<PotionEffect> removableEffects = new ArrayList<PotionEffect>(effects.size());

				for (PotionEffect effect : effects) {
					if (effect.getPotion().isBadEffect())
						removableEffects.add(effect);
				}

				for (PotionEffect effect : removableEffects) {
					target.addPotionEffect(effect);
					attacker.removePotionEffect(effect.getPotion());
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ShroomusSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
