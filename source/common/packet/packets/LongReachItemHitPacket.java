package net.tslat.aoa3.common.packet.packets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.item.LongReachItem;
import org.apache.logging.log4j.Level;

import java.util.function.Supplier;

public class LongReachItemHitPacket implements AoAPacket {
	private final int entityId;

	public LongReachItemHitPacket(final int id) {
		entityId = id;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(entityId);
	}

	public static LongReachItemHitPacket decode(PacketBuffer buffer) {
		return new LongReachItemHitPacket(buffer.readInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ServerPlayerEntity player = context.get().getSender();

		if (player == null) {
			Logging.logMessage(Level.ERROR, "Received a long reach item hit packet with no assigned player, how does this even happen forge? C'mon bro.");

			return;
		}

		player.closeContainer();

		if (player.container == null || player.openContainer instanceof PlayerContainer) {
			context.get().enqueueWork(() -> {
				ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);

				if (!(stack.getItem() instanceof LongReachItem)) {
					Logging.logMessage(Level.WARN, "Received long reach item packet but player is not holding a long reach item.");

					return;
				}

				Entity target = player.world.getEntityByID(entityId);

				if (target == null) {
					Logging.logMessage(Level.WARN, "No entity found for long reach item packet, skipping");

					return;
				}

				LongReachItem weapon = (LongReachItem)stack.getItem();

				if (!player.isHandActive()) {
					double reach = weapon.getReach();

					if (player.getDistanceSq(target) < reach * reach) {
						RayTraceContext traceContext = new RayTraceContext(new Vec3d(player.getPosX(), player.getPosY() + player.getEyeHeight(), player.getPosZ()), new Vec3d(target.getPosX(), target.getPosY() + target.getEyeHeight(), target.getPosZ()), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, null);
						RayTraceResult rayTrace = player.world.rayTraceBlocks(traceContext);
						BlockPos pos = new BlockPos(rayTrace.getHitVec());

						if (rayTrace.getType() == RayTraceResult.Type.MISS || (rayTrace.getType() == RayTraceResult.Type.BLOCK && !player.world.getBlockState(pos).isOpaqueCube(player.world, pos)))
							weapon.hitEntity(stack, target, player, -1);
					}
				}
			});
		}

		context.get().setPacketHandled(true);
	}
}
