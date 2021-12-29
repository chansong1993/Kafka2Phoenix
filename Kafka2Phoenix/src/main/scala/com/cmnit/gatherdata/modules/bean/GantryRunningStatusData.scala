package com.cmnit.gatherdata.modules.bean

case class GantryRunningStatusData(
                                    chargeUnitId: String,
                                    heatVersion: String,
                                    gantryHeartbeatList: String,
                                    chargeUnitHeartbeatList: String,
                                    vehicleDetectorHeartbeatList: String,
                                    otherHeartbeatList: String,
                                    cameraHeartbeatList: String,
                                    RSUHeartbeatList: String,
                                    PSAMInfoList: String,
                                    antennalInfoList: String,
                                    receivetime: String,
                                    var year: String,
                                    var month: String,
                                    var day: String,
                                    var hour: String
                                  ) {}
