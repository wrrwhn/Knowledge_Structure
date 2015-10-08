package com.yao.utils.apache.storm.topologies.countword;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import com.yao.utils.apache.storm.topologies.countword.bolts.WordCounter;
import com.yao.utils.apache.storm.topologies.countword.bolts.WordNormalizer;
import com.yao.utils.apache.storm.topologies.countword.spouts.SignalsSpout;
import com.yao.utils.apache.storm.topologies.countword.spouts.WordReader;

public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
        
        // 拓扑结构定义
			// word-reader(spout) -> word-normalizer(bolt) -> word-counter(bolt)
			// signals(spout) --------------------------------^
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader());
		builder.setSpout("signals-spout",new SignalsSpout());
		builder.setBolt("word-normalizer", new WordNormalizer())
			.shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(), 2)
			.fieldsGrouping("word-normalizer",new Fields("word"))
			.allGrouping("signals-spout","signals");

        // 集群配置 - 设置输入地址、调试模式且 spout 最大处理元组数量为 1
		Config conf = new Config();
		conf.put("wordsFile", "D:\\WorkSpace\\work4Git\\Knowledge_Structure\\00.语言\\Java\\代码\\java-study\\src\\main\\java\\com\\yao\\utils\\apache\\storm\\topologies\\resources\\words.txt");
		conf.setDebug(true);
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);

        // 运行集群并于 5 秒后停止
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Count-Word-Toplogy-With-Refresh-Cache", conf, builder.createTopology());
		Thread.sleep(5000);
		cluster.shutdown();
	}
}
