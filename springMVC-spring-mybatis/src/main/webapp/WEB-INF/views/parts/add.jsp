<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>车管家ERP-库存管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@ include file="../include/css.jsp" %>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp" %>

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="parts"/>
    </jsp:include>

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                配件入库
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-body">
                    <form action="" method="post" id="addForm">
                        <div class="form-group">
                            <label class=" control-label">配件编号:</label>
                            <input type="text" name="partsNo" id="partsNo" class="form-control" placeholder="请输入配件编号">
                        </div>
                        <div class="form-group">
                            <label>配件名称:</label>
                            <input type="text" name="partsName" id="partsName" class="form-control" placeholder="请输入配件名称">
                        </div>

                        <div class="form-group">
                            <label>入库数量:</label>
                            <input type="text" name="inventory" id="inventory" class="form-control" placeholder="请输入入库数量">
                        </div>
                        <div class="form-group">
                            <label>进价:</label>
                            <input type="text" name="inprice" id="inprice" class="form-control" placeholder="请输入进价">
                        </div>
                        <div class="form-group">
                            <label>售价:</label>
                            <input type="text" name="salePrice" id="salePrice" class="form-control" placeholder="请输入售价">
                        </div>
                        <div class="form-group">
                            <label>类型:</label>
                            <select name="typeId" id="typeId" class="form-control">
                                <option>请选择类型</option>
                               <c:forEach items="${typeList}" var="type">
                                    <option value="${type.id}">${type.typeName}</option>
                               </c:forEach>

                            </select>
                        </div>
                        <div class="form-group">
                            <label>产地:</label>
                            <input type="text" name="address" id="address" class="form-control" placeholder="请输入产地">
                        </div>
                    </form>
                    <button class="btn btn-primary pull-left" id="save">保存</button>
                    <button class="btn btn-default pull-left" data-dismiss="modal" id="cancel">取消</button>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    <%@ include file="../include/footer.jsp" %>

</div>

<%@ include file="../include/js.jsp" %>
<script>
    $(function () {
        $("#cancel").click(function () {
           window.location.href = "/parts"
        });

        function add() {
          var partsNo = $("#partsNo").val();
          var partaName = $("#partsName").val();
          var inventory = $("#inventory").val();
          var inprice = $("#inprice").val();
          var salePrice = $("#salePrice").val();
          var typeId = $("#typeId").val();
          var address = $("#address").val();
          $.ajax({
              type: "post",
              url: basePath + "parts/add",
              data: {
                  "partsNo": partsNo,
                  "partaName": partaName,
                  "inventory": inventory,
                  "inprice": inprice,
                  "salePrice": salePrice,
                  "typeId": typeId,
                  "address": address,
              },
              dataType: 'json',
              success: function (msg) {
                  if(msg.msg = '1'){
                      reloadTest();
                  } else if(msg.msg = '0'){
                      layer.alert("添加失败")
                  }
              }

          });
        };




            /*// validate 验证 需要添入jar
            $(#addForm).validate({
                errorElement: 'span',  // 验证错误信息
                errorClass: 'text-danger',  // 验证类型
                rules : {  // 规则
                    partsNo:{
                        required : true,     // required 必填字段 不填则不能提交
                        remote : "/parts/check/partsNo"               // 如果可用，返回ture   这是异步请求
                    },
                    partsName:{
                        required : true
                    },
                    inventory :{
                        required : true
                    },
                    inPrice : {
                        required : true
                    },
                    salePrice : {
                        required : true
                    },
                    typeId : {
                        required : true
                    },
                    address : {
                        required : true
                    },

                },
                messages : {   // 信息
                    partsNo : {
                        required : "请输入配件编号",
                        remote : "该编号不可重复录入"     // false 时提示信息
                    },
                    partsName : {
                        required : "请输入配件名名称"
                    },
                    inventory : {
                        required : "请输入入库数量"
                    },
                    inPrice : {
                        requires : "请输入进价"
                    },
                    salePrice : {
                        required : "请输入售价"
                    },
                    typeId : {
                        required : "请输选择类型"
                    },
                    address : {
                        required : "请输入产地"
                    },

                    errorPlacement: function(error, element) {    //错误信息位置设置方法
                        error.appendTo(element.parent());     //这里的element是录入数据的对象
                    },
                    submitHandler:function () {
                        $.ajax({
                            url : "/parts/add",
                            type : "post",
                            data : $("#addForm").serialize(),
                            beforeSend :function () {
                              $("#save").attr("disabled", "disabled").text("保存中...");
                            },
                            success:function () {
                                if(res.state == "success"){
                                    window.location.href = "/parts"
                                }
                            },
                            error:function () {
                                layer.alert("系统繁忙")
                            },
                            comPlete:function () {
                                $(#save).removieAttr("disabled").text("保存");
                            }

                        })
                    }



                }

            });*/


    })


</script>
</body>
</html>
