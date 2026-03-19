# 冒险岛 - 安卓游戏

经典 FC 冒险岛玩法的安卓重制版

## 项目结构

```
adventure-island/
├── core/              # 游戏核心代码 (跨平台)
│   └── src/main/kotlin/com/adventureisland/game/
│       ├── AdventureIslandGame.kt    # 游戏入口
│       ├── core/                     # 游戏引擎
│       ├── entities/                 # 游戏实体
│       ├── screens/                  # 游戏界面
│       ├── input/                    # 输入处理
│       ├── levels/                   # 关卡设计
│       └── utils/                    # 工具类
├── android/           # 安卓平台实现
│   └── src/main/
│       ├── kotlin/.../MainActivity.kt
│       ├── res/                      # 资源文件
│       └── AndroidManifest.xml
└── gradle/           # 构建配置
```

## 游戏特性 (V1.0)

### 已完成
- ✅ 玩家移动 (左右行走、跳跃)
- ✅ 爬梯子
- ✅ 投掷石头攻击
- ✅ 敌人 AI (巡逻)
- ✅ 踩踏敌人
- ✅ 击晕敌人后可踢走
- ✅ 平台碰撞检测
- ✅ 基础关卡设计
- ✅ 虚拟触摸按键
- ✅ 键盘支持 (调试用)

### 待实现 (V1.1+)
- ⬜ 更多敌人类型 (飞行、BOSS)
- ⬜ 道具系统 (心形、速度鞋、武器升级)
- ⬜ 多个关卡
- ⬜ 音效和音乐
- ⬜ 动画系统
- ⬜ UI 界面 (主菜单、暂停)
- ⬜ 关卡编辑器

## 操作说明

### 触摸控制
- 左下角：方向键 (左/右)
- 右下角：跳跃键、攻击键

### 键盘控制 (调试)
- 方向键/WASD：移动
- 空格/W/上：跳跃
- Z/K：攻击

## 构建说明

### 前置要求
- Android Studio Arctic Fox 或更高版本
- JDK 17
- Android SDK 34

### 构建步骤

1. 打开 Android Studio
2. File → Open → 选择 `adventure-island` 文件夹
3. 等待 Gradle 同步完成
4. 连接安卓设备或启动模拟器
5. 点击 Run (绿色三角形)

### 命令行构建
```bash
cd adventure-island
./gradlew assembleDebug
```

APK 输出位置：`android/build/outputs/apk/debug/`

## 技术栈

- **语言**: Kotlin
- **游戏框架**: LibGDX 1.12.1
- **最低 SDK**: Android 5.0 (API 21)
- **目标 SDK**: Android 14 (API 34)

## 架构设计

采用 ECS (Entity-Component-System) 简化版架构：

- **Entity**: 游戏实体基类 (Player, Enemy, Platform, Item)
- **System**: 游戏系统 (Collision, EntityManager, GameLoop)
- **Screen**: 游戏界面管理 (GameScreen, GameOverScreen)

## 后续开发

详见 `../temp/冒险岛游戏开发计划.md`

## 许可证

仅供学习和个人使用
