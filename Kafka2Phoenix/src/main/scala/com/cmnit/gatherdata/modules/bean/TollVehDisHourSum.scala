package com.cmnit.gatherdata.modules.bean

case class TollVehDisHourSum(
                              auth: String,
                              id: String,
                              stationProId: String,
                              stationId: String,
                              stationName: String,
                              laneNum: String,
                              hourBatchNo: String,
                              collectDate: String,
                              vehicleDataCount: String,
                              vehiclePicCount: String,
                              receivetime: String,
                              branchAgency: String,
                              var year: String,
                              var month: String,
                              var day: String
                            ) {}
