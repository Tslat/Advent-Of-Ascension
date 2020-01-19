package net.tslat.aoa3.item.misc.summon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.boss.kingshroomus.EntityKingShroomus;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ShroomStone extends BossSpawningItem {
	public ShroomStone() {
		super("ShroomStone", "shroom_stone", SoundsRegister.mobKingShroomusHeal, EnumParticleTypes.CLOUD, EnumParticleTypes.SPELL_MOB);
	}

	@Override
	public void spawnBoss(World world, EntityPlayer summoner, double posX, double posY, double posZ) {
		EntityKingShroomus kingShroomus = new EntityKingShroomus(world);

		kingShroomus.setLocationAndAngles(posX, posY, posZ, itemRand.nextFloat() * 360f, 0f);
		world.spawnEntity(kingShroomus);
		StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.kingShroomus.spawn", summoner.getDisplayNameString()), kingShroomus, 50);
	}

	@Override
	public boolean canSpawnHere(World world, EntityPlayer player, double posX, double posY, double posZ) {
		if (world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.mysterium) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.mob.kingShroomus.incorrectDimension");

			return false;
		}

		if (world.collidesWithAnyBlock(new AxisAlignedBB(posX - 0.75d, posY, posZ - 0.75d, posX + 0.75d, posY + 4d, posZ + 0.75d))) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.noSpace");

			return false;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.ShroomStone.desc.1", TextFormatting.LIGHT_PURPLE));
		tooltip.add(StringUtil.getColourLocaleString("items.description.bossSummonItem.unstable", TextFormatting.DARK_RED));
	}
}
