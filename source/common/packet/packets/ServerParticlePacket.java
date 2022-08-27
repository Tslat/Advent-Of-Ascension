package net.tslat.aoa3.common.packet.packets;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ServerParticlePacket implements AoAPacket {
	private final ArrayList<ParticleData> particles = new ArrayList<ParticleData>(1);
	private final boolean longRange;

	public ServerParticlePacket() {
		this(false);
	}

	public ServerParticlePacket(boolean longRange) {
		this.longRange = longRange;
	}

	public ServerParticlePacket particle(ParticleOptions particle, Entity entity) {
		return particle(particle, entity, false);
	}

	public ServerParticlePacket particle(ParticleOptions particle, Entity entity, boolean randomPosInBB) {
		if (randomPosInBB) {
			return particle(particle, entity.getX() + RandomUtil.randomScaledGaussianValue(entity.getBbWidth()), entity.getY() + RandomUtil.randomScaledGaussianValue(entity.getBbHeight()), entity.getZ() + RandomUtil.randomScaledGaussianValue(entity.getBbWidth()));
		}
		else {
			return particle(particle, entity, 0, 0, 0);
		}
	}

	public ServerParticlePacket particle(ParticleOptions particle, Entity entity, boolean randomPosInBB, double velX, double velY, double velZ) {
		return particle(particle, entity, randomPosInBB, velX, velY, velZ, 1);
	}

	public ServerParticlePacket particle(ParticleOptions particle, Entity entity, boolean randomPosInBB, double velX, double velY, double velZ, int amount) {
		return particle(particle, entity, randomPosInBB, velX, velY, velZ, amount, 1);
	}

	public ServerParticlePacket particle(ParticleOptions particle, Entity entity, boolean randomPosInBB, double velX, double velY, double velZ, int amountPerParticle, int particlesToGen) {
		for (int i = 0; i < particlesToGen; i++) {
			if (randomPosInBB) {
				particle(particle, entity.getX() + RandomUtil.randomScaledGaussianValue(entity.getBbWidth()), entity.getY() + RandomUtil.randomScaledGaussianValue(entity.getBbHeight()), entity.getZ() + RandomUtil.randomScaledGaussianValue(entity.getBbWidth()), velX, velY, velZ, amountPerParticle);
			}
			else {
				particle(particle, entity, velX, velY, velZ);
			}
		}

		return this;
	}

	public ServerParticlePacket particle(ParticleOptions particle, Entity entity, double velX, double velY, double velZ) {
		return particle(particle, entity.getX(), entity.getY(), entity.getZ(), velX, velY, velZ, 1);
	}

	public ServerParticlePacket particle(ParticleOptions particle, Entity entity, double velX, double velY, double velZ, int amount) {
		return particle(particle, entity.getX(), entity.getY(), entity.getZ(), velX, velY, velZ, amount);
	}

	public ServerParticlePacket particle(ParticleOptions particle, double x, double y, double z) {
		return particle(particle, x, y, z, 1);
	}

	public ServerParticlePacket particle(ParticleOptions particle, double x, double y, double z, int amount) {
		return particle(particle, x, y, z, 0, 0, 0, amount);
	}

	public ServerParticlePacket particle(ParticleOptions particle, double x, double y, double z, double velX, double velY, double velZ) {
		return particle(particle, x, y, z, velX, velY, velZ, 1);
	}

	public ServerParticlePacket particle(ParticleOptions particle, double x, double y, double z, double velX, double velY, double velZ, int amount) {
		particles.add(new ParticleData(particle, x, y, z, velX, velY, velZ, amount));

		return this;
	}

	public boolean isLongRange() {
		return this.longRange;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeInt(particles.size());

		for (ParticleData data : particles) {
			data.toBuffer(buffer);
		}
	}

	public static ServerParticlePacket decode(FriendlyByteBuf buffer) {
		ServerParticlePacket packet = new ServerParticlePacket();
		int count = buffer.readInt();

		for (int i = 0; i < count; i++) {
			packet.particles.add(ParticleData.fromBuffer(buffer));
		}

		return packet;
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			for (ParticleData data : particles) {
				ClientOperations.addParticle(data.particle, data.x, data.y, data.z, data.velX, data.velY, data.velZ, data.amount);
			}
		});

		context.get().setPacketHandled(true);
	}

	private static <T extends ParticleOptions> T deserializeParticle(FriendlyByteBuf buffer, ParticleType<T> particleType) {
		return particleType.getDeserializer().fromNetwork(particleType, buffer);
	}

	private record ParticleData(ParticleOptions particle, double x, double y, double z, double velX, double velY, double velZ, int amount) {
		private void toBuffer(FriendlyByteBuf buffer) {
				buffer.writeInt(Registry.PARTICLE_TYPE.getId(this.particle.getType()));
				this.particle.writeToNetwork(buffer);
				buffer.writeDouble(this.x);
				buffer.writeDouble(this.y);
				buffer.writeDouble(this.z);
				buffer.writeDouble(this.velX);
				buffer.writeDouble(this.velY);
				buffer.writeDouble(this.velZ);
				buffer.writeInt(this.amount);
			}

			private static ParticleData fromBuffer(FriendlyByteBuf buffer) {
				ParticleType<? extends ParticleOptions> particle = Registry.PARTICLE_TYPE.byId(buffer.readInt());

				if (particle == null)
					particle = ParticleTypes.BLOCK_MARKER;

				return new ParticleData(deserializeParticle(buffer, particle), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readInt());
			}
		}
}
