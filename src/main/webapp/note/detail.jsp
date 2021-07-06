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
                <button class="btn btn-primary" type="button" onclick="updateNote(${note.noteId})">Modify</button>
                <button class="btn btn-danger" type="button" onclick="deleteNote(${note.noteId})">Delete</button>
            </div>



        </div>


    </div>

</div>

<script type="text/javascript">

    function deleteNote(noteId) {
        swal({
            title: "",
            text: "<h3>Are you sure you want to delete this?</h3>",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "orange",
            confirmButtonText: "Confirm",
            cancelButtonText: "Cancel"
        }).then(function(){
            $.ajax({
               type: "post",
               url: "note",
               data:{
                   actionName: "delete",
                   noteId: noteId
               },
               success: function (code) {
                   if (code == 1) {
                       window.location.href = "index";
                   } else {
                       swal("", "<h3>Delete failed</h3>", "error");
                   }
               }
            });

        });
    }

    function updateNote(noteId) {
        window.location.href = "note?actionName=view&noteId="+noteId;
    }


</script>