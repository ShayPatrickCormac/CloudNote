<%--
  Created by IntelliJ IDEA.
  User: Zijian Huang
  Date: 6/4/2021
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- saved from url=(0036)http://localhost:8080/cloudnote/type -->
<div class="col-md-9">


    <div class="data_list">
        <div class="data_list_title">
            <span class="glyphicon glyphicon-list"></span>&nbsp;Type List
            <span class="noteType_add">
			<button class="btn btn-sm btn-success" type="button" id="addBtn">Add Type</button>
		</span>

        </div>
        <div>
            <table class="table table-hover table-striped ">
                <tbody><tr>
                    <th>Number</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>


                <tr>
                    <td>2</td>
                    <td>Tech</td>
                    <td>
                        <button class="btn btn-primary" type="button">Modify</button>&nbsp;
                        <button class="btn btn-danger del" type="button">Delete</button>
                    </td>
                </tr>

                <tr>
                    <td>3</td>
                    <td>Note</td>
                    <td>
                        <button class="btn btn-primary" type="button">Modify</button>&nbsp;
                        <button class="btn btn-danger del" type="button">Delete</button>
                    </td>
                </tr>

                <tr>
                    <td>4</td>
                    <td>Quote</td>
                    <td>
                        <button class="btn btn-primary" type="button">Modify</button>&nbsp;
                        <button class="btn btn-danger del" type="button">Delete</button>
                    </td>
                </tr>

                <tr>
                    <td>5</td>
                    <td>test</td>
                    <td>
                        <button class="btn btn-primary" type="button">Modify</button>&nbsp;
                        <button class="btn btn-danger del" type="button">Delete</button>
                    </td>
                </tr>


                </tbody></table>
        </div>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="typename">Type Name</label>
                        <input type="hidden" name="typeId">
                        <input type="text" name="typename" class="form-control" id="typename" placeholder="Type Name">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove"></span>Close</button>
                    <button type="button" id="btn_submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk"></span>Save</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(
            function(){
                $("#addBtn").click(
                    function(){
                        $("#myModalLabel").text("Add");
                        $('#myModal').modal();
                        $("input[name='typeId']").val('');
                        $('#typename').val('');
                    }
                );

                var target=$('#typename');
                target.focus(
                    function(){
                        $('#btn_submit').html('Save').removeClass("btn-danger").addClass("btn-info").attr('disabled',false);
                    }
                );
                target.blur(
                    function(){
                        var val =target.val();
                        if(val.length==0){
                            return;
                        }
                        $.getJSON("type",{
                            act:'unique',
                            typename:val
                        },function(data){
                            if(data.resultCode==-1){
                                $('#btn_submit').html('Name already exists').removeClass("btn-primary").addClass("btn-danger").attr('disabled',true);
                            }else{
                                $('#btn_submit').html('Save').removeClass("btn-danger").addClass("btn-info").attr('disabled',false);

                            }
                        });
                    }
                );

                $('#btn_submit').click(
                    function(){
                        var idVal =$("input[name='typeId']").val();
                        $.getJSON("type",{
                            act:'save',
                            typename:$('#typename').val(),
                            typeId:idVal
                        },function(data){
                            if(data.resultCode==1){
                                if(idVal.length==0){
                                    var noteType =data.result;
                                    var tr="<tr><td>"+noteType.typeId+"</td>";
                                    tr +="<td>"+noteType.typeName+"</td>";
                                    tr +="<td><button class=\"btn btn-primary\" type=\"button\">Modify</button>&nbsp;";
                                    tr +="<button class=\"btn btn-danger\" type=\"button\">Delete</button></td></tr>";
                                    $(".table.table-hover.table-striped").append(tr);

                                }else{
                                    var targetTr ;
                                    $("table tr:gt(0)").each(function(i){
                                        $(this).children("td:eq(0)").each(function(i){
                                            if($(this).text()==idVal){
                                                targetTr=$(this).parent();
                                                return false;
                                            }
                                        });
                                    });
                                    targetTr.children('td').eq(1).html($('#typename').val());
                                }
                                $('#myModal').modal('hide');
                            }
                        });
                    }
                );

                $('.table').on('click','.btn-primary',function(){
                    var tr=$(this).parent().parent();
                    var typeId=tr.children('td').eq(0).text();
                    var typeName=tr.children('td').eq(1).text();

                    $("#myModalLabel").text("Modify");
                    $('#myModal').modal();
                    $('#btn_submit').html('Save').removeClass("btn-danger").addClass("btn-info").attr('disabled',true);
                    $('#typename').val(typeName);
                    $("input[name='typeId']").val(typeId);
                });
                $('.table').on('click','.btn-danger',function(){
                    var tr=$(this).parent().parent();
                    var typeId=tr.children('td').eq(0).text();
                    var typeName=tr.children('td').eq(1).text();
                    swal({title: "Action Alert",
                        text: "Are you sure you want to delete"+typeName,
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        cancelButtonText: "No",
                        confirmButtonText: "Yes"
                    }).then(function(isConfirm) {
                        if (isConfirm === true) {
                            $.getJSON("type",{
                                act:'del',
                                typeId:typeId
                            },function(data){
                                if(data.resultCode==1){
                                    swal('Sucessful','Data Deleted','success');
                                    var targetTr ;
                                    $("table tr:gt(0)").each(function(i){
                                        $(this).children("td:eq(0)").each(function(i){
                                            if($(this).text()==typeId){
                                                targetTr=$(this).parent();
                                                return false;
                                            }
                                        });
                                    });
                                    targetTr.remove();
                                }else if(data.resultCode==0){
                                    swal('failed','Sub-record exists','error');
                                }else{
                                    swal('failed','Unknown reason','error');
                                }
                            });

                        }
                    });
                });
            }
        );
    </script>
</div>