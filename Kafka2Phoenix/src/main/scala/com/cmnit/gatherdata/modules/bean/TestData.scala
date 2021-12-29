package com.cmnit.gatherdata.modules.bean

case class TestData(
                     gantryId: String, //车牌识别流水号（唯一索引），规则：门架编号+牌识编号（101~299）+牌识批次号+批次内流水号（6位，000001~999999）
                     binImageSize: String, //门架编号，规则：参考编码规则，全网唯一编号
                     reliability: String, //ETC门架Hex值，规则：当前门架的hex值
                     gantryHex: String, //抓拍时间，规则：YYYY-MM-DDTHH:mm:ss
                     identifyType: String, //门架顺序号，规则：方向（1上行，2下行）+序号（2位），例如：上行：101，102，103；下行：201，202，203
                     gantryOrderNum: String, //行驶方向，规则：1-上行，2-下行
                     matchStatus: String, //牌识编号，规则：牌识编号（101~299），1位拍摄位置（1-车头，2-车尾）+2位相机序号（01-99）
                     validStatus: String, //小时批次号，规则：按小时自增，不能回退。例如：2019080716
                     cleanDataFlag: String, //拍摄位置，规则：1-车头;2-车尾
                     licenseImageSize: String, //物理车道编码，规则：由行驶方向内侧向外顺序递增，跨多车道时采用组合编号，如：1、2、3、123等
                     vehicleModel: String, //识别车牌，规则：车牌号码+间隔符+车牌颜色，间隔符：“_”车牌颜色2位数字:0-蓝色，1-黄色，2-黑色，3-白色，4-渐变绿色5-黄绿双拼色6-蓝白渐变色9-未确定11-绿色12-红色例：京A12345_1，若无法识别车牌，可填写为默A00000_9
                     imageSize: String, //车辆速度，规则：单位：千米/小时
                     laneNum :String,
                     picId :String,
                     shootPosition :String,
                     vehicleColor :String,
                     stationDBTime :String,
                     vehicleSpeed :String,
                     stationValidTime :String,
                     verifyCode :String,
                     stationMatchTime :String,
                     vehiclePlate :String,
                     hourBatchNo :String,
                     dealStatus :String,
                     stationDealTime :String,
                     receiveTime :String,
                     driveDir :String,
                     picTime :String,
                     cameraNum :String,
                     var year :String,
                     var month :String,
                     var day :String,
                     var hour :String) {

  }