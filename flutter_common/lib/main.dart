import 'dart:ui';

import 'package:fcommon/tab_navigator.dart';
import 'package:flutter/material.dart';

void main() => runApp(getRouter(window.defaultRouteName));
// 启动路由, 可以根据路由名称切换 flutter 程序入口
Widget getRouter(String name) {
  switch (name) {
    case 'login':
      return MyApp();
    case 'flutter_login_activity':
      return MyApp();
    default:
      return MyApp();
  }
}

//static const Method

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or press Run > Flutter Hot Reload in a Flutter IDE). Notice that the
        // counter didn't reset back to zero; the application is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: TabNavigator(title: 'Flutter Demo Home Page'),
    );
  }
}
