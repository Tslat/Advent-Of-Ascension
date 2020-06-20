package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.gun.BaseBullet;
import net.tslat.aoa3.entity.projectiles.gun.EntityLimoniteBullet;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class GoldenFury extends BaseGun {
	public GoldenFury(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("GoldenFury");
		setRegistryName("aoa3:golden_fury");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.FAST_RIFLE_FIRE;
	}

	@Override
	public BaseBullet findAndConsumeAmmo(EntityPlayer player, ItemStack gunStack, EnumHand hand) {
		if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.LIMONITE_BULLET), true, 1 + EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, gunStack))) {
			EntityLimoniteBullet bullet = new EntityLimoniteBullet(player, (BaseGun)gunStack.getItem(), hand, 120, 0);

			if (!player.world.isRemote) {
				for (int i = 0; i < 6; i++) {
					((WorldServer)player.world).spawnParticle(EnumParticleTypes.DRAGON_BREATH, bullet.posX + itemRand.nextGaussian() / 5d, bullet.posY + itemRand.nextGaussian() / 5d, bullet.posZ + itemRand.nextGaussian() / 5d, 1, 0, 0, 0, 0d);
				}
			}

			return bullet;
		}

		return null;
	}

	@Override
	protected void doImpactEffect(Entity target, EntityLivingBase shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(bullet.world, (target.posX + bullet.posX) / 2d, (target.posY + bullet.posY) / 2d, (target.posZ + bullet.posZ) / 2d);

		cloud.setOwner(shooter);
		cloud.setParticle(EnumParticleTypes.DRAGON_BREATH);
		cloud.setRadius(1f);
		cloud.setDuration(20);
		cloud.setRadiusPerTick((5.0F - cloud.getRadius()) / (float)cloud.getDuration());
		cloud.addEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 0));

		bullet.world.spawnEntity(cloud);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.FlamingFury.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
