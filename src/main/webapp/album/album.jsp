<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="application/javascript">

    $(function () {
        $("#list").jqGrid({
            url: "${path}/album/showAll",
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
            editurl: "${path}/album/edit",
            //自适应
            autowidth: true,
            //列名
            colNames: ["ID", "名称", "路径", "状态", "上传时间", "描述"],
            height: '100%',
            //是否显示总条数
            viewrecords: true,
            closeAfterEdit: true,
            closeAfterAdd: true,
            //显示行号
            rownumbers: true,
            colNames: ['ID', '名字', '封面', '评分', '作者', '播音员', '集数', '简要', '时间'],
            colModel: [
                {name: "id", hidden: true, align: "center"},
                {name: "title", editable: true, align: "center"},
                {
                    name: "cover_img",
                    id: "cover_img",
                    editable: true,
                    edittype: "file",
                    align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${path}/album/cover/" + cellvalue + "' height='50px' width='100px'/>"
                    }
                },
                {name: "score", align: "center", editable: true},
                {name: "author", align: "center", editable: true},
                {name: "broadcast", align: "center", editable: true},
                {name: "number", align: "center"},
                {name: "content", align: "center", editable: true},
                {name: "pub_date", align: "center", formatter: "date", formatoptions: {newformat: 'Y-m-d'}}
            ],
            rowNum: 5,
            rowList: [5, 10, 20, 30],
            pager: '#pager',
            viewrecords: true,
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, row_id) {
                var subgrid_table_id;
                var pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;

                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id + "'></table><div id='" + pager_id + "'></div>");

                $("#" + subgrid_table_id).jqGrid({
                    url: "${path}/chapter/showAll?album_id="+row_id,
                    datatype: "json",
                    rowNum: 5,
                    pager: "#" + pager_id,
                    height: '100%',
                    rowList: [5, 10, 20, 30],
                    rownumbers: true,
                    viewrecords: true,
                    styleUI: "Bootstrap",
                    editurl: "${path}/chapter/edit?album_id="+row_id,
                    colNames: ['编号', '标题', '音频', '大小', '时长', '上传时间', '专辑ID','操作'],
                    colModel: [
                        {name: "id", align: "center"},
                        {name: "title", align: "center",editable: true},
                        {name: "url", align: "center",editable: true, edittype: "file",
                            editoptions:{enctype:"multipart/from-data"}
                        },
                        {name: "newsize", align: "center"},
                        {name: "duration", align: "center"},
                        {name: "up_date", align: "center", formatter: "date", formatoptions: {newformat: 'Y-m-d'}},
                        {name: "album_id", hidden: true},
                        {
                            name: "caozuo",
                            formatter: function (cellvalue, options, rowObject) {
                                return "<a href='#' onclick='player(\""+rowObject.url+"\")'><span class='glyphicon glyphicon-play-circle'></span></a>&nbsp;" +
                                    "<a href='${path}/chapter/download?value=att&url="+rowObject.url+"'><span class='glyphicon glyphicon-download'></span></a>";
                            }
                        }
                    ],
                });
                $("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {edit: false,add: true,addtext:"添加",del: true},
                    {},
                    {
                        afterSubmit: function (uuid) {
                            $.ajaxFileUpload({
                                url: "${path}/chapter/upload",
                                type: "post",
                                datatype: "json",
                                fileElementId: "url",
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
            },
            subGridRowColapsed: function (subgrid_id, row_id) {

            }
        });
        $("#list").jqGrid('navGrid', '#pager', {add: true,addtext:"添加",edit: true,edittext:"编辑",del: false},
            {
                closeAfterEdit:true,
                beforeShowForm:function(data){
                    data.find("#cover_img").attr("disabled",true)//禁用input框
                }
            },
            {
                afterSubmit: function (uuid) {
                    $.ajaxFileUpload({
                        url: "${path}/album/upload",
                        type: "post",
                        datatype: "json",
                        fileElementId: "cover_img",
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

    //在线播放
    function player(filename) {
        $("#inline").modal("show");
        $("#player").prop("src","${path}/audios/"+filename);
    }
    //按钮添加专辑
    //添加操作
    $("#add").click(function () {
        $("#list").jqGrid('editGridRow', "new", {
            afterSubmit: function (uuid) {
                $.ajaxFileUpload({
                    url: "${path}/album/upload",
                    type: "post",
                    datatype: "json",
                    fileElementId: "cover_img",
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
                closeAfterEdit:true,
                beforeShowForm:function(data){
                    data.find("#cover_img").attr("disabled",true)//禁用input框
                }
            });
        } else{
            alert("请选择要修改的数据!");
        }
    });
</script>
<div class="col-md-12">
    <div class="panel panel-success">
        <div class="panel-heading"><h4>专辑信息</h4></div>
    </div>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">专辑管理</a></li>
    </ul>
    <div class="panel panel-default">
        <br/>
        <div>
            &nbsp; &nbsp;
            <button type="button" class="btn btn-success" id="add">添加专辑</button>
            <button type="button" class="btn btn-info" id="edit">修改专辑</button>
        </div>
        <br/>
        <div>
            <table id="list"></table>
        </div>
        <div id="pager"></div>
    </div>
</div>
<div class="modal fade" tabindex="-1" id="inline">
    <div class="modal-dialog" role="document">
        <audio id="player" src="" controls></audio>
    </div>
</div>