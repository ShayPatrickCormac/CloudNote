<%--
  Created by IntelliJ IDEA.
  User: Zijian Huang
  Date: 6/22/2021
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-9">


    <div class="data_list">
        <div class="data_list_title">
            <span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;
            <c:if test="${empty noteInfo}">
                Add Note
            </c:if>
            <c:if test="${!empty noteInfo}">
                Modify Note
            </c:if>
        </div>
        <div class="container-fluid">
            <div class="container-fluid">
                <div class="row" style="padding-top: 20px;">
                    <div class="col-md-12">
<%--                        Determine if the type list is empty--%>
                        <c:if test="${empty typeList}">
                            <h2>Can't get types!</h2>
                            <h4><a href="type?actionName=list">add type</a></h4>
                        </c:if>
                        <c:if test="${!empty typeList}">
                        <form class="form-horizontal" method="post" action="note">
                            <%-- Set hidden field --%>
                            <input type="hidden" name="actionName" value="addOrUpdate">
                            <!-- noteId hidden field -->
                            <input type="hidden" name="noteId" value="${noteInfo.noteId}">
                            <div class="form-group">
                                <label for="typeId" class="col-sm-2 control-label">type:</label>
                                <div class="col-sm-8">
                                    <select id="typeId" class="form-control" name="typeId">
                                        <option value="">Select note type</option>
                                        <c:forEach var="item" items="${typeList}">
                                            <c:choose>
                                                <c:when test="${!empty resultInfo}">
                                                    <option <c:if test="${resultInfo.result.typeId == item.typeId}">selected</c:if> value="${item.typeId}">${item.typeName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option <c:if test="${noteInfo.typeId == item.typeId}">selected</c:if> value="${item.typeId}">${item.typeName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>



                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">Title:</label>
                                <div class="col-sm-8">
                                    <c:choose>
                                        <c:when test="${!empty resultInfo}">
                                            <input class="form-control" name="title" id="title" placeholder="Note Title" value="${resultInfo.result.title}">
                                        </c:when>
                                        <c:otherwise>
                                            <input class="form-control" name="title" id="title" placeholder="Note Title" value="${noteInfo.title}">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">Content:</label>
                                <div class="col-sm-8">
                                    <c:choose>
                                        <c:when test="${!empty resultInfo}">
                                            <%--                                        prepare the container for editor--%>
                                            <textarea id="content" name="content">${resultInfo.result.content}</textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <%--                                        prepare the container for editor--%>
                                            <textarea id="content" name="content">${noteInfo.content}</textarea>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-4">
                                    <input type="submit" class="btn btn-primary" onclick="return checkForm();" value="Save">
                                    &nbsp;<span id="msg" style="font-size: 12px;color: red">${resultInfo.msg}</span>
                                </div>
                            </div>
                        </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    var ue;
    $(function () {
        ue = UE.getEditor('content');
    });

    function checkForm() {

        var typeId = $("#typeId").val();
        var title = $("#title").val();
        var content = ue.getContent();

        if (isEmpty(typeId)) {
            $("#msg").html("Please select note type")
            return false;
        }
        if (isEmpty(title)) {
            $("#msg").html("Title can't be empty")
            return false;
        }
        if (isEmpty(content)) {
            $("#msg").html("Content can't be empty")
            return false;
        }
        return true;
    }
</script>