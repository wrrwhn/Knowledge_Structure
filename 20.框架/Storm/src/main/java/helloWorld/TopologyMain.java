package helloWorld;


import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import helloWorld.bolts.WordCounter;
import helloWorld.bolts.WordNormalizer;
import helloWorld.spouts.WordReader;

public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
         
        // 定义代码的拓扑结构 - word-reader(spout)-> word-normalizer(bolt)-> word-counter(bolt)
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader());
		builder.setBolt("word-normalizer", new WordNormalizer())
			.shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(),1)
			.fieldsGrouping("word-normalizer", new Fields("word"));
		
        // 启动配置 - 定义配置文件地址& 非调试模式& 最大输入流为 1
		Config conf = new Config();
		conf.put("wordsFile", "D:\\WorkSpace\\work4Git\\Knowledge_Structure\\00.语言\\Java\\代码\\java-study\\src\\main\\java\\com\\yao\\utils\\apache\\storm\\helloWorld\\resources\\words.txt");
		conf.setDebug(false);
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);

        // 运行集群
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
		Thread.sleep(10000);
		cluster.shutdown();
	}
}
