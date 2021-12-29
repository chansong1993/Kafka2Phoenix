package com.cmnit.gatherdata.modules.bean

case class TollVehDisPic(
                          auth: String,
                          id: String,
                          picTime: String,
                          gantryId: String,
                          vehiclePlate: String,
                          imageUrl: String,
                          tailImageUrl: String,
                          licenseImageUrl: String,
                          binImageUrl: String,
                          videoImageUrl: String,
                          hourBatchNo: String,
                          interfaceMark: String,
                          receiveTime: String,
                          branchAgency: String,
                          videoImgSuffix: String,
                          bodyImageUrl: String,
                          var year: String,
                          var month: String,
                          var day: String,
                          var hour: String
                        ) {}
