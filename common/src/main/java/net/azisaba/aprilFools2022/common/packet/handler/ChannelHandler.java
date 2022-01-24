package net.azisaba.aprilFools2022.common.packet.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class ChannelHandler extends ChannelDuplexHandler {
    private boolean frozen = false;

    public void freeze() {
        frozen = true;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!frozen) super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (!frozen) super.write(ctx, msg, promise);
    }
}
