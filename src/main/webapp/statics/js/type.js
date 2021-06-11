function deleteType(typeId){
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
			type:"post",
			url:"type",
			data:{
				actionName:"delete",
				typeId:typeId
			},
			success:function(result){
				if (result.code == 1) {
					swal("","<h2>Sucessfully deleted!</h2>","success");
					deleteDom(typeId);
				} else {
					swal("","<h3>"+result.msg+"</h3>","error");
				}
			}
		});
		
	});
}


function deleteDom(typeId) {
	var trLength = $("#myTable tr").length;
	if (trLength == 2) {
		$("#myTable").remove();
		$("#myDiv").html("<h2>Can't find the type!</h2>");
	} else {
		$("#tr_" + typeId).remove();
	}

	$("#li_" + typeId).remove();
}



function openUpdateDialog(typeId) {

	$("#myModalLabel").html("Change the type");

	var tr = $("#tr_"+typeId);
	var typeName = tr.children().eq(1).text();

	$("#typeName").val(typeName);
	// var typeId = tr.children().eq(0).text();
	$("#typeId").val(typeId);
	$("#msg").html("")

	$("#myModal").modal("show");
	
}






$("#addBtn").click(function(){

	$("#myModalLabel").html("Add new type");
	

	$("#typeId").val("");
	$("#typeName").val("");
	$("#msg").html("")
	

	$("#myModal").modal("show");
});



$("#btn_submit").click(function(){

	var typeId = $("#typeId").val();
	var typeName = $("#typeName").val();
	

	if (isEmpty(typeName)) {
		$("#msg").html("Type name can't be empty");
		return;
	}

	$.ajax({
		type:"post",
		url:"type",
		data:{
			actionName:"addOrUpdate",
			typeId:typeId,
			typeName:typeName
		},
		success:function(result){
			console.log(result);
			if (result.code == 1) {
				$("#myModal").modal("hide");
 				if (isEmpty(typeId)) {
 					//prime key from backend
 					var key = result.result;
 					addDom(key,typeName);
 				} else {
 					updateDom(typeId,typeName);
 				}
				
			} else {
				$("#msg").html(result.msg);
			}
		}
	});
	
});


function updateDom(typeId,typeName) {
	var tr = $("#tr_" + typeId);
	tr.children().eq(1).text(typeName);
	$("#sp_"+typeId).html(typeName);
}


function addDom(typeId,typeName) {
	var tr = '<tr id="tr_'+typeId+'"><td>'+typeId+'</td><td>'+typeName+'</td>';
	tr += '<td><button class="btn btn-primary" type="button" onclick="openUpdateDialog('+typeId+')">Modify</button>&nbsp;';
	tr += '<button class="btn btn-danger del" type="button" onclick="deleteType('+typeId+')">Delete</button></td></tr>';

	var myTable = $("#myTable");
	if (myTable.length > 0) {
		myTable.append(tr);
	} else {
		myTable = '<table class="table table-hover table-striped" id="myTable">';
		myTable += '<tbody> <tr> <th>编号</th> <th>类型</th> <th>操作</th> </tr>';
		myTable += tr + '</tbody></table>';
		
		$("#myDiv").html(myTable);
	}

	

	var li = '<li id="li_'+typeId+'"><a href=""><span id="sp_'+typeId+'">'+typeName+' </span><span class="badge">0</span></a></li>';
	$("#typeUl").append(li);
}


