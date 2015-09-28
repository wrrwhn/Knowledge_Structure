package com.yao.study.network.netty.codec;

import com.yao.utils.custom.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/1/4.
 */
public class RPCServerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes()<= 8){
            out.add("参数异常啊，你怎么破");
        }else{
            HashMap map= ByteUtils.decodeBytes(in);
            out.add(map);
        }
    }
}
