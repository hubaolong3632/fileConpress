# fileConpress

Java 图片压缩实验项目，含 Thumbnailator 压缩工具与 QQ Bot WebSocket 机器人代码（Maven 模块名 QQ_BOT2）。

## 项目简介

`fileConpress` 是一个 Java 17 Maven 项目，核心功能分为两部分：

1. **图片压缩**：`ljx.imagezip` 包提供 `ImageCompressUtil`，基于 Thumbnailator 对 PNG/JPG 进行缩放与质量压缩，仓库根目录含 `output.png` 示例输出。
2. **QQ Bot 实验**：`QQBot` 包含 WebSocket 客户端（`WebSocketExample`）、消息模型、服务端与 `utio` 工具，用于对接 QQ 机器人协议（OneBot 风格）。

> 仓库名称为 fileConpress，pom.xml 中 artifactId 为 `QQ_BOT2`，两者为历史命名并存。

## 技术栈

- Java 17
- Thumbnailator 0.4.8（图片压缩）
- Java-WebSocket 1.5.2
- FastJSON、Jsoup、Lombok
- Spotify docker-client（Docker 操作实验）

## 主要功能

- `ImageCompressUtil`：按目标尺寸/质量压缩图片，支持 RGBA 处理
- `QQBot/WebSocketExample`：WebSocket 连接与消息收发示例
- `QQBot/server`、`QQBot/model`：机器人消息处理框架
- `ljx/cs.java`：入口测试类

## 项目结构

```
fileConpress/
├── pom.xml
├── output.png                    # 压缩示例输出
└── src/main/java/
    ├── ljx/
    │   ├── cs.java               # 测试入口
    │   └── imagezip/Text/
    │       ├── ImageCompressUtil.java
    │       └── RGBA.java
    └── QQBot/
        ├── WebSocketExample.java
        ├── server/               # 消息服务
        ├── model/                # 消息模型
        └── utio/                 # 工具类
```

## 快速开始

**环境要求**：JDK 17、Maven 3.x

```bash
git clone https://github.com/hubaolong3632/fileConpress.git
cd fileConpress
mvn clean compile
```

在 IDE 中运行 `ImageCompressUtil` 或 `QQBot.WebSocketExample` 的 main 方法。图片压缩需准备输入文件路径并指定输出路径。

## 相关仓库

- 同类图片处理逻辑也出现在 [daliy_spring_v1](https://github.com/hubaolong3632/daliy_spring_v1) 的用户图库模块
