package ex.ntytest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/***
 * 生命周期每个时期处理的事情
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    //当从服务器接收到一条消息时被调用；
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));
    }
    //在到服务器的连接已经建立之后将被调用；
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.copiedBuffer("链接已通 neety rocks ",CharsetUtil.UTF_8));
    }

    //exceptionCaught()——在处理过程中引发异常时被调用
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
