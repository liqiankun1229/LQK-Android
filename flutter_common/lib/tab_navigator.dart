import 'dart:async';

import 'package:fcommon/main/content_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class TabNavigator extends StatefulWidget {
  final String title;

  TabNavigator({Key key, this.title}) : super(key: key);

  @override
  State createState() {
    return TabNavigatorState();
  }
}

class TabNavigatorState extends State<TabNavigator> {
  final _defaultColor = Colors.grey;
  final _activeColor = Colors.blue;
  int _currentIndex = 0;

  int _counter = 0;
  String showText = "0";

  static const callFlutter = const EventChannel("plugin_call_flutter");

  static const callNative = const MethodChannel("plugin_call_native");
  StreamSubscription _subscription;

  static const callNativeLogin = const MethodChannel("com.lqk.ku/login");

  static const msgPlugin =
      const BasicMessageChannel("login_message", StringCodec());

  @override
  void initState() {
    super.initState();
    if (_subscription == null) {
      _subscription = callFlutter
          .receiveBroadcastStream()
          .listen(_onEvent, onError: _onError);
    }
  }

  _onEvent(Object object) {
    setState(() {
      showText = object.toString();
    });
  }

  _onError(Object object) {
    setState(() {
      showText = "数据交互异常";
    });
  }

  /// 发送消息
  Future<Null> sendMessage() async {
    String reply = await msgPlugin.send("Flutter Send");
    setState(() {
      showText = reply;
    });
  }

  /// 接收消息
  void receiveMessage() {
    msgPlugin.setMessageHandler((msg) async {
      setState(() {
        showText = msg;
      });
      return msg;
    });
  }

  /// floatButton 点击事件
  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
      showText = _counter.toString();
    });
    if (_counter == 3) {
      _jumpToNative();
    }
    if (_counter == 4) {
      sendMessage();
    }
    if (_counter == 5) {
      _jumpToNativeWithValue();
    }
    if (_counter == 6) {
      _callNativeLogin("jump");
    }
    if (_counter == 7) {
      _callNativeMethod("jump");
    }
    if (_counter == 10) {
      _counter = 0;
    }
  }

  /// 向 Native 发送消息
  Future<Null> _callNativeLogin(String method) async {
    String string = await callNative.invokeMethod(method);
    setState(() {
      showText = string;
    });
  }

  /// 调用 Native 方法
  Future<Null> _callNativeMethod(String method) async {
    String string = await callNative.invokeMethod(method);
    setState(() {
      showText = string;
    });
  }

  /// 无参 Flutter -> Native
  Future<Null> _jumpToNative() async {
    String string = await callNative.invokeMethod('oneAct');
    setState(() {
      showText = string;
      print(string);
    });
  }

  /// 无参 Flutter -> Native
  Future<Null> _jumpToNativeWithValue() async {
    Map<String, String> map = {
      "data":
          "{\"status\": 200, \"message\": \"Login Succeed\", \"data\": {\"name\": \"LQK\", \"mobile\": \"18106899660\",\"sex\":{\"s\":\"男\"}}}"
    };
    String string = await callNative.invokeMethod("twoAct", map);
    setState(() {
      showText = string;
    });
  }

  // pager 控制器 监听 page 的变化
  ContentPagerController contentPagerController = ContentPagerController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [
              Colors.white,
              Colors.white70,
            ],
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
          ),
        ),
        child: ContentPager(
          onPageChanged: (int index) {
            setState(() {
              _currentIndex = index;
            });
          },
          contentPagerController: contentPagerController,
        ),
//        Row(
//          children: <Widget>[
//            Text(
//              '$showText \n 当前是第 $_currentIndex 个Tab',
//              style: TextStyle(
//                fontSize: 18,
//                color: Colors.cyan,
//              ),
//            ),
//
//          ],
//        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        currentIndex: _currentIndex,
        onTap: (index) {
          setState(() {
            contentPagerController.jumpToPage(index, showText);
            _currentIndex = index;
          });
        },
        items: [
          _bottomBuild("首页", Icons.home, 0),
          _bottomBuild("分类", Icons.category, 1),
          _bottomBuild("生活", Icons.filter_drama, 2),
          _bottomBuild("首页", Icons.people, 3),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Row(
          children: [Icon(Icons.add), Text(_counter.toString())],
        ),
      ),
    );
  }

  _bottomBuild(String title, IconData icon, int index) {
    return BottomNavigationBarItem(
      icon: Icon(
        icon,
        color: _defaultColor,
      ),
      activeIcon: Icon(
        icon,
        color: _activeColor,
      ),
      title: Text(
        title,
        style: TextStyle(
            color: _currentIndex != index ? _defaultColor : _activeColor),
      ),
    );
  }
}
