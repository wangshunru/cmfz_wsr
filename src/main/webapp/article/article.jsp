<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>

<script>
    KindEditor.create('#editor_id',{
        uploadJson:"${path}/article/upload",
        filePostName:"upload", //与上传文件的controller 参数名一致 默认imgFile
        allowFileManager:true,
        fileManagerJson:"${path}/article/showAllPicture",
        afterBlur:function (){
            this.sync();  //将编辑器中的内容同步到表单
        }
    });

</script>

<script type="application/javascript">
    $(function () {
        $("#list").jqGrid({
            url: "${path}/article/showAll",
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
            editurl: "${path}/article/edit",
            //自适应
            autowidth: true,
            //列名
            colNames: ["ID", "封面", "标题", '内容', '创建时间','上传时间', "状态", '上师ID', "操作"],
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
                    name: "insert_img",
                    editable: true,
                    edittype: "file",
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${path}/article/insert_img/" + cellvalue + "' height='50px' width='100px'/>"
                    }
                },
                {name: "title", editable: true, align: "center"},
                {name: "content", hidden: true, align: "center"},
                {name: "up_date", align: "center", formatter: "date", formatoptions: {newformat: 'Y-m-d'}},
                {name: "up_date", align: "center", formatter: "date", formatoptions: {newformat: 'Y-m-d'}},
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
                {name: "user_id", editable: true, align: "center"},
                {
                    name: "caozuo", align: "center", formatter: function (cellvalue, options, rowObject) {
                    return "<a href='#'><span class='glyphicon glyphicon-th-list'></span></a>"
                }
                }
            ]
        }).jqGrid("navGrid", "#pager", {add: false, edit: false, del: true, search: false}
        );
    })

    function update(id) {
        $.ajax({
            url: "${path}/article/status",
            type: "post",
            datatype: "json",
            data: "id=" + id,
            success: function () {
                $("#list").trigger("reloadGrid")
            }
        })
    };

    $("#edit").click(function () {
        var gr = $("#list").jqGrid('getGridParam', 'selrow');
        if (gr != null){
            var row= $("#list").jqGrid("getRowData",gr);
            //展示模态框
            $("#showArticle").modal("show");
            //设置值
            $("#title").val(row.title);
            //给KindEditor 框设置内容
            KindEditor.html("#editor_id",row.content);
            $("#footer").html(
                "<button type='button' class='btn btn-primary' onclick='editArticle("+row.id+")'>提交</button>"+
                "<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>"
            )
        } else{
            alert("请选择要修改的数据!");
        }
    });

    function editArticle(rowId) {
        //修改
        $.ajax({
            url:"${path}/article/edit?id="+rowId,
            type:"post",
            datatype:"json",
            data:$("#updateForm").serialize(),
            success:function () {
                //关闭模态框
                $("#showArticle").modal("hide");
                //刷新页面
                $("#list").trigger("reloadGrid")
            }
        })
    }

    $("#add").click(function () {
        //给表单置空
        $("#updateForm")[0].reset();
        //给KindEditor框置空
        KindEditor.html("#editor_id","");
        $("#showArticle").modal("show");
        $("#articleImg").html(
            "<span class='input-group-addon' id='basic-addon2'>头像</span>"+
            "<input type='file' class='form-control' name='coverImg' id='insert_img'>"
        )
        $("#footer").html(
            "<button type='button' class='btn btn-primary' onclick='addArticle()'>提交</button>"+
            "<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>"
        )
    })
    //添加文章
    function addArticle() {
        var title = $("#title").val();
        var coverImg = $("#insert_img").val()
        var content = $("#editor_id").val()
        $.ajax({
            url:"${path}/article/add",
            type:"post",
            datatype:"json",
            data:{"title":title,"insert_img":coverImg,"content":content},
            success:function () {
                //关闭模态框
                $("#showArticle").modal("hide");
                //刷新页面
                $("#list").trigger("reloadGrid")
            }
        })
    }


</script>


<div class="col-md-12">
    <div class="panel panel-default">
        <div class="panel-heading"><h4>文章管理</h4></div>
    </div>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">文章信息</a></li>
    </ul>
    <div class="panel panel-default">
        <br/>
        <div>
            &nbsp; &nbsp;
            <button type="button" class="btn btn-success" id="edit">文章信息</button>
            <button type="button" class="btn btn-info" id="add">添加文章</button>
            <button type="button" class="btn btn-warning" id="del">删除文章</button>
        </div>
        <br/>
        <div>
            <table id="list"></table>
        </div>
        <div id="pager"></div>
    </div>
</div>

<div class="modal fade" role="dialog" id="showArticle">
    <div class="modal-dialog"  style="width:730px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">文章信息</h4>
            </div>
            <div class="modal-body">
                <form id="updateForm" method="post" enctype="multipart/form-data" action="${path}/article/add">
                    <div class="input-group" id="article">
                        <span class="input-group-addon" id="basic-addon1">标题</span>
                        <input type="text" class="form-control" name="title" id="title">
                    </div>
                    <br/>
                    <div class="input-group" id="articleImg">

                    </div>
                    <br/>
                    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                    </textarea>
                </form>
            </div>
            <div class="modal-footer" id="footer">
                <button type="button" class="btn btn-primary" id="showEdit">提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


