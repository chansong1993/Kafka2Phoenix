package com.cmnit.gatherdata.modules.bean

case class GantryDownloadRecordInfo(
                                     gantryId: String,
                                     downloadTime: String,
                                     downloadType: String,
                                     systemType: String,
                                     version: String,
                                     receivetime: String,
                                     var year: String,
                                     var month: String,
                                     var day: String
                                   ) {}
