package com.cmnit.gatherdata.modules.bean

case class TollLoadGantryBillInfo(
                                   auth: String,
                                   tradeId: String,
                                   gantryId: String,
                                   gantryType: String,
                                   originalFlag: String,
                                   computerOrder: String,
                                   hourBatchNo: String,
                                   gantryOrderNum: String,
                                   gantryHex: String,
                                   gantryHexOpposite: String,
                                   transTime: String,
                                   payFee: String,
                                   fee: String,
                                   discountFee: String,
                                   transFee: String,
                                   mediaType: String,
                                   obuSign: String,
                                   tollIntervalId: String,
                                   tollIntervalSign: String,
                                   payFeeGroup: String,
                                   feeGroup: String,
                                   discountFeeGroup: String,
                                   enWeight: String,
                                   enAxleCount: String,
                                   vlp: String,
                                   vlpc: String,
                                   vehicleType: String,
                                   identifyVehicleType: String,
                                   vehicleClass: String,
                                   TAC: String,
                                   transType: String,
                                   terminalNo: String,
                                   terminalTransNo: String,
                                   transNo: String,
                                   serviceType: String,
                                   algorithmIdentifier: String,
                                   keyVersion: String,
                                   antennaID: String,
                                   tollModeVer: String,
                                   tollParaVer: String,
                                   rateCompute: String,
                                   rateFitCount: String,
                                   consumeTime: String,
                                   passState: String,
                                   enTollLaneId: String,
                                   enTollStationHex: String,
                                   enTime: String,
                                   enLaneType: String,
                                   passId: String,
                                   lastGantryHex: String,
                                   lastGantryTime: String,
                                   OBUMAC: String,
                                   OBUIssueID: String,
                                   OBUSN: String,
                                   OBUVersion: String,
                                   OBUStartDate: String,
                                   OBUEndDate: String,
                                   OBUElectrical: String,
                                   OBUState: String,
                                   OBUVlp: String,
                                   OBUVlpc: String,
                                   OBUVehicleType: String,
                                   vehicleUserType: String,
                                   vehicleSeat: String,
                                   axleCount: String,
                                   totalWeight: String,
                                   vehicleLength: String,
                                   vehicleWidth: String,
                                   vehicleHight: String,
                                   CPUNetID: String,
                                   CPUIssueID: String,
                                   CPUVlp: String,
                                   CPUVlpc: String,
                                   CPUVehicleType: String,
                                   CPUStartDate: String,
                                   CPUEndDate: String,
                                   CPUVersion: String,
                                   CPUCardType: String,
                                   CPUCardId: String,
                                   balanceBefore: String,
                                   balanceAfter: String,
                                   gantryPassCount: String,
                                   gantryPassInfo: String,
                                   feeProvInfo: String,
                                   feeSumLocalBefore: String,
                                   feeSumLocalAfter: String,
                                   feeCalcResult: String,
                                   feeInfo1: String,
                                   feeInfo2: String,
                                   feeInfo3: String,
                                   OBUpayFeeSumBefore: String,
                                   OBUpayFeeSumAfter: String,
                                   OBUdiscountFeeSumBefore: String,
                                   OBUdiscountFeeSumAfter: String,
                                   OBUProvfeeSumBefore: String,
                                   OBUProvfeeSumAfter: String,
                                   cardfeeSumBefore: String,
                                   cardfeeSumAfter: String,
                                   noCardTimesBefore: String,
                                   noCardTimesAfter: String,
                                   provinceNumBefore: String,
                                   provinceNumAfter: String,
                                   OBUTotalTradeSuccNumBefore: String,
                                   OBUTotalTradeSuccNumAfter: String,
                                   OBUProvTradeSuccNumBefore: String,
                                   OBUProvTradeSuccNumAfter: String,
                                   OBUTradeResult: String,
                                   OBUVerifyCode: String,
                                   tradeType: String,
                                   OBUInfoTypeRead: String,
                                   OBUInfoTypeWrite: String,
                                   OBUPassState: String,
                                   feeVehicleType: String,
                                   OBULastGantryHex: String,
                                   OBULastGantryTime: String,
                                   feeMileage: String,
                                   OBUMileageBefore: String,
                                   OBUMileageAfter: String,
                                   tradeReadCiphertext: String,
                                   readCiphertextVerify: String,
                                   tradeWriteCiphertext: String,
                                   holidayState: String,
                                   tradeResult: String,
                                   provMinFee: String,
                                   provMinFeeCalcMode: String,
                                   feeSpare1: String,
                                   feeSpare2: String,
                                   feeSpare3: String,
                                   feeProvBeginHex: String,
                                   OBUFeeSumBefore: String,
                                   OBUFeeSumAfter: String,
                                   OBUProvPayFeeSumBefore: String,
                                   OBUProvPayFeeSumAfter: String,
                                   pathFitFlag: String,
                                   feeCalcSpecials: String,
                                   payFeeProvSumLocal: String,
                                   PCRSUVersion: String,
                                   gantryPassInfoAfter: String,
                                   updateResult: String,
                                   CPCFeeTradeResult: String,
                                   feeProvEF04: String,
                                   fitProvFlag: String,
                                   gantryPassCountBefore: String,
                                   feeProvBeginHexFit: String,
                                   feeProvBeginTimeFit: String,
                                   feeProvBeginTime: String,
                                   feeSumLocalAfterEF04: String,
                                   lastGantryFeePass: String,
                                   lastGantryMilePass: String,
                                   specialType: String,
                                   verifyCode: String,
                                   interruptSignal: String,
                                   vehiclePicId: String,
                                   vehicleTailPicId: String,
                                   matchStatus: String,
                                   validStatus: String,
                                   dealStatus: String,
                                   relatedTradeId: String,
                                   allRelatedTradeId: String,
                                   stationDBTime: String,
                                   stationDealTime: String,
                                   stationValidTime: String,
                                   stationMatchTime: String,
                                   description: String,
                                   vehicleSign: String,
                                   lastGantryHexFee: String,
                                   lastGantryHexPass: String,
                                   feeCalcSpecial: String,
                                   chargesSpecialType: String,
                                   isFixData: String,
                                   discountType: String,
                                   provinceDiscountFee: String,
                                   originFee: String,
                                   identifyVehicleId: String,
                                   vehiclePlate: String,
                                   CPUVehiclePlate: String,
                                   OBUVehiclePlate: String,
                                   rateVersion: String,
                                   receivetime: String,
                                   branchAgency: String,
                                   var year: String,
                                   var month: String,
                                   var day: String,
                                   var hour: String
                                 ) {}
