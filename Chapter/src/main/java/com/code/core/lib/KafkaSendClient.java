package com.code.core.lib;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaSendClient {

    private final KafkaProducer<String, String> producer;

    public String getKafkaServer() {
        return kafkaServer;
    }

    public void setKafkaServer(String kafkaServer) {
        this.kafkaServer = kafkaServer;
    }

    public String getKafkaTopic() {
        return kafkaTopic;
    }

    public void setKafkaTopic(String kafkaTopic) {
        this.kafkaTopic = kafkaTopic;
    }

    //xxx服务器ip "xxx:9092,1xxx:9092,xxx:9092"
    private String kafkaServer;
    private String kafkaTopic;



    public KafkaSendClient(String kafkaServer, String kafkaTopic) {
        this.kafkaServer = kafkaServer;
        this.kafkaTopic = kafkaTopic;

        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServer);//xxx服务器ip
        /** request.required.acks default: 1
         * 0，producer永远不会等待一个来自broker的ack,当server挂掉的时候会丢失一些数据。
         * 1，在leader replica接收数据后，producer得到一个ack,持久性和写入效率居中。
         * all，所有ISR都接收数据后，producer才得到一个ack。数据持久性最好,但是影响写入效率。
         *注意：数据丢失率：all>1>0 性能：all<1<0,请看性能测试报告：Kafka 性能测试报告
         **/
        props.put("acks", "all");//所有follower都响应了才认为消息提交成功，即"committed"
        props.put("retries", 3);//retries = MAX 无限重试，直到你意识到出现了问题:)
        /**
         * The number of messages to send in one batch when using async mode.
         * The producer will wait until either this number of messages are ready to send or queue.buffer.max.ms is reached.
         * batch.size default 16384 queue.buffer.max.ms default 5s
         */
        props.put("batch.size", 16384);//producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        //batch.size当批量的数据大小达到设定值后，就会立即发送，不顾下面的linger.ms
        props.put("linger.ms", 1);//延迟1ms发送，这项设置将通过增加小的延迟来完成--即，不是立即发送一条记录，producer将会等待给定的延迟时间以允许其他消息记录发送，这些消息记录可以批量处理
        props.put("buffer.memory", 33554432);//producer可以用来缓存数据的内存大小。
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(props);
    }

    public void produce(String data) {


        try {
            producer.send(new ProducerRecord<String, String>(kafkaTopic, data));
            System.out.println("发送成功！");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("--------------");
            System.out.println(e.getMessage());
        }
        producer.close();
    }

    public static void main(String[] args) {
//        new KafkaSendClient().produce();
    }
}
