package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.misc.EntityTidalWave;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class TidalGreatblade extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public TidalGreatblade(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("TidalGreatblade");
		setRegistryName("aoa3:tidal_greatblade");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase attacker, Entity target, float dmgDealt) {
		double xOffset = MathHelper.cos(attacker.rotationYaw / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = MathHelper.sin(attacker.rotationYaw / 180.0F * (float)Math.PI) * 0.7F;

		attacker.world.spawnEntity(new EntityTidalWave(attacker.world, attacker, xOffset, zOffset));
		attacker.world.spawnEntity(new EntityTidalWave(attacker.world, attacker, 0, 0));
		attacker.world.spawnEntity(new EntityTidalWave(attacker.world, attacker, -xOffset, -zOffset));
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.TidalGreatblade.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
