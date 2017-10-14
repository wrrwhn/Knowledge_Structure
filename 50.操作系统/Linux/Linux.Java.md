

[TOC]

## 参考

- [Java SE](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [CentOS 7 下安装jdk1.8](https://blog.argcv.com/articles/3155.c)
- [Download Oracle Java JRE & JDK using a script](https://ivan-site.com/2012/05/download-oracle-java-jre-jdk-using-a-script/)


## 安装
- download
	- `wget http://download.oracle.com/otn-pub/java/jdk/8u144-b01/090f390dda5b47b9b721c7dfaa008135/jdk-8u144-linux-x64.rpm?AuthParam=1502793850_1a99b9b8c46b13fbf1ea41f955c5bd54`
- install
	- `yum localinstall jdk-8u144-linux-x64.rpm -y`
- check
	- `java -version`
	- `find / -name java`
		- /usr/java/jdk1.8.0_144

## 配置
- `find / -name java`
	- /usr/java/jdk1.8.0_144
- export
	```
	export JAVA_HOME="/usr/java/jdk1.8.0_144"
	export PATH=$JAVA_HOME/bin:$PATH
	```