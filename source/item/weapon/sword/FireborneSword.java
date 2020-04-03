package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class FireborneSword extends BaseSword implements AdventWeapon {
	public FireborneSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("FireborneSword");
		setRegistryName("aoa3:fireborne_sword");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		boolean flaming = target.isBurning();

		if (!flaming && !target.isImmuneToFire())
			target.setFire(1);

		boolean success = super.hitEntity(stack, target, attacker);

		if (!flaming && !success)
			target.setFire(0);

		return success;
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		target.setFire((int)(3 * attackCooldown));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.fire", Enums.ItemDescriptionType.POSITIVE));
	}
}
