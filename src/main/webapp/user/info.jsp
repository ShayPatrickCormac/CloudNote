<%--
  Created by IntelliJ IDEA.
  User: Zijian Huang
  Date: 6/4/2021
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="col-md-9">


    <div class="data_list">
        <div class="data_list_title"><span class="glyphicon glyphicon-edit"></span>&nbsp;User Center </div>
        <div class="container-fluid">
            <div class="row" style="padding-top: 20px;">
                <div class="col-md-8">
                    <form class="form-horizontal" method="post" action="user" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="hidden" name="actionName" value="updateUser">
                            <label for="nickName" class="col-sm-2 control-label">NickName</label>
                            <div class="col-sm-3">
                                <input class="form-control" name="nick" id="nickName" placeholder="NickName" value="${user.nick}">
                            </div>
                            <label for="img" class="col-sm-2 control-label">Avatar</label>
                            <div class="col-sm-5">
                                <input type="file" id="img" name="img">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mood" class="col-sm-2 control-label">mood</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="mood" id="mood" rows="3">${user.mood}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" id="btn" class="btn btn-success" onclick="return updateUser();">Modify</button>&nbsp;&nbsp;<span style="color:red; font-size: 12px" id="msg"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4"><img style="width:240px;height:180px" src="user?actionName=userHead&imageName=${user.head}"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    // get nickname value
    $("#nickName").blur(function () {
        var nickName = $("#nickName").val();
        // Determine if the value is empty
        if (isEmpty(nickName)) {
            $("#msg").html("Username can't be empty");
            $("#btn").prop("disabled", true);
        }
        // Determine if the name is changed
        // get nickname from session
        var nick = '${user.nick}';
        // if same
        if (nickName == nick) {
            return;
        }
        // if not
        $.ajax({
            type:"get",
            url:"user",
            data:{
                actionName:"checkNick",
                nick:nickName
            },
            success:function (result) {
                // if usable
                if (result == 1) {
                    $("#msg").html("");
                    $("#btn").prop("disabled", false);
                }
                else {
                    //if not usable
                    $("#msg").html("Nickname already exists");
                    $("#btn").prop("disabled", true);
                }
            }

        });
    }).focus(function () {
        // clear the msg
        $("#msg").html("");
        $("#btn").prop("disabled", false);
    });

    function updateUser() {
        var nickName = $("#nickName").val();
        // Determine if the value is empty
        if (isEmpty(nickName)) {
            $("#msg").html("Username can't be empty");
            $("#btn").prop("disabled", true);
            return false;
        }

        return true;
    }
</script>