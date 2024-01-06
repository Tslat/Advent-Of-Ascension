package net.tslat.aoa3.common.packet.packets;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.library.builder.ParticleBuilder;

import java.util.List;
import java.util.function.Supplier;

public class ServerParticlePacket implements AoAPacket {
	private final List<ParticleBuilder> particles;

	public ServerParticlePacket() {
		this(1);
	}

	public ServerParticlePacket(int amount) {
		this(new ObjectArrayList<>(amount));
	}

	public ServerParticlePacket(List<ParticleBuilder> particles) {
		this.particles = particles;
	}

	public ServerParticlePacket(ParticleBuilder... particles) {
		this(ObjectArrayList.of(particles));
	}

	public ServerParticlePacket particle(final ParticleBuilder particle) {
		this.particles.add(particle);

		return this;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeCollection(this.particles, (buf, builder) -> builder.toNetwork(buf));
	}

	public boolean isEmpty() {
		return this.particles.isEmpty();
	}

	public static ServerParticlePacket decode(FriendlyByteBuf buffer) {
		return new ServerParticlePacket(buffer.readCollection(ObjectArrayList::new, ParticleBuilder::fromNetwork));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			for (ParticleBuilder builder : this.particles) {
				builder.spawnParticles();
			}
		});

		context.get().setPacketHandled(true);
	}
}
