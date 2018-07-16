# ！！！重点！！！
- 请使用正式 master Tag 代码


# 本项目使用[RePlugin](https://github.com/Qihoo360/RePlugin/wiki)
- version: v2.2.4
- 详情请看github

# 项目结构说明
   * [host/](host) 宿主项目
   * [host-app-config/](host-app-config) 配置主项目的配置
        * [MainPng](host-app-config/MainPng) 图片资源
        * [app-config.txt](host-app-config/app-config.txt) 多app打包的配置
        * [create-host-variant.gradle](host-app-config/create-host-variant.gradle)
   * [host-plugin-config/](host-plugin-config) 插件环境的配置
       * [buildPluginApkToHost.gradle](host-plugin-config/buildPluginApkToHost.gradle) 打包插件到 [host/src/main/assets](host/src/main/assets)
       * [buildSettingConfig.gradle](host-plugin-config/buildSettingConfig.gradle) 负责[settings.gradle](settings.gradle) 配置
       * [config.gradle](host-plugin-config/config.gradle) 相关的全局配置变量
       * [plugin-config.gradle](host-plugin-config/plugin-config.gradle) 插件的配置
       * [remoceLabelIcon.gradle](host-plugin-config/remoceLabelIcon.gradle) 打包插件移除脚本
   * [host-plugin-template/](host-plugin-template) 插件的生成模板
   * [jks/](jks) 签名文件
   * [debug-apk](debug-apk) 辅助拉去全部插件工具
   * [demo-apk](demo-apk) 演示apk
   * ....
   
   
# [host/libs/aar](host/libs/) sdk 版本有可能更新
- 开放 BxRePluginAppLicationMakeImpl RePluginApplication 接口
    * initRePluginYourNeed()  允许初始乙方公司的[插件管理](https://dc.360.cn/) tag：1.0.0 现在已经弃用
    * initJPushYouNeed()  初始乙方公司 极光推送
        * 可以使用内部定义的
        <pre>
            override fun initJPushYouNeed() {
                    JPushInterface.setDebugMode(BuildConfig.DEBUG)    // 设置开启日志,发布时请关闭日志
                    JPushInterface.init(this)                         // 初始化 JPush
                    val builder = CustomPushNotificationBuilder(this, R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text)
                    builder.layoutIconDrawable = R.mipmap.logo
                    builder.developerArg0 = "developerArg2"
                    JPushInterface.setDefaultPushNotificationBuilder(builder)
                }
        </pre>
- BxStartActivityImpl Activity 启动页界面接口 （跳转看权限）
    * toYourMainActivity() 进入乙方公司app 的逻辑 马甲的界面
- BxJPushReceiverImpl JPushReceiver 极光配置推送 接口
    * isGoToReleaseMain()  一般使用false
    * toGoYouMain() 自己业务的界面

## 项目接入
### [host](host) 项目
   * 项目相关配置
       * 极光的配置看官网  [极光推送](http://www.jiguang.cn)
       * logo ,start app 启动图的配置(必须是png格式)
       * BxStartActivityImpl，BxRePluginAppLicationMakeImpl，BxJPushReceiverImpl


### 方式一  在现有的 Host model 上直接编写
- 在host model StartActivity toYourMainActivity()
    <pre>
        toYourMainActivity(){
        // 主界面入口
            startActvity(Intent(this,MainActivity::java.class))
        }
        
        // 接下来正常开发就可以了
    </pre>
### 方式二  使用 [host-plugin-config](host-plugin-config/plugin-config.gradle) 配置的插件编写 这个会和宿主使用的相同的lib库 ，不支持其他v7lib
- #####第一步
    * 
    <pre>
    pluginConfigs = [
                // plugin-demo 工程
                "plugin-demo": [
                        pliginIsInner    : false, // 内置，外置的打包方式
                        pluginPackageName: 'com.tiangong.android.plugin.demo', // 插件的包名
                        pluginVersion    : 100, // 版本号
                        pluginVer        : 100, // 插件的版本 ， 升级的需要
                        pluginVersionName: "1.0.0", // 版本名称
                ], 
                // plugin-demo2 工程
                "plugin-demo2": [
                        pliginIsInner    : false, // 内置，外置的打包方式
                        pluginPackageName: 'com.tiangong.android.plugin.demo2', // 插件的包名
                        pluginVersion    : 100, // 版本号
                        pluginVer        : 100, // 插件的版本 ， 升级的需要
                        pluginVersionName: "1.0.0", // 版本名称
                ],
                .....
                
        ]
    </pre>
- #####第二步  在 [plugin-config.gradle](host-plugin-config/plugin-config.gradle) 文件中
    * 开启 isSettingRun = true 生成 settings.gradle  这一步 类是于 new module 配置
- #####第三步 Build -- clean  -- build  构建项目
- #####第四步 配置插件主界面入口
    在host model StartActivity toYourMainActivity()
        <pre>
            toYourMainActivity(){
            // 主界面入口
                startActvity(Intent(this,MainActivity::java.class))
            }
            
            // 接下来正常开发就可以了
        </pre>
### 方式三  插件模式 使用[plugin-nosdklib](/plugin-nosdklib) 为模板，不好之处是插件包非常的大，比较损耗性能，加载速度慢
- ##### 其他步骤和方式二（除第一步外）其他同样

# 项目构建 打包  注意如果方式二 形式先打包插件再打包app
 ## 宿主 输出路径(rootDir/apk)
   * [assemble](img/宿主打包.png)
 ## 插件 输出路径(rootDir/host/src/main/assets/plugin)(rootDir/host/src/main/assets/plugin)
   * 需要打开 isPlugin = true
   * 测试插件包 [buildDebugJarToHost](img/插件构建打包.png)
   * 正式插件包 [buildReleaseJarToHost](img/插件构建打包.png)
 ## 插件单独运行
    * [host-plugin-config](host-plugin-config/plugin-config.gradle) 修改 isPlugin = false

# 运行 
- ### 方式一模式下  修改[host-plugin-config](host-plugin-config/plugin-config.gradle) 修改 isPlugin = true
 * 然后 run 项目
- ### 方式二模式下
 * 单独运行宿主  打包插件 再run
 * 单独运行插件 修改[host-plugin-config](host-plugin-config/plugin-config.gradle) 修改 isPlugin = false run



# 注意点
- ！！！ 针对本工程的环境配置不要随便修改 ！！！
- ！！！ 本项目极光推送必须配置 ！！！
- 本工程虽然是[kotlin](https://www.kotlincn.net/)编写的，已经嵌入kotlin 的配置，但是不影响 纯java的开发，按照正常的android开发就可以了
- 本项目对v4的工程深度的定制优化，项目引入v4包的请注意！！！！
- v7 repluginHostConfig {
     
         /**
          * 主包不使用
          */
         useAppCompat = false
     }
     是关闭的，请不要引入
- recycleView 本身的xml定义的属性 已经卡掉，请悉知
- 由于replugin对插件不支持混淆，请不要打开
- ！！！！！！ 由于其他开发者 说不能使用v7包，我们添加了插件[plugin-nosdklib](/plugin-nosdklib)的例子 ，不好之处是插件包非常的大，比较损耗性能，加载速度慢

# 个人建议
- 代码直接在 host编写
- 针对图片大小，最好使用[tinypng.com](https://tinypng.com/) 进行压缩


