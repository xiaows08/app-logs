## 模块分布
1. app-logs-web (web | springBoot) [主业务]
    - 用户访问入口
    - 前端界面设置埋点,收集用户数据
    - 用户行为日志收集
        1. 通过 flume 采集 nginx 访问日志,发送至 kafka | hdfs
        2. 或者直接发送至 nginx-kafka

2. app-logs-phone (test) [模拟客户端生成日志]
    - 通过 random 产生数据
    - 模拟手机端发送日志
    - 日志类型共分为5种 [startup, error, event, usage, page]
    - 日志发送到 app-logs-collect tomcat服务器上

3. app-logs-collect (web | springBoot) [接收应用日志到 kafka 集群]
    - 集成 kafka 生产者, 向 broker-list 发送数据
    - 注: <u>该模块可被 nginx-kafka 替代</u>
    
4. app-logs-flume
    - 自定义拦截器
    - 功能:
        - 增加 topicType 头
        - 增加数据收集时间头

5. app-logs-hive
    - 自定义函数 UDF
    
    hive spark
    mysql hbase

6. app-dubbo-api
    - 声明service接口

7. app-dubbo-service [微服务-提供分析后的数据]
    - 实现 service 接口
    - 读取数据库,查询数据
    - jdbc:hive2://hive-1:10000/applogdb
    - jdbc:hive2://spark-1:10000/applogdb

8. app-dubbo-web (web | springBoot) [微服务-分析后的数据可视化]
    - 调用 service 接口
    - 可视化 web 程序

## 项目流程
zookeeper -> kafka -> flume -> hdfs -> hive -> spark

---
### Zookeeper v3.4.10
- 编写集群启动脚本 [$ZOOKEEPER_HOME/cluster-zk.sh]
```bash
#!/bin/bash
if [ $# = 0 ]; then
    echo "Please input commod of zkServer {start|start-foreground|stop|restart|status|upgrade|print-cmd}"
    exit 1
fi
for i in 1 2 3; do
    ssh zk-$i "echo  $i > /data/zkData/myid;$ZOOKEEPER_HOME/bin/zkServer.sh $1"
done
#tailf -500 zookeeper.out

```
- 启动 zookeeper集群
```bash
$ZOOKEEPER_HOME/cluster-zk.sh start
```

---
### Kafka v2.11-1.0
- 启动kafka集群脚本[$KAFKA_HOME/cluster-kafka.sh]
```bash
#!/bin/bash
if [ $# = 0 ]; then
    echo "Please input commod of kafka {start|stop}"
    exit 1
fi

for i in 1 2 3; do
    ssh kafka-$i sed -i "s/broker.id=[0-9]*/broker.id=$i/g" $KAFKA_HOME/config/server.properties;
    ssh kafka-$i $KAFKA_HOME/bin/kafka-server-$1.sh -daemon $KAFKA_HOME/config/server.properties
done

echo "$KAFKA_HOME/bin/kafka-topics.sh --zookeeper zk-1:2181,zk-2:2181,zk-3:2181 --list"
echo "$KAFKA_HOME/bin/kafka-topics.sh --zookeeper zk-1:2181,zk-2:2181,zk-3:2181 --create --replication-factor 2 --partitions 3 --topic mytopic"
echo "$KAFKA_HOME/bin/kafka-console-producer.sh --broker-list kafka-1:9092 --topic mytopic"
echo "$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server kafka-1:9092 --topic mytopic"

```
- 启动kafka集群
```bash
$KAFKA_HOME/cluster-kafka.sh start
```

- kafka常用命令
```bash
# 查看topic
/usr/local/kafka_2.11-2.0.0/bin/kafka-topics.sh --zookeeper zk-1:2181,zk-2:2181,zk-3:2181 --list

# 创建 topic
/usr/local/kafka_2.11-2.0.0/bin/kafka-topics.sh --zookeeper zk-1:2181,zk-2:2181,zk-3:2181 --create --replication-factor 2 --partitions 3 --topic mytopic

# 启动控制台生产者
/usr/local/kafka_2.11-2.0.0/bin/kafka-console-producer.sh --broker-list kafka-1:9092 --topic mytopic
#>
# 启动控制台消费者 (--from-beginning 从头开始消费)
/usr/local/kafka_2.11-2.0.0/bin/kafka-console-consumer.sh --bootstrap-server kafka-1:9092 --topic mytopic --from-beginning

```

---
### Hadoop v2.8.3
- 启动 hdfs
```bash
start-dfs.sh
```

---
### Flume v1.8
- 配置 $FLUME_HOME/conf/kafka-c_hdfs-k.conf
```yaml
# kafka Channel + HDFS sink(without sources)
a1.channels = c1
a1.sinks = k1

# 定义 KafkaChannel
a1.channels.c1.type = org.apache.flume.channel.kafka.KafkaChannel
a1.channels.c1.parseAsFlumeEvent = false
a1.channels.c1.kafka.bootstrap.servers = kafka-1:9092,kafka-2:9092,kafka-3:9092
a1.channels.c1.kafka.topic = app-log
a1.channels.c1.kafka.consumer.group.id = g1

# 定义 HDFS sink
a1.sinks.k1.channel = c1
a1.sinks.k1.type = hdfs
a1.sinks.k1.hdfs.path = hdfs://hadoop-1:9000/flume/app-log/%Y/%m/%d/%H/%M
a1.sinks.k1.hdfs.useLocalTimeStamp = true
a1.sinks.k1.hdfs.filePrefix = log
a1.sinks.k1.hdfs.fileType = DataStream
# 不按照条数生成文件
a1.sinks.k1.hdfs.rollCount = 100
# HDFS 上的文件达到128M 生成一个文件
a1.sinks.k1.hdfs.rollSize = 134217728
# HDFS 上的文件达到10分钟生成一个文件
a1.sinks.k1.hdfs.rollInterval = 600

```

- 添加 HDFS 相关依赖
```bash
ssh hadoop-1
cd /usr/local/hadoop-2.8.3/share/hadoop/

#find . -name commons-configuration-1.6.jar
#find . -name commons-io-2.4.jar
#find . -name hadoop-auth-2.8.3.jar
#find . -name hadoop-common-2.8.3.jar
#find . -name hadoop-hdfs-2.8.3.jar
#find . -name hadoop-hdfs-client-2.8.3.jar
#find . -name htrace-core4-4.0.1-incubating.jar

cp common/lib/commons-configuration-1.6.jar flume-libs/
cp common/lib/commons-io-2.4.jar flume-libs/
cp common/lib/hadoop-auth-2.8.3.jar flume-libs/
cp common/hadoop-common-2.8.3.jar flume-libs/
cp hdfs/hadoop-hdfs-2.8.3.jar flume-libs/
cp hdfs/hadoop-hdfs-client-2.8.3.jar flume-libs/
cp common/lib/htrace-core4-4.0.1-incubating.jar flume-libs/

scp -r flume-libs/* flume-1:/usr/local/flume-1.8.0-bin/lib/
```

- 启动 flume-ng
```bash
/usr/local/flume-1.8.0-bin/bin/flume-ng agent -c conf/ -f conf/kafka-c_hdfs-k.conf -n a1 -Dflume.root.logger=INFO,console
```

---
### Hive v1.2 | v2.3
- 配置 $HIVE_HOME/conf/hive-site.xml
```xml
<configuration>
  <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://awx.local:3306/hivedb?createDatabaseIfNotExist=true</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>root</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>123456</value>
  </property>
</configuration>

```
- 添加mysql连接依赖
```bash
cp mysql-connector-java-5.1.40.jar $HIVE_HOME/lib/
```
- 添加 JSON 解析依赖
```bash
cp hcatalog/share/hcatalog/hive-hcatalog-core-1.2.2.jar lib/
```
- 启动hiveserver2后台服务
```bash
nohup ./bin/hiveserver2 1 >> hiveserver2.log 2>&1 &
```
- 启动 Hive 客户端 beeline
```bash
./bin/beeline -u jdbc:hive2://localhost:10000 -n root
```
- 在 Hive 客户端 beeline 中创建外部分区表
```sql
create external table ext_app_log(
  appChannel string,
  appPlatFrom string,
  appVersion string,
  brand string,
  carrier string,
  country string,
  currentDate string,
  deviceStyle string,
  eventID string,
  network string,
  osType string,
  province string,
  remoteAddr string,
  screenSize string,
  tenantId string
) partitioned by (ym string, day string, hm string)
 row format serde 'org.apache.hive.hcatalog.data.JsonSerDe' stored as textfile;

```
- 从 HDFS 中加载数据
```sql
load data inpath '/flume/app-log/2018/12/28/15/06' into table ext_app_log partition(ym=201812, day=28, hm=1506);
#load data inpath '/flume/app-log/${ym}/${day}/${hm}' into table t_access partition(dt=20170806);
```
- 编写 `/root/crontab/.load_data.hql` (隐藏文件)
```sql
load data inpath '/flume/app-log/${YEAR}/${MONTH}/${DAY}/${HM}' into table app_logs.ext_app_log partition(ym=${YM}, day=${DAY}, hm=${HM});

```
- 编写 crontab 执行的 shell 脚本 `/root/crontab/load_data.sh`
```bash
#!/bin/bash

systime=`date "+%Y-%m-%d-%H%M"`
year=`echo ${systime} | awk -F '-' '{print $1}'`
month=`echo ${systime} | awk -F '-' '{print $2}'`
ym=`echo ${systime} | awk -F '-' '{print $1$2}'`
day=`echo ${systime} | awk -F '-' '{print $3}'`
hm=`echo ${systime} | awk -F '-' '{print $4}'`

cp /root/crontab/.load_data.hql /root/crontab/load_data.hql

sed -i 's/${YEAR}/'${year}'/g' /root/crontab/load_data.hql
sed -i 's/${MONTH}/'${month}'/g' /root/crontab/load_data.hql
sed -i 's/${YM}/'${ym}'/g' /root/crontab/load_data.hql
sed -i 's/${DAY}/'${day}'/g' /root/crontab/load_data.hql
sed -i 's/${HM}/'${hm}'/g' /root/crontab/load_data.hql
echo '===========> load_data.sql creation has completed!'

#执行 hive 的命令
echo '===========> execute load_data.sql'
/usr/local/hive-1.2.2-bin/bin/hive -f /root/crontab/load_data.hql
#rm /root/crontab/load_data.sql
echo '===========> load_data.sql executed done!!!'

#sqoop export
#echo '===========> execute sqoop job ...'
#sqoop job --exec myjob
#echo '===========> execute sqoop job over!!!'

```
- 配置 crontab 表达式
`crontab -e`
```bash
# m h dom mon dow user	command
* * * * * root /root/crontab/load_data.sh >> /root/crontab/load_data.log

```
- 启动 crontab 服务
```bash
service crond start
service crond status
```
注: docker 容器中 crontab 启动不起来...(上次不知道怎么又好了O_O#)

- 上传 app-logs-hive-1.0.0-SNAPSHOT.jar包到 HDFS 中
```bash
hsdf dfs -put app-logs-hive-1.0.0-SNAPSHOT.jar hdfs://hadoop-1:9000/user/root/
```
- 在 beeline 中向 hive 注册 UDF 函数
```sql
create function formattime as 'cn.xiaows.hive.udf.FormatTimeUDF' using jar 'hdfs://hadoop-1:9000/user/root/app-logs-hive-1.0.0-SNAPSHOT.jar'
create function getdaybegin as 'cn.xiaows.hive.udf.DayBeginUDF' using jar 'hdfs://hadoop-1:9000/user/root/app-logs-hive-1.0.0-SNAPSHOT.jar'
create function getweekbegin as 'cn.xiaows.hive.udf.WeekBeginUDF' using jar 'hdfs://hadoop-1:9000/user/root/app-logs-hive-1.0.0-SNAPSHOT.jar'
create function getmonthbegin as 'cn.xiaows.hive.udf.MonthBeginUDF' using jar 'hdfs://hadoop-1:9000/user/root/app-logs-hive-1.0.0-SNAPSHOT.jar'
```

- 检测 Hive 元数据库
```sql
mysql> select * from funcs;
mysql> select * from func_ru;
-- 测试 UDF
hive> select formattime();
hive> select getdaybegin();
hive> select getweekbegin(-1);
hive> select getmonthbegin();
hive> select formattime(getmonthbegin(1));
```

---
### Spark v2.2.0
- 启动thriftserver
```bash
$SPARK_HOME/sbin/start-thriftserver.sh --master spark://spark-1:7077
netstat -nultp | grep 10000

./bin/beeline -u jdbc:hive2://localhost:10000/
```


---
- 同步时间
```bash
echo "Asia/Shanghai" > /etc/timezone
dpkg-reconfigure -f nonineteractive tzdata
```


pv:
select page, count(*) from logs where year=2019 and month=01 and day=01 group by page;