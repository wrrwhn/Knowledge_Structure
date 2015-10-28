package spouts.banktransactions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;


public class TransactionsSpouts extends BaseRichSpout{

	private static final Integer MAX_FAILS = 2;
	Map<Integer,String> messages;
	Map<Integer,Integer> transactionFailureCount;
	Map<Integer,String> toSend;
	private SpoutOutputCollector collector;  
	
	static Logger LOG = Logger.getLogger(TransactionsSpouts.class);

	public void ack(Object msgId) {
		messages.remove(msgId);
		LOG.info("Message fully processed ["+msgId+"]");
	}

	public void close() {
		
	}

	/***
	 * 发布失败情况下，在有效次数内进行生性，否则进行异常提示
	 * @param msgId
	 */
	public void fail(Object msgId) {
		if(!transactionFailureCount.containsKey(msgId))
			throw new RuntimeException("Error, transaction id not found ["+msgId+"]");
		Integer transactionId = (Integer) msgId;
		
		//Get the transactions fail
		Integer failures = transactionFailureCount.get(transactionId) + 1;
		if(failures >= MAX_FAILS){
			//If exceeds the max fails will go down the topology
			throw new RuntimeException("Error, transaction id ["+transactionId+"] has had many errors ["+failures+"]");
		}
		//If not exceeds the max fails we save the new fails quantity and re-send the message 
		transactionFailureCount.put(transactionId, failures);
		toSend.put(transactionId, messages.get(transactionId));
		LOG.info("Re-sending message ["+msgId+"]");
	}

	/***
	 * 	处理并将业务数据发布给下一环节
	 * 		若无待处理数据情况下，直接休眠一毫秒
	 */
	public void nextTuple() {
		if(!toSend.isEmpty()){
			for(Map.Entry<Integer, String> transactionEntry : toSend.entrySet()){
				Integer transactionId = transactionEntry.getKey();
				String transactionMessage = transactionEntry.getValue();
				collector.emit(new Values(transactionMessage),transactionId);
			}
			toSend.clear();
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {}
	}

	/**
	 * 发送数据初始化
	 * @param conf
	 * @param context
	 * @param collector
	 */
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		Random random = new Random();
		messages = new HashMap<Integer, String>();
		toSend = new HashMap<Integer, String>();
		transactionFailureCount = new HashMap<Integer, Integer>();
		for(int i = 0; i< 100; i++){
			messages.put(i, "transaction_"+random.nextInt());
			transactionFailureCount.put(i, 0);
		}
		toSend.putAll(messages);
		this.collector = collector;
	}

	/**
	 * 定义元组名称
	 * @param declarer
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("transactionMessage"));
	}

}
