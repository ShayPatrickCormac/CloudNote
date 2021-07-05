<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-md-9">



    <div class="data_list">
        <div class="data_list_title">
            <span class="glyphicon glyphicon-eye-open"></span>&nbsp;Note Detail
        </div>
        <div>


            <div class="note_title"><h2>${note.title}</h2></div>
            <div class="note_info">
                Pub time: <fmt:formatDate value="${note.pubTime}" pattern="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;Type：${note.typeName}
            </div>
            <div class="note_content">
                <p>${note.content}</p>
            </div>
            <div class="note_btn">
                <button class="btn btn-primary" type="button" onclick="update(${note.noteId})">Modify</button>
                <button class="btn btn-danger" type="button" onclick="del(${note.noteId})">Delete</button>
            </div>



        </div>


    </div>

    <script>
        function update(data){
            window.location="note?noteId="+data;
        }

        function del(data){
            swal({title: "Delete Alert",
                text: "Are you sure about this deletion？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                cancelButtonText: "Cancel",
                confirmButtonText: "Yes"
            }).then(function(isConfirm) {
                if (isConfirm === true) {
                    window.location="note?act=del&noteId="+data;
                }
            });
        }
    </script>

</div>