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
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class ExplochronSword extends BaseSword {
	public ExplochronSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("ExplochronSword");
		setRegistryName("aoa3:explochron_sword");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, float attackCooldown) {
		if (attackCooldown > 0.75f)
			WorldUtil.createExplosion(attacker, attacker.world, target.posX, target.posY, target.posZ, 1.75f);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ExplochronSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
