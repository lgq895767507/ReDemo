##// host/libs/host-sdk-release-1.0.8.aar  版本修改底层类库更改  // 20180609
* 在已经编辑的代码上面改动步骤：
   * [host/libs/host-sdk-release-1.0.8.aar](host/libs/host-sdk-release-1.0.8.aar)  确保已经是1.0.8版本的aar
   * copy [/into-plugin/a.b.c.d.e.jar](/into-plugin/a.b.c.d.e.jar) /into-plugin/a.b.c.d.e.jar 到 /host/src/main/assets/plugins/
   * 修改 [config.gradle](host-plugin-config/config.gradle) jitParkAndroidSupportVersion = '26.0.2.10' 确认已经是 26.0.2.10
                                                              jitParkSupportHttpVersion = '1.0.10.5.5' 确认已经是 1.0.10.5.5
   * 添加[host/build.gradle](host/build.gradle)  在 dependencies 中 implementation "com.qihoo360.replugin:replugin-host-lib:$replugin_version"
   
具体内容可以看git log 提交的文件


// 注意： 已经上市场的app 可以 走 plist-admin.bxvip588.com  安卓全量更新。




##（2018-06-20）//- ！！！！！！ 由于其他开发者 说不能使用v7包，我们添加了插件[plugin-nosdklib](/plugin-nosdklib)的例子 ，不好之处是插件包非常的大，比较损耗性能，加载速度慢，不支持AppCompatActivity配置的颜色，没有宿主没有配置可用的坑位，也不打算扩展