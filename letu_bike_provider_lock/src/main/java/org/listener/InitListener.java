package org.listener;


import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.server.lock.LockServerHandler;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 初始化监听器
 * 
 * @author haobo
 *
 */

public class InitListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Resource
	AppConfig appConfig;

	public void onApplicationEvent(ContextRefreshedEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getApplicationContext().getParent() == null) {
			new Thread() {//新建线程启动TCP服务，否则tomcat将阻塞
				@Override
				public void run() {
					try {
						EventLoopGroup bossGruop=new NioEventLoopGroup();//用于服务器端接受客户端的连接
				        EventLoopGroup workGroup=new NioEventLoopGroup();//用于网络事件的处理
				        try
				        {
				            ServerBootstrap b=new ServerBootstrap();
				            b.group(bossGruop, workGroup).channel(NioServerSocketChannel.class);
				            b.childHandler(new ChannelInitializer<SocketChannel>()
				            {
				                @Override
				                protected void initChannel(SocketChannel arg0) throws Exception
				                {
				                	arg0.pipeline().addLast(new IdleStateHandler(305,0,0,TimeUnit.SECONDS));
				                    arg0.pipeline().addLast(new LockServerHandler());
				                }
				              
				                
				            }).option(ChannelOption.SO_BACKLOG, 10240).option(ChannelOption.SO_KEEPALIVE, true);//指定此套接口排队的最大连接个数
				            ChannelFuture f=b.bind(appConfig.getTcpserver_port()).sync();
				            System.out.println("TCP服务启动...");
				            f.channel().closeFuture().sync();
				            
				        }
				        finally
				        {
				            bossGruop.shutdownGracefully();
				            workGroup.shutdownGracefully();
				        }
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();

		}
	}

}
