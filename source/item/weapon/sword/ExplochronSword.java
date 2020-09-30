package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
		if (attackCooldown > 0.75f) {
			double offset = target.width / 1.99d;
			double offsetX = MathHelper.clamp(attacker.posX - target.posX, -offset, offset);
			double offsetY = MathHelper.clamp(attacker.posY + attacker.getEyeHeight() - target.posY, -0.1, target.height + 0.1);
			double offsetZ = MathHelper.clamp(attacker.posZ - target.posZ, -offset, offset);

			WorldUtil.createExplosion(attacker, attacker.world, target.posX + offsetX, target.posY + offsetY, target.posZ + offsetZ, 1.75f);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ExplochronSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
