<%--
  Created by IntelliJ IDEA.
  User: Zijian Huang
  Date: 6/4/2021
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-9">



    <div class="data_list">
        <div class="data_list_title"><span class="glyphicon glyphicon glyphicon-th-list"></span>&nbsp;
            Note List </div>

        <div class="note_datas">
            <ul>
                <li>2016-08-04&nbsp;&nbsp;<a href="note?act=view&amp;noteId=28">123</a> </li>
                <li>2016-08-04&nbsp;&nbsp;<a href="note?act=view&amp;noteId=27">Test</a> </li>
            </ul>
        </div>
        <nav style="text-align: center">
            <ul class="pagination   center">
                <li class="active"><a href="main#">1</a></li>
                <li><a href="main?act=&amp;val=&amp;valStr=&amp;current=2">2</a></li>
                <li><a href="main?act=&amp;val=&amp;valStr=&amp;current=3">3</a></li>
                <li><a href="main?act=&amp;val=&amp;valStr=&amp;current=4">4</a></li>
                <li><a href="main?act=&amp;val=&amp;valStr=&amp;current=5">5</a></li>
                <li>
                    <a href="main?act=&amp;val=&amp;valStr=&amp;current=2">
                        <span>»</span>
                    </a>
                </li>
            </ul>
        </nav>

    </div>

</div>