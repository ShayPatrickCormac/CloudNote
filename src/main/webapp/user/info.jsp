<%--
  Created by IntelliJ IDEA.
  User: Zijian Huang
  Date: 6/4/2021
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-9">


    <script>
        function checkUser(){
            var nickName=$('#nick').val();
            if(nickName.length==0){
                $("#msg").html("Nickname can't be empty");
                return false;
            }
            return true;
        }
    </script>
    <div class="data_list">
        <div class="data_list_title"><span class="glyphicon glyphicon-edit"></span>&nbsp;User Center </div>
        <div class="container-fluid">
            <div class="row" style="padding-top: 20px;">
                <div class="col-md-8">
                    <form class="form-horizontal" method="post" action="user?act=save" enctype="multipart/form-data" onsubmit="return checkUser();">
                        <div class="form-group">
                            <input type="hidden" name="act" value="save">
                            <label for="nickName" class="col-sm-2 control-label">NickName</label>
                            <div class="col-sm-3">
                                <input class="form-control" name="nick" id="nickName" placeholder="NickName" value="Test">
                            </div>
                            <label for="img" class="col-sm-2 control-label">Avatar</label>
                            <div class="col-sm-5">
                                <input type="file" id="img" name="img">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mood" class="col-sm-2 control-label">mood</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="mood" id="mood" rows="3">Test2</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" id="btn" class="btn btn-success">Modify</button>&nbsp;&nbsp;<span style="color:red" id="msg"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4"><img style="width:260px;height:200px" src="/statics/images/h2.jpg"></div>
            </div>
        </div>
    </div>
    <script>
        $(function(){

                var target=$("#nickName");
                target.blur(
                    function(){
                        $("#btn").attr('disabled',false);
                        $("#msg").html('');
                        var value =target.val();
                        if(value.length==0 ||value=='Test2'){
                            target.val('Test2');
                            return ;
                        }

                        //ajax验证
                        $.getJSON("user",{
                            act:'unique',
                            nick:value
                        },function(data){
                            if(data.resultCode==-1){
                                $("#msg").html(value+"Username already exists");
                                target.val('');
                                $("#btn").attr('disabled',true);
                            }else{
                                $("#btn").attr('disabled',false);
                            }
                        });
                    }

                );
            }
        );
    </script>
</div>