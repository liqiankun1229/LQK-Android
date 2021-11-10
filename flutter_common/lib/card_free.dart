import 'package:fcommon/base_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CardFree extends BaseCard {
  @override
  BaseCardState createState() => CardFreeState();
}

const BOOK_LIST = [
  {'title': "牧神记", 'img': "http://t9.baidu.com/it/u=4169540006,4220376401&fm=79&app=86&f=JPEG?w=1000&h=1500", 'price': "20.0"},
  {'title': "牧神记", 'img': "http://t9.baidu.com/it/u=4169540006,4220376401&fm=79&app=86&f=JPEG?w=1000&h=1500", 'price': "20.0"},
  {'title': "牧神记", 'img': "http://t9.baidu.com/it/u=4169540006,4220376401&fm=79&app=86&f=JPEG?w=1000&h=1500", 'price': "20.0"},
  {'title': "牧神记", 'img': "http://t9.baidu.com/it/u=4169540006,4220376401&fm=79&app=86&f=JPEG?w=1000&h=1500", 'price': "20.0"},
  {'title': "牧神记", 'img': "http://t9.baidu.com/it/u=4169540006,4220376401&fm=79&app=86&f=JPEG?w=1000&h=1500", 'price': "20.0"},
  {'title': "牧神记", 'img': "http://t9.baidu.com/it/u=4169540006,4220376401&fm=79&app=86&f=JPEG?w=1000&h=1500", 'price': "20.0"}
];

class CardFreeState extends BaseCardState {
  @override
  topTitle(String topTitle) {
    return super.topTitle("免费听书管");
  }

  @override
  subTitle(String subTitle) {
    return super.subTitle("第 146 期");
  }

  @override
  bottomContent() {
    return Expanded(
      child: Container(
        margin: EdgeInsets.only(top: 20),
        child: Column(
          children: <Widget>[
            Expanded(
              child: bookList(),
            ),
            Padding(
              padding: EdgeInsets.only(bottom: 20),
              child: bottomBtn(),
            ),
          ],
        ),
      ),
    );
  }

  bookList() {
    return GridView.count(
      crossAxisCount: 3,
      mainAxisSpacing: 10,
      crossAxisSpacing: 15,
      childAspectRatio: 0.46,
      padding: EdgeInsets.only(left: 20, right: 20),
      children: BOOK_LIST.map((item) {
        return _item(item);
      }).toList(),
    );
  }

  bottomBtn() {
    return FractionallySizedBox(
      widthFactor: 1,
      child: Container(
        padding: EdgeInsets.only(left: 20, right: 20),
        child: RaisedButton(
          color: Colors.blue,
          onPressed: () {},
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(30),
          ),
          padding: EdgeInsets.only(top: 13, bottom: 15),
          child: Text(
            "免费领取",
            style: TextStyle(color: Colors.white),
          ),
        ),
      ),
    );
  }

  Widget _item(Map<String, String> item) {
    return Container(
      child: Column(
        children: <Widget>[
          // 绝对布局
          Stack(
            alignment: AlignmentDirectional.center,
            children: <Widget>[
              Image.network(
                "${item['img']}",
                fit: BoxFit.cover,
              ),
              Container(
                width: 30,
                height: 30,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(20),
                  color: Colors.black26,
                ),
                child: Icon(
                  Icons.play_circle_outline,
                  color: Colors.white,
                ),
              ),
              Positioned(
                child: Container(
                  padding: EdgeInsets.all(3),
                  decoration: BoxDecoration(color: Colors.black54),
                  child: Text(
                    "原价: ${item['price']}",
                    style: TextStyle(color: Colors.white),
                  ),
                ),
                bottom: 0,
                left: 0,
                right: 0,
              ),
            ],
          ),
          Text(
            "${item["title"]}",
            style: TextStyle(fontSize: 12, color: Colors.black54),
          ),
        ],
      ),
    );
  }
}
