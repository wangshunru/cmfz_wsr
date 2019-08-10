<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
    <script src="${path}/jquery/echarts.min.js"></script>
    <script src="${path}/jquery/china.js"></script>


</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><strong>池名法洲管理系统</strong></a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">欢迎&nbsp; <span style="color: #2aabd2">${sessionScope.admin.username}</span></a></li>
            <li><a href="${path}/admin/exit">退出登陆
                <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
            </a></li>
        </ul>
    </div>
</nav>
<!--栅格系统-->
<div class="row">
    <div class="col-sm-2">
        <!--左边手风琴部分-->
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" align="center">
            <div class="panel panel-info">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                           aria-expanded="true" aria-controls="collapseOne">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li>
                                <button type="button" class="btn btn-warning"><a href="javascript:$('#xxx').load('${path}/user/user.jsp')">用户信息</a></button>
                            </li>
                            <br/>
                            <li>
                                <button type="button" class="btn btn-warning"><a href="javascript:$('#xxx').load('${path}/user/userGoEasy.jsp')">用户统计</a></button>
                            </li>
                            <br/>
                            <li>
                                <button type="button" class="btn btn-warning"><a href="javascript:$('#xxx').load('${path}/user/userMap.jsp')">用户分布</a>
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="panel panel-success">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li>
                                <button type="button" class="btn btn-success"><a
                                        href="javascript:$('#xxx').load('${path}/banner/banner.jsp')">轮播图信息</a></button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="panel panel-warning">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li>
                                <button type="button" class="btn btn-warning"><a href="javascript:$('#xxx').load('${path}/album/album.jsp')">专辑信息</a></button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="panel panel-danger">
                <div class="panel-heading" role="tab" id="headingFore">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseFore" aria-expanded="false" aria-controls="collapseTwo">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFore" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFore">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li>
                                <button type="button" class="btn btn-danger"><a href="javascript:$('#xxx').load('${path}/article/article.jsp')">文章信息</a></button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="panel panel-primary">
                <div class="panel-heading" role="tab" id="headingFive">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseFive" aria-expanded="false" aria-controls="collapseTwo">
                            上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li>
                                <button type="button" class="btn btn-warning"><a href="#">上师状态修改</a></button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="panel panel-info">
                <div class="panel-heading" role="tab" id="headingSix">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseSix" aria-expanded="false" aria-controls="collapseTwo">
                            管理员管理
                        </a>
                    </h4>
                </div>
                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            <li>
                                <button type="button" class="btn btn-warning"><a href="#">换账号登陆</a></button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-10" id="xxx">
        <!--巨幕开始-->
        <div class="col-sm-12">
            <div class="jumbotron">
                <div class="container">
                    <h3>&nbsp;&nbsp;&nbsp;欢迎来到池名法洲后台管理系统</h3>
                </div>
            </div>
        </div>
        <!--右边轮播图部分-->
        <div class="col-sm-12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="${path}/bootstrap/img/shouye.jpg" alt="...">
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/shouye.jpg" alt="...">
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/shouye.jpg" alt="...">
                    </div>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="col-sm-12">
    <div style="height: 40px">
        <br/>
        <div style="text-align: center;font-size:15px">@百知教育 wsr@zparkhr.com.cn</div>
    </div>
</div>
</body>
</html>
