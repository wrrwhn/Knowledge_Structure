package com.yao.utils.apache.storm.topologies.drpc;

import backtype.storm.task.OutputCollector;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 加法处理器
 * @param <String>
 */
public class AdderBolt<String> extends BaseBasicBolt{

	private static final Object NULL = "NULL";
	private OutputCollector collector;

	/***
	 * 默认以 + 分割，累计各值相加后直接返回
	 * @param input
	 * @param collector
	 */
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		//Parse the add expression
		String[] numbers = (String[]) input.getString(1).split("\\+");
		Integer added = 0;
		try{
			if(numbers.length<2){
//				throw new InvalidParameterException("Should be at least 2 numbers");
				collector.emit(new Values("InvalidParameterException -> Should be at least 2 numbers", NULL));
				return;
			}
			for(String num : numbers){
				added += Integer.parseInt((java.lang.String) num);
			}
		}catch(Exception e){
			collector.emit(new Values(input.getValue(0), NULL));
		}
		collector.emit(new Values(input.getValue(0), added));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("id","result"));
	}
}
