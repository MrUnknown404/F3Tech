package mrunknown404.f3tech.network.message;

import io.netty.buffer.ByteBuf;
import mrunknown404.f3tech.handlers.SlimeSeedHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageSlimeSeed implements IMessage, IMessageHandler<MessageSlimeSeed, IMessage> {
	public long seed;
	
	public MessageSlimeSeed() {
		this.seed = 0;
	}
	
	public MessageSlimeSeed(long seed) {
		this.seed = seed;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.seed = buf.readLong();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.seed);
	}
	
	@Override
	public IMessage onMessage(final MessageSlimeSeed message, final MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			SlimeSeedHandler.seed = message.seed;
		}
		
		return null;
	}
}
