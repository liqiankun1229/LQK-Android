import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

/// 卡片基类
class BaseCard extends StatefulWidget {
  @override
  BaseCardState createState() {
    return BaseCardState();
  }
}

class BaseCardState extends State<BaseCard> {
  // subTitle 默认颜色
  Color subTitleColor = Colors.grey;
  Color footerTitleColor = Colors.cyan;

  @override
  Widget build(BuildContext context) {
    // 裁剪圆角
    return PhysicalModel(
      color: Colors.greenAccent,
      borderRadius: BorderRadius.circular(6),
      clipBehavior: Clip.antiAlias, // 抗锯齿
      child: Container(
        decoration: BoxDecoration(color: Colors.white),
        child: Container(
          color: Colors.transparent,
          child: Column(
            children: <Widget>[
              topContent(),
              bottomContent(),
            ],
          ),
        ),
      ),
    );
  }

  /// 标题
  topContent() {
    return Padding(
      padding: EdgeInsets.fromLTRB(20, 20, 26, 20),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Row(
            crossAxisAlignment: CrossAxisAlignment.end,
            children: <Widget>[
              topTitle(""),
              topTitle2(""),
            ],
          ),
          subTitle(""),
        ],
      ),
    );
  }

  /// 内容
  bottomContent() {
    return Container();
  }

  /// 一级标题
  topTitle(String topTitle) {
    return Padding(
      padding: EdgeInsets.only(top: 5),
      child: Text(
        topTitle,
        style: TextStyle(
          fontSize: 22,
          color: Colors.black,
        ),
      ),
    );
  }

  /// 顶部二级标题
  topTitle2(String topTitle) {
    return Container();
  }

  /// 底部文字
  footerTitle(String footerTitle) {
    return Text(
      footerTitle,
      style: TextStyle(fontSize: 12, color: footerTitleColor),
    );
  }

  /// 二级标题
  subTitle(String subTitle) {
    return Padding(
      padding: EdgeInsets.only(top: 5),
      child: Text(
        subTitle,
        style: TextStyle(
          fontSize: 12,
          color: subTitleColor,
        ),
      ),
    );
  }
}
