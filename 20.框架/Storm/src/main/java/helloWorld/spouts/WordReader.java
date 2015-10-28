package helloWorld.spouts;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * 数据源 - 此次数据源来自于对文件的阅读
 */
public class WordReader extends BaseRichSpout {

	private SpoutOutputCollector collector;
	private FileReader fileReader;
	private boolean completed = false;

	/**
	 * 成功获取时输出状态为 OK
	 * @param msgId
	 */
	public void ack(Object msgId) {
		System.out.println("OK:"+msgId);
	}
	/**
	 * 关闭时自动关闭文件流
	 */
	public void close() {
		if(null!= fileReader){
			try {
				fileReader.close();
			} catch (IOException e) {
			}
		}
	}
	/**
	 * 成功获取时输出状态为 FAIL
	 * @param msgId
	 */
	public void fail(Object msgId) {
		System.out.println("FAIL:"+msgId);
	}
	/**
	 * 在 SpoutTracker 类中被不停调用，且调用一次则向 Storm 集群中发射一条元组记录
	 */
	public void nextTuple() {
		/**
		 * 此次由于文件流只进行一次读取，完成状态下自动暂停后返回
		 */
		if(completed){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//Do nothing
			}
			return;
		}
		String str;
		BufferedReader reader = new BufferedReader(fileReader);
		try{
			// 将每非空行以键值形式发布给下一操作环节
			while((str = reader.readLine()) != null){
				this.collector.emit(new Values(str),str);
			}
		}catch(Exception e){
			throw new RuntimeException("Error reading tuple",e);
		}finally{
			completed = true;
		}
	}
	/**
	 * 服务启动时的操作 - 此处为获取服务配置地址，并
	 */
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		try {
			this.fileReader = new FileReader(conf.get("wordsFile").toString());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error reading file ["+conf.get("wordFile")+"]");
		}
		this.collector = collector;
	}
	/**
	 * 定义 ID 字段，且仅于字段分组模式下使用
	 *	可用 declarer.declareStream() 定义 Stream ID 以实现更复杂流拓扑结构
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
}
