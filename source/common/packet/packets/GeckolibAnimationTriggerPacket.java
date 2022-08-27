package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.misc.AoAAnimatable;

import java.util.function.Supplier;

public class GeckolibAnimationTriggerPacket implements AoAPacket {
	private final int entityId;
	private final String anim;

	public GeckolibAnimationTriggerPacket(int id, final String anim) {
		this.entityId = id;
		this.anim = anim;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeVarInt(this.entityId);
		buffer.writeUtf(this.anim);
	}

	public static GeckolibAnimationTriggerPacket decode(FriendlyByteBuf buffer) {
		return new GeckolibAnimationTriggerPacket(buffer.readVarInt(), buffer.readUtf(32767));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			Entity entity = ClientOperations.getWorld().getEntity(this.entityId);

			if (entity instanceof AoAAnimatable animatable)
				animatable.triggerAnim(entity, this.anim);
		});

		context.get().setPacketHandled(true);
	}
}
