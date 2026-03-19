# 构建说明

## GitHub Actions 自动构建

本项目已配置 GitHub Actions，每次 push 到 `main` 或 `develop` 分支时会自动构建 APK。

### 自动构建流程

1. **Push 到 main/develop 分支**
   - 自动触发 CI 构建
   - 生成 Debug APK
   - 上传到 Actions Artifacts（保留 30 天）

2. **创建 Tag（发布版本）**
   - 自动构建 Release APK
   - 创建 GitHub Release
   - 上传 APK 到 Release

### 下载测试 APK

#### 方法 1: 从 Actions 下载（最新构建）

1. 访问 https://github.com/Syakaraka/adventure-island/actions
2. 点击最新的构建（绿色勾）
3. 在页面底部的 "Artifacts" 部分
4. 点击 `adventure-island-debug` 下载
5. 解压后得到 APK 文件

#### 方法 2: 从 Releases 下载（正式版本）

1. 访问 https://github.com/Syakaraka/adventure-island/releases
2. 下载最新版本的 APK

### 手动触发构建

创建一个新的 commit 并 push：
```bash
git commit --allow-empty -m "Trigger CI build"
git push
```

### 发布新版本

```bash
# 创建版本标签
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

Actions 会自动：
- 构建 Release APK
- 创建 GitHub Release
- 上传 APK 到 Release

### 本地构建

```bash
# Debug 版本
./gradlew assembleDebug

# Release 版本
./gradlew assembleRelease

# 安装到设备
./gradlew installDebug
```

APK 输出位置：
- Debug: `android/build/outputs/apk/debug/`
- Release: `android/build/outputs/apk/release/`

## 故障排查

### 构建失败

检查 Actions 日志：
1. 访问 Actions 页面
2. 点击失败的构建
3. 查看具体错误信息

常见问题：
- Gradle 依赖下载失败 → 检查网络连接
- SDK 版本不匹配 → 检查 `build.gradle.kts` 配置
- 签名问题（Release）→ 需要配置签名密钥

### 下载不到 APK

- 确保构建成功（绿色勾）
- Artifacts 只在构建成功后才上传
- Artifacts 保留 30 天，过期自动删除
