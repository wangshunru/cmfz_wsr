<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="application/javascript">
    $(function () {
        $("#list").jqGrid({
            url: "${path}/banner/showAll",
            //JQGRID 使用 Bootstrap 的主题风格
            styleUI: "Bootstrap",
            //后台相应回的数据类型
            datatype: "json",
            //cellEdit:true,
            //分页标识
            pager: "#pager",
            //每页显示的数据
            rowNum: 4,
            //每页显示数据条数下拉列表
            rowList: [2, 4, 6, 8],
            //访问增删改的action
            editurl: "${path}/banner/edit",
            //自适应
            autowidth: true,
            //列名
            colNames: ["ID", "名称", "路径", "状态", "上传时间", "描述"],
            height: 300,
            //是否显示总条数
            viewrecords: true,
            closeAfterEdit: true,
            closeAfterAdd: true,
            //显示行号
            rownumbers: true,
            //设置每列属性
            colModel: [
                {name: "id", hidden: true, align: "center"},
                {name: "title", editable: true, align: "center"},
                {
                    name: "img_path",
                    editable: true,
                    edittype: "file",
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${path}/image/photo/" + cellvalue + "' height='50px' width='100px'/>"
                    }
                },
                {
                    name: "status",
                    align: "center",
                    formatter:function (cellvalue, options, rowObject) {
                        if(cellvalue == "0"){
                            return "<button type='button' class='btn btn-success' value='"+cellvalue+"' id='bannerstatus' onclick=\"update('" + rowObject.id + "')\">冻结</button>"
                        }else{
                            return "<button type='button' class='btn btn-danger' value='"+cellvalue+"' id='bannerstatus' onclick=\"update('" + rowObject.id + "')\">解除冻结</button>"
                        }
                }},
                {name: "up_date", align: "center", formatter:"date", formatoptions: {newformat:'Y-m-d'}},
                {name: "description", editable: true, align: "center"}
            ]
        }).jqGrid("navGrid", "#pager", {add: true, edit: true, del: true,search:false, addtext: "添加", edittext: "编辑"},
            {
                afterSubmit: function (uuid) {
                    $.ajaxFileUpload({
                        url: "${path}/banner/upload",
                        type: "post",
                        datatype: "json",
                        fileElementId: "pic_img",
                        data: {id: uuid.responseText},
                        success: function () {
                            $("#list").trigger("reloadGrid")
                        }
                    })
                    return "hello";
                },
                closeAfterEdit: true
            },
            {
                afterSubmit: function (uuid) {
                    $.ajaxFileUpload({
                        url: "${path}/banner/upload",
                        type: "post",
                        datatype: "json",
                        fileElementId: "img_path",
                        data: {id: uuid.responseText},
                        success: function () {
                            $("#list").trigger("reloadGrid")
                        }
                    })
                    return "hello";
                },
                closeAfterAdd: true
            }
        );
    })
    //添加操作
    $("#add").click(function () {
        $("#list").jqGrid('editGridRow', "new", {
            afterSubmit: function (uuid) {
                $.ajaxFileUpload({
                    url: "${path}/banner/upload",
                    type: "post",
                    datatype: "json",
                    fileElementId: "img_path",
                    data: {id: uuid.responseText},
                    success: function () {
                        $("#list").trigger("reloadGrid")
                    }
                })
                return "hello";
            },
            closeAfterAdd: true
        });
    });
    //修改
    $("#edit").click(function () {
        var gr = $("#list").jqGrid('getGridParam', 'selrow');
        if (gr != null){
            $("#list").jqGrid('editGridRow', gr, {
                afterSubmit: function (uuid) {
                    $.ajaxFileUpload({
                        url: "${path}/banner/upload",
                        type: "post",
                        datatype: "json",
                        fileElementId: "img_path",
                        data: {id: uuid.responseText},
                        success: function () {
                            $("#list").trigger("reloadGrid")
                        }
                    })
                    return "hello";
                },
                closeAfterEdit: true
               /* height: 300,*/
                /*reloadAfterSubmit: true,
                closeAfterEdit: true*/
            });
        } else{
            alert("请选择要修改的数据!");
        }
    });
    //删除操作
    $("#del").click(function () {
        var gr = $("#list").jqGrid('getGridParam', 'selrow');
        if (gr != null)
            $("#list").jqGrid('delGridRow', gr, {
                reloadAfterSubmit: true,
            });
        else
            alert("请选择要删除的数据行!");
    });
    //修改状态
    function update(id) {
       $.ajax({
           url:"${path}/banner/status",
           type:"post",
           datatype:"json",
           data:"id="+id,
           success:function () {
               $("#list").trigger("reloadGrid")
           }
       })
    };
</script>
<div class="col-md-12">
    <div class="panel panel-success">
        <div class="panel-heading"><h4>轮播图信息</h4></div>
    </div>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">轮播图信息</a></li>
    </ul>
    <div class="panel panel-default">
        <br/>
        <div>
            &nbsp; &nbsp;
            <button type="button" class="btn btn-success" id="add">添加轮播图</button>
            <button type="button" class="btn btn-info" id="edit">修改轮播图</button>
            <button type="button" class="btn btn-warning" id="del">删除轮播图</button>
        </div>
        <br/>
        <div>
            <table id="list"></table>
        </div>
        <div id="pager"></div>
    </div>
</div>
