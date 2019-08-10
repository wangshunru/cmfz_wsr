<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="application/javascript">
    $(function () {
        $("#list").jqGrid({
            url: "${path}/user/showAll",
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
            editurl: "${path}/user/edit",
            //自适应
            autowidth: true,
            //列名
            colNames: ["ID", "头像", "名字", '昵称', '密码', '性别', "状态", '手机号', "注册时间"],
            height: '100%',
            //是否显示总条数
            viewrecords: true,
            closeAfterEdit: true,
            closeAfterAdd: true,
            //显示行号
            rownumbers: true,
            //设置每列属性
            colModel: [
                {name: "id", hidden: true, align: "center"},
                {
                    name: "pic_img",
                    editable: true,
                    edittype: "file",
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='" + cellvalue + "' height='50px' width='100px'/>"
                    }
                },
                {name: "name", editable: true, align: "center"},
                {name: "ahama", editable: true, align: "center"},
                {name: "password", align: "center"},
                {name: "sex", editable: true, align: "center", width: "60px"},
                {
                    name: "status",
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        if (cellvalue == "0") {
                            return "<button type='button' class='btn btn-success' value='" + cellvalue + "' id='userstatus' onclick=\"update('" + rowObject.id + "')\">冻结</button>"
                        } else {
                            return "<button type='button' class='btn btn-danger' value='" + cellvalue + "' id='userstatus' onclick=\"update('" + rowObject.id + "')\">解除冻结</button>"
                        }
                    }
                },
                {name: "phone", editable: true, align: "center"},
                {name: "reg_date", align: "center", formatter: "date", formatoptions: {newformat: 'Y-m-d'}}

            ]
        }).jqGrid("navGrid", "#pager", {
                add: false,
                edit: true,
                del: false,
                search: false,
                addtext: "添加",
                edittext: "编辑"
            },
            {
                afterSubmit: function (uuid) {
                    $.ajaxFileUpload({
                        url: "${path}/user/upload",
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
            }
        );
    })

    function update(id) {
        $.ajax({
            url: "${path}/user/status",
            type: "post",
            datatype: "json",
            data: "id=" + id,
            success: function () {
                $("#list").trigger("reloadGrid")
            }
        })
    };

    $("#add").click(function () {
        $.ajax({
            url: "${path}/user/export",
            type: "post",
            data: "mark=" + 0,
            datatype: "json",
            success: function (text) {
                alert(text)
            }
        })
    })
    $("#all").click(function () {
        $.ajax({
            url: "${path}/user/export",
            type: "post",
            data: "mark=" + 1,
            datatype: "json",
            success: function (text) {
                alert(text)
            }
        })
    })
</script>

<div class="col-md-12">
    <div class="panel panel-default">
        <div class="panel-heading"><h4>用户信息</h4></div>
    </div>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">用户管理</a></li>
    </ul>
    <div class="panel panel-default">
        <br/>
        <div>
            &nbsp; &nbsp;
            <button type="button" class="btn btn-success" id="add">导出当前用户信息</button>
            <button type="button" class="btn btn-info" id="edit">导入用户</button>
            <button type="button" class="btn btn-warning" id="all">导出所有用户信息</button>
            <div class="input-group">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">发送验证码</button>
                 </span>
                <input type="text" class="form-control" placeholder="请输入手机号~">
            </div>
        </div>
        <br/>
        <div>
            <table id="list"></table>
        </div>
        <div id="pager"></div>
    </div>
</div>
