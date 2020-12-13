package net.tslat.aoa3.library.misc;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public class PortalFloaterParticleType extends ParticleType<PortalFloaterParticleType.Data> {
	public PortalFloaterParticleType(boolean alwaysShow) {
		super(alwaysShow, Data.DESERIALIZER);
	}

	public static class Data implements IParticleData {
		private final ParticleType<PortalFloaterParticleType.Data> particleType;

		public final BlockPos portalPos;
		public final float red;
		public final float green;
		public final float blue;
		public final float alpha;

		public Data(ParticleType<PortalFloaterParticleType.Data> particleType, BlockPos portalPos, int colour) {
			this(particleType, portalPos, (colour >> 16) / 255.0f, ((colour >> 8) & 0xff) / 255.0f, (colour & 0xff) / 255.0f, (colour >> 24) / 255.0f);
		}

		public Data(ParticleType<PortalFloaterParticleType.Data> particleType, BlockPos portalPos, float red, float green, float blue, float alpha) {
			this.particleType = particleType;
			this.portalPos = portalPos;
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.alpha = alpha == 0 ? 1 : alpha;
		}

		@Override
		public ParticleType<?> getType() {
			return particleType;
		}

		@Override
		public void write(PacketBuffer buffer) {
			buffer.writeBlockPos(portalPos);
			buffer.writeFloat(red);
			buffer.writeFloat(green);
			buffer.writeFloat(blue);
			buffer.writeFloat(alpha);
		}

		@Override
		public String getParameters() {
			return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(getType()), red, green, blue, alpha);
		}

		public static final IDeserializer<Data> DESERIALIZER = new IDeserializer<Data>() {
			public Data deserialize(ParticleType<Data> particleType, StringReader reader) throws CommandSyntaxException {
				reader.expect(' ');
				int x = reader.readInt();
				reader.expect(' ');
				int y = reader.readInt();
				reader.expect(' ');
				int z = reader.readInt();
				reader.expect(' ');
				float red = (float)reader.readDouble();
				reader.expect(' ');
				float green = (float)reader.readDouble();
				reader.expect(' ');
				float blue = (float)reader.readDouble();
				reader.expect(' ');
				float alpha = (float)reader.readDouble();

				return new Data(particleType, new BlockPos(x, y, z), red, green, blue, alpha);
			}

			public Data read(ParticleType<Data> particleType, PacketBuffer buffer) {
				return new Data(particleType, buffer.readBlockPos(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
			}
		};
	}
}
