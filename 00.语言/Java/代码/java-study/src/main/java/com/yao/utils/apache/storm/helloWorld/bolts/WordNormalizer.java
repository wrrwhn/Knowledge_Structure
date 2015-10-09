package com.yao.utils.apache.storm.helloWorld.bolts;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/***
 * 文字序列化 - 用于进行文字的解析处理
 */
public class WordNormalizer extends BaseBasicBolt {

	public void cleanup() {}

	/**
	 * 将获取到的文本信息分段转小写后以键值形式传入下一螺栓（处理环节）
	 */
	public void execute(Tuple input, BasicOutputCollector collector) {
        String sentence = input.getString(0);
        String[] words = sentence.split(" ");
        for(String word : words){
            word = word.trim();
            if(!word.isEmpty()){
                word = word.toLowerCase();
                collector.emit(new Values(word));
            }
        }
	}

	/**
	 * 同 WordReader 中用法，定义分组 ID，适用于字段分组模式
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
}
