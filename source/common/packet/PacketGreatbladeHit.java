package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.item.weapon.LongReachWeapon;

public class PacketGreatbladeHit implements IMessage {
	private int entityId;

	public PacketGreatbladeHit() {}

	public PacketGreatbladeHit(final int id) {
		entityId = id;
	}

	public void fromBytes(final ByteBuf buffer) {
		entityId = buffer.readInt();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(entityId);
	}

	public static class Handler implements IMessageHandler<PacketGreatbladeHit, IMessage> {
		public IMessage onMessage(final PacketGreatbladeHit msg, final MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;

			player.getServerWorld().addScheduledTask(() -> {
				Entity target = player.world.getEntityByID(msg.entityId);
				ItemStack weapon = player.getHeldItem(EnumHand.MAIN_HAND);

				if (target != null && weapon.getItem() instanceof LongReachWeapon) {
					double reach = ((LongReachWeapon)weapon.getItem()).getReach();

					if (player.getDistanceSq(target) < reach * reach && player.canEntityBeSeen(target)) {
						((LongReachWeapon)weapon.getItem()).attackEntity(weapon, target, player, -1);
					}
				}
			});
			return null;
		}
	}
}
