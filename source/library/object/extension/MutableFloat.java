package net.tslat.aoa3.library.object.extension;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.tslat.tme.api.util.StreamCodecUtil;

public class MutableFloat extends org.apache.commons.lang3.mutable.MutableFloat {
    public static final StreamCodec<ByteBuf, MutableFloat> STREAM_CODEC = StreamCodecUtil.MUTABLE_FLOAT.map(value -> new MutableFloat(value.floatValue()), value -> new MutableFloat(value.floatValue()));

    public MutableFloat() {}

    public MutableFloat(final float value) {
        super(value);
    }

    public MutableFloat(final Number value) {
        super(value);
    }

    public MutableFloat(final String value) {
        super(value);
    }

    public void multiply(float multiplier) {
        setValue(getValue() * multiplier);
    }

    public void divide(float divisor) {
        setValue(getValue() / divisor);
    }
}
