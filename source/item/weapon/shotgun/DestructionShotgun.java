package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import java.util.List;

public class DestructionShotgun extends BaseShotgun {
	public DestructionShotgun(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, pellets, durability, fireDelayTicks, knockbackFactor, recoil);
		setTranslationKey("DestructionShotgun");
		setRegistryName("aoa3:destruction_shotgun");
	}

	@Override
	public void fireShotgun(EntityLivingBase shooter, EnumHand hand, float spreadFactor, int pellets) {
		boolean charged = itemRand.nextInt(5) == 0;

		for (int i = 0; i < pellets; i++) {
			BaseBullet pellet = new EntityLimoniteBullet(shooter, this, hand, 4, charged ? 1.5f : 1.0f, 0, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor, (itemRand.nextFloat() - 0.5f) * spreadFactor);
			shooter.world.spawnEntity(pellet);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.DestructionShotgun.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
