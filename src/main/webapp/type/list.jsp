<%--
  Created by IntelliJ IDEA.
  User: Zijian Huang
  Date: 6/4/2021
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%--            Determine if type collection exists--%>
            <c:if test="${empty typeList}">
                <h2>Didn't get type data</h2>
            </c:if>
            <c:if test="${!empty typeList}">

            <table class="table table-hover table-striped ">
                <tbody>
                <tr>
                    <th>Number</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${typeList}" var="item">:
                    <tr>
                        <td>${item.typeId}</td>
                        <td>${item.typeName}</td>
                        <td>
                            <button class="btn btn-primary" type="button">Modify</button>&nbsp;
                            <button class="btn btn-danger del" type="button">Delete</button>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
            </c:if>
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

</div>