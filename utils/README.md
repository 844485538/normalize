## MagicPackageUtils

Wake-On-LAN简称WOL，是一种电源管理功能；它是由IBM公司提出的网络唤醒标准，目前该标准已被大多数主板厂商支持。支持该标准的主板允许从远程通过网络唤醒计算机，也就是远程开机。

通过向局域网发送指定报文包来实现设备的开机。

## StringUtils

|方法|描述|
|--|--|
|static boolean isEmpty|判定字符串是否为空|
|static boolean isNotEmpty|判定字符串是否非空|
|static boolean isBlank|判定字符串是否为空白（多个空格也算空白）|
|static boolean isNotBlank|判定字符串是否非空白（多个空格也算空白）|

## TimeUtils

|方法|描述|
|--|--|
|static LocalDateTime now()|获取当前时间|
|static String LocalDateTimeToStr|LocalDateTimeToStr 转 String|
|static LocalDateTime StrToLocalDateTime|String 转 LocalDateTimeToStr|


## UUIDUtils

|方法|描述|
|--|--|
|static String getUuid()|获取uuid|