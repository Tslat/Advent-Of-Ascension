package net.tslat.aoa3.item.weapon.bow;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class PrimordialBow extends BaseBow {
	public PrimordialBow(double damage, float drawSpeedMultiplier, int durability) {
		super(damage, drawSpeedMultiplier, durability);
		setTranslationKey("PrimordialBow");
		setRegistryName("aoa3:primordial_bow");
	}

	@Override
	public void doBlockImpact(EntityHollyArrow arrow, IBlockState blockState, Entity shooter) {
		EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(arrow.world, arrow.posX, arrow.posY, arrow.posZ);

		cloud.addEffect(new PotionEffect(MobEffects.WITHER, 40, 0, false, true));
		cloud.setParticle(EnumParticleTypes.SMOKE_NORMAL);
		cloud.setRadius(2);
		cloud.setDuration(200);

		arrow.world.spawnEntity(cloud);
	}

	@Override
	public void doImpactEffect(EntityHollyArrow arrow, Entity target, Entity shooter, float damage) {
		EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(arrow.world, arrow.posX, arrow.posY, arrow.posZ);

		cloud.addEffect(new PotionEffect(MobEffects.WITHER, 40, 0, false, true));
		cloud.setParticle(EnumParticleTypes.SMOKE_NORMAL);
		cloud.setRadius(2);
		cloud.setDuration(200);

		arrow.world.spawnEntity(cloud);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.PrimordialBow.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
