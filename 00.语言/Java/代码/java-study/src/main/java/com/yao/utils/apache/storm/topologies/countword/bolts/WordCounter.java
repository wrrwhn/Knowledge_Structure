package com.yao.utils.apache.storm.topologies.countword.bolts;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class WordCounter extends BaseRichBolt {

	Integer id;
	String name;
	Map<String, Integer> counters;
	private OutputCollector collector;

	/**
	 * At the end of the spout (when the cluster is shutdown
	 * We will show the word counters
	 */
	@Override
	public void cleanup() {
		System.out.println("-- Word Counter ["+name+"-"+id+"] --");
		for(Map.Entry<String, Integer> entry : counters.entrySet()){
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
	}

	/**
	 * 若数据来源于 word 分组，则自动统计添加
	 * 若数据来源于流 ID 为 signals 且分组为 action、内容为 refreshCache 则清除缓存信息
	 */
	@Override
	public void execute(Tuple input) {
			String str = null; 
			try{
				str = input.getStringByField("word");
			}catch (IllegalArgumentException e) {
				//Do nothing
			}
			
			if(str!=null){
				if(!counters.containsKey(str)){
					counters.put(str, 1);
				}else{
					Integer c = counters.get(str) + 1;
					counters.put(str, c);
				}
			}else{
				if(input.getSourceStreamId().equals("signals")){
					str = input.getStringByField("action");
					if("refreshCache".equals(str)) {
//						counters.clear();
						counters.put(str, counters.containsKey(str)? counters.get(str)+1: 1);
					}
				}
			}
		//Set the tuple as Acknowledge
		collector.ack(input);
	}

	/**
	 * 初始化创建时的伴随操作
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.counters = new HashMap<String, Integer>();
		this.collector = collector;
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {}
}
