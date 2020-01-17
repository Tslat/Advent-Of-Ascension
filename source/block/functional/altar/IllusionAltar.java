package net.tslat.aoa3.block.functional.altar;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.boss.elusive.EntityElusive;
import net.tslat.aoa3.entity.boss.elusive.EntityElusiveClone;
import net.tslat.aoa3.utils.StringUtil;

public class IllusionAltar extends BossAltarBlock {
	public IllusionAltar() {
		super("IllusionAltar", "illusion_altar");
	}

	@Override
	protected void doActivationEffect(EntityPlayer player, EnumHand hand, IBlockState state, BlockPos blockPos) {
		EntityElusive elusive = new EntityElusive(player.world);
		EntityElusiveClone clone = new EntityElusiveClone(elusive);
		double posX = (int)(blockPos.getX() + player.getLookVec().x * -10);
		double posZ = (int)(blockPos.getZ() + player.getLookVec().z * -10);

		clone.setLocationAndAngles(posX, player.world.getHeight((int)posX, (int)posZ), posZ, player.rotationYawHead, 0);

		posX += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);
		posZ += RANDOM.nextBoolean() ? 10 + RANDOM.nextInt(10) : -10 - RANDOM.nextInt(10);

		elusive.setLocationAndAngles(posX, player.world.getHeight((int)posX, (int)posZ), posZ, 0, 0);
		player.world.spawnEntity(elusive);
		player.world.spawnEntity(clone);
		sendSpawnMessage(player, StringUtil.getLocaleWithArguments("message.mob.elusive.spawn", player.getDisplayNameString()), blockPos);
	}

	@Override
	protected Item getActivationItem() {
		return ItemRegister.staringEye;
	}
}
