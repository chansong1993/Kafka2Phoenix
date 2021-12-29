package com.cmnit.gatherdata.enums;

/**
 * 原始流水Kafka Topic枚举定义
 * Created by Hehj
 */
public enum KafkaTopicEnum {

    // ETC收费门架流水数据（门架交易数据）
    GBUPLOAD_ETCTU_TOPIC("GBUPLOAD_ETCTU_TOPIC", "ETC收费门架流水数据"),

    // 门架心跳流水（门架运行状态监测数据）
    RM_TGHBU_TOPIC("RM_TGHBU_TOPIC", "门架心跳流水"),

    // ETC门架牌识流水（门架牌识数据）
    GBUPLOAD_VIU_TOPIC("GBUPLOAD_VIU_TOPIC", "ETC门架牌识流水"),

    // 车道车牌识别流水
    TRC_VIU_TOPIC("TRC_VIU_TOPIC", "车道车牌识别流水"),

    // 出口车道流水
    TOLL_ORI_OUT_TRADE_TOPIC("TOLL_ORI_OUT_TRADE_TOPIC", "出口车道流水"),

    // 入口车道流水（车道入口车道数据）
    TRC_ENPU_TOPIC("TRC_ENPU_TOPIC", "入口车道流水"),

    // 车道心跳流水
    RM_LHBU_TOPIC("RM_LHBU_TOPIC", "车道心跳流水"),

    // 门架基础信息数据
    RM_BASEINFOUPLOAD_TOPIC("RM_BASEINFOUPLOAD_TOPIC", "门架基础信息数据"),

    // 门架交易小时批次汇总
    GBUPLOAD_ETCSU_TOPIC("GBUPLOAD_ETCSU_TOPIC", "门架交易小时批次汇总"),

    // 门架牌识小时批次汇总
    GBUPLOAD_VISU_TOPIC("GBUPLOAD_VISU_TOPIC", "门架牌识小时批次汇总"),

    // 门架识别图片
    GBUPLOAD_VIPU_TOPIC("GBUPLOAD_VIPU_TOPIC", "门架识别图片"),

    // 门架下载记录
    GANTY_DOWNLOAD_RECORD("GANTY_DOWNLOAD_RECORD", "门架下载记录"),

    // 车道操作日志
    TRC_LOPLU_TOPIC("TRC_LOPLU_TOPIC", "车道操作日志"),

    // 车道车牌识别图片数据
    TRC_SVIPU_TOPIC("TRC_SVIPU_TOPIC", "车道车牌识别图片数据"),

    // 车道车牌识别小时批次汇总
    TRC_TOLLDISHSU("TRC_TOLLDISHSU", "车道车牌识别小时批次汇总"),

    // 车道承载门架交易数据
    TRC_ETCTU_TOPIC("TRC_ETCTU_TOPIC", "车道承载门架交易数据"),

    // 车道承载门架小时批次汇总
    TRC_ETCSU_TOPIC("TRC_ETCSU_TOPIC", "车道承载门架小时批次汇总"),

    // 车道实时过车监测数据
    RM_RRU_TOPIC("RM_RRU_TOPIC", "车道实时过车监测数据"),

    // 收费员出口小班合计汇总数据
    TRC_EXLSU_TOPIC("TRC_EXLSU_TOPIC", "收费员出口小班合计汇总数据"),

    // 收费员入口小班汇总数据
    TRC_ENLSU_TOPIC("TRC_ENLSU_TOPIC", "收费员入口小班汇总数据");

    KafkaTopicEnum(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }

    //通过topic获取kafka枚举类
    public static KafkaTopicEnum getByTopic(String topic) {
        for (KafkaTopicEnum item : KafkaTopicEnum.values()) {
            if (topic.equals(item.getTopic())) {
                return item;
            }
        }
        return null;
    }


    /**
     * Kafka topic
     */
    private String topic;

    /**
     * 描述
     */
    private String desc;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
