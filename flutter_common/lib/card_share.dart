import 'package:fcommon/base_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

/// 分享 卡片界面
class CardShare extends BaseCard {
  @override
  createState() => CardShareState();
}

class CardShareState extends BaseCardState {
  @override
  topTitle(String topTitle) {
    return super.topTitle("分享得无限卡");
  }

  @override
  topTitle2(String topTitle) {
    return Padding(
      padding: EdgeInsets.only(bottom: 5),
      child: Text(
        " / 第 19 期",
        style: TextStyle(fontSize: 10),
      ),
    );
  }

  @override
  subTitle(String subTitle) {
    return super.subTitle("分享给朋友 最多可获得12天无限卡");
  }

  @override
  bottomContent() {
    return Expanded(
      child: Container(
        margin: EdgeInsets.only(top: 20),
        decoration: BoxDecoration(color: Colors.transparent),
        child: Column(
          children: <Widget>[
            Expanded(
              child: Container(
                constraints: BoxConstraints.expand(),
                child: Padding(
                  padding: EdgeInsets.only(bottom: 20),
                  child: Image.network(
                    "http://t8.baidu.com/it/u=2247852322,986532796&fm=79&app=86&f=JPEG?w=1280&h=853",
                    fit: BoxFit.cover,
                  ),
                ),
              ),
            ),
            Container(
              alignment: AlignmentDirectional.center,
              child: Padding(
                padding: EdgeInsets.only(bottom: 20),
                child: footerTitle("123456789 人 · 参加活动"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
