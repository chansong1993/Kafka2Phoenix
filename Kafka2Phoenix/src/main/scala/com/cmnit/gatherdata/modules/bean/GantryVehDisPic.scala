package com.cmnit.gatherdata.modules.bean

case class GantryVehDisPic(
                            id: String,
                            pictime: String,
                            gantryid: String,
                            vehicleplate: String,
                            imageurl: String,
                            licenseimageurl: String,
                            binimageurl: String,
                            interfacemark: String,
                            tailimageurl: String,
                            receivetime: String,
                            var year: String,
                            var month: String,
                            var day: String,
                            var hour: String
                          ) {}
