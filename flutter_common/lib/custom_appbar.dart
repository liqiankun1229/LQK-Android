import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget {
  String hotBookName = "牧神记";

  @override
  Widget build(BuildContext context) {
    double paddingTop = MediaQuery.of(context).padding.top;
    return Container(
      margin: EdgeInsets.fromLTRB(20, paddingTop + 10, 20, 5),
      padding: EdgeInsets.fromLTRB(20, 7, 20, 7),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(19),
        color: Colors.brown,
      ),
      child: Row(
        children: <Widget>[
          Icon(
            Icons.search,
            color: Colors.white,
          ),
          Expanded(
            child: Text(
              '$hotBookName',
              style: TextStyle(color: Colors.white),
            ),
          ),
          Container(
            width: 1,
            height: 20,
            margin: EdgeInsets.only(right: 13),
            decoration: BoxDecoration(color: Colors.white),
          ),
          Text(
            '书城',
            style: TextStyle(fontSize: 13, color: Colors.white),
          )
        ],
      ),
    );
  }
}
