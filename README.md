# thxy-net-api

这是一个基于OkHttp3开发的thxy校园网接口。内含各平台的验证码OCR代码，不支持Android

## 下载

[![Github Releases (by Release)](https://img.shields.io/github/downloads/atom/atom/v1.0.0/total.svg)](https://coding.net/api/user/Lyceum/project/thxy-net-api/git/releases/attachments/download/10733)

## CHANGELOG

### v 1.0.2

- 增加后台工具类

### v 1.0.1

- 修复cookieStore共用导致空指针的bug

## 依赖

```Xml
<dependencies>
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.8.3</version>
    </dependency>
    <dependency>
        <groupId>com.squareup.okhttp3</groupId>
        <artifactId>okhttp</artifactId>
        <version>3.10.0</version>
    </dependency>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.2</version>
    </dependency>
</dependencies>
```

## 功能

### 1.教务系统 (http://jwgl.thxy.cn/)

- 查成绩
- 查课程安排
- 查学分和平均绩点
- 查课程表
- 查考试安排
- 查个人信息
- 获取个人照片
- 抢课（暂未实现）
- 评教（暂未实现）

### 2.自助平台 (http://self.thxy.cn:8080/)

- 查询余额
- 查在线设备Mac地址
- 清空绑定设备
- 清除在线设备
- 查充值记录
- 查计费周期

### 3.iMC Portal (http://10.0.8.9:8080/portal/)

- 上线
- 下线

###  4.故障报修平台  (http://10.0.8.48/hq/baoxiu.php)

- 发报修单

### 5.用电查询平台 (http://10.0.8.50)

- 查用电情况
- 查免费电
- 查缴费记录

## 演示

### 1.教务系统查成绩

```java
Jwgl jwgl = new Jwgl();
String reslut = jwgl.login("学号", "密码");
if ("登陆成功".equals(reslut)) {
    jwgl.getExamResults("201702").forEach(System.out::println);
} else {
    System.err.println(reslut);
}
```

### 2.自助平台查余额

```java
NetSys netSys = new NetSys();
String result = netSys.login("学号", "密码");
if ("success".equals(result)) {
    netSys.getInfo().forEach((k, v) -> System.out.println(v));
} else {
    System.err.println(result);
}
```

## 反馈

可以扫下面的二维码添加公众号进行反馈

![wechat](https://coding.net/u/Lyceum/p/thxy-net-api/git/raw/master/demo/image/wechat.jpg)

## License

```
Copyright 2018 Lyceum Hewun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

