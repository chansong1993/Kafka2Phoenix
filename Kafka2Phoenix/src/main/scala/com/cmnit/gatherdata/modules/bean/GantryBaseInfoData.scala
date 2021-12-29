package com.cmnit.gatherdata.modules.bean

case class GantryBaseInfoData(
                               chargeUnitId: String,
                               infoVersion: String,
                               gantryInfoList: String,
                               chargeUnitInfoList: String,
                               cameraInfoList: String,
                               rsuInfoList: String,
                               vehicleDetectorInfoList: String,
                               otherInfoList: String,
                               receivetime: String,
                               var year: String,
                               var month: String,
                               var day: String,
                               var hour: String
                             ) {}
