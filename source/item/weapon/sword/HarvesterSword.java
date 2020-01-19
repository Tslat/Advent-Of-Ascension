package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class HarvesterSword extends BaseSword implements AdventWeapon {
	public HarvesterSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("HarvesterSword");
		setRegistryName("aoa3:harvester_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (itemRand.nextFloat() < 0.1 * attackCooldown)
			EntityUtil.healEntity(attacker, 1);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.HarvesterSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
