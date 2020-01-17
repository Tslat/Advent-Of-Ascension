package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import java.util.List;

public class CaramelCarver extends BaseSword implements AdventWeapon {
	public CaramelCarver(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("CaramelCarver");
		setRegistryName("aoa3:caramel_carver");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		final int multiplier = attacker.world.getEntitiesWithinAABB(EntityLivingBase.class, attacker.getEntityBoundingBox().grow(5.0f), PredicateUtil.IS_HOSTILE_MOB).size();

		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int)(multiplier * 15 * attackCooldown), 1));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.slow", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CaramelCarver.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
