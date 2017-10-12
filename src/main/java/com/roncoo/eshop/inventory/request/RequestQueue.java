package com.roncoo.eshop.inventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 请求内存队列
 * @author Administrator
 *
 */
public class RequestQueue {
	
	/**
	 * 内存队列
	 */
	private List<ArrayBlockingQueue<Request>> requestQueues = new ArrayList<ArrayBlockingQueue<Request>>();
	
	
	
	public static class Singleton{
		private static RequestQueue instance;
		
		static{
			instance = new RequestQueue();
		}
		
	    private static RequestQueue getInstance(){
	    	return instance;
	    }
	}
	
	/**
	 * jvm的机制去保证多线程并发安全
	 * 
	 * 内部类的初始化，一定只会发生一次，不管多少个线程并发去初始化
	 * 
	 * @return
	 */
	public static RequestQueue getInstance(){
		return Singleton.getInstance();
	}
	
	/**
	 * 添加一个内存队列
	 * @param queue
	 */
	public void addQueue(ArrayBlockingQueue<Request>queue){
	   this.requestQueues.add(queue);	
	}

}
