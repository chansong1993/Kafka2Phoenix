package com.cmnit.gatherdata.modules.bean

case class GantryBillHourSum(
                              collectId: String,
                              gantryId: String,
                              computerOrder: String,
                              collectDate: String,
                              collectHourBatch: String,
                              batchCount: String,
                              etcTypeCount: String,
                              etcClassCount: String,
                              etcSuccessCount: String,
                              etcSuccessFee: String,
                              etcFailCount: String,
                              cpcTypeCount: String,
                              cpcClassCount: String,
                              cpcSuccessCount: String,
                              cpcSuccessFee: String,
                              cpcFailCount: String,
                              receivetime: String,
                              var year: String,
                              var month: String,
                              var day: String
                            ) {}
