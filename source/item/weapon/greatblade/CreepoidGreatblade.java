package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import java.util.List;

public class CreepoidGreatblade extends BaseGreatblade {
	public CreepoidGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("CreepoidGreatblade");
		setRegistryName("aoa3:creepoid_greatblade");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase attacker, Entity target, float dmgDealt) {
		if (!(attacker instanceof EntityPlayer) || ((EntityPlayer)attacker).getCooledAttackStrength(0.0f) > 0.75f) {
			double offset = target.width / 1.99d;
			double offsetX = MathHelper.clamp(attacker.posX - target.posX, -offset, offset);
			double offsetY = MathHelper.clamp(attacker.posY + attacker.getEyeHeight() - target.posY, -0.1, target.height + 0.1);
			double offsetZ = MathHelper.clamp(attacker.posZ - target.posZ, -offset, offset);

			WorldUtil.createExplosion(attacker, attacker.world, target.posX + offsetX, target.posY + offsetY, target.posZ + offsetZ, 1.5f);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.CreepoidGreatblade.desc.1", TextFormatting.DARK_GREEN));
	}
}
