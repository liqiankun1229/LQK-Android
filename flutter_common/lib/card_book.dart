import 'package:fcommon/base_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CardBook extends BaseCard {
  @override
  BaseCardState createState() {
    return CardBookState();
  }
}

class CardBookState extends BaseCardState {
  @override
  topContent() {
    return Column(
      children: <Widget>[
        Container(
          padding: EdgeInsets.fromLTRB(66, 36, 66, 30),
          decoration: BoxDecoration(color: Colors.white70),
          child: Container(
            decoration: BoxDecoration(
              boxShadow: [
                BoxShadow(
                  color: Colors.grey,
                  blurRadius: 20,
                  offset: Offset(0, 10),
                ),
              ],
            ),
            child: Image.network("http://t9.baidu.com/it/u=583874135,70653437&fm=79&app=86&f=JPEG?w=3607&h=2408"),
          ),
        ),
        Container(
          padding: EdgeInsets.only(left: 20, top: 15, bottom: 15, right: 20),
          decoration: BoxDecoration(color: Colors.black12),
          child: Row(
            children: <Widget>[
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(
                    "书名",
                    style: TextStyle(fontSize: 18, color: Colors.black54),
                  ),
                  Text(
                    "作者",
                    style: TextStyle(fontSize: 13, color: Colors.grey),
                  )
                ],
              ),
              Container(
                margin: EdgeInsets.only(left: 20),
                padding: EdgeInsets.only(left: 10, right: 10, top: 5, bottom: 5),
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(20),
                  gradient: LinearGradient(colors: [Colors.blue, Colors.lightBlue]),
                ),
                child: Text(
                  "分享领取书籍",
                  style: TextStyle(color: Colors.white),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }

  @override
  bottomContent() {
    return Expanded(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Padding(
            padding: EdgeInsets.only(left: 20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Image.network(
                  "",
                  height: 26,
                  width: 26,
                ),
                Text("书籍描述"),
              ],
            ),
          ),
          footerTitle('更多好书免费领 >'),
        ],
      ),
    );
  }
}
