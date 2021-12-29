package com.cmnit.gatherdata.modules.bean

case class GantryVehDisHourSum(
                                collectId: String,
                                gantryId: String,
                                computerOrder: String,
                                collectDate: String,
                                collectHourBatch: String,
                                vehicleDataCount: String,
                                vehiclePicCount: String,
                                receivetime: String,
                                var year: String,
                                var month: String,
                                var day: String
                              ) {}
