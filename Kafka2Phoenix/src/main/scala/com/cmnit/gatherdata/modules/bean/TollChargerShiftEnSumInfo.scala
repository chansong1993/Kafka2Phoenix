package com.cmnit.gatherdata.modules.bean

case class TollChargerShiftEnSumInfo(
                                      auth: String,
                                      batchNum: String,
                                      station: String,
                                      lane: String,
                                      stationId: String,
                                      laneId: String,
                                      workDate: String,
                                      shift: String,
                                      tollCollectorID: String,
                                      tollCollectorName: String,
                                      laneType: String,
                                      bl_SubCenter: String,
                                      bl_Center: String,
                                      startTime: String,
                                      endTime: String,
                                      invStartNo: String,
                                      statusFlag: String,
                                      invNum: String,
                                      badInvNum: String,
                                      vCnt: String,
                                      inductCnt: String,
                                      CPCCnt: String,
                                      papCnt: String,
                                      damCardCnt: String,
                                      ETCCardCnt: String,
                                      transCnt: String,
                                      transFeeSum: String,
                                      unsendcardcnt: String,
                                      invEndNo: String,
                                      receivetime: String,
                                      branchAgency: String,
                                      var year: String,
                                      var month: String,
                                      var day: String
                                    ) {}