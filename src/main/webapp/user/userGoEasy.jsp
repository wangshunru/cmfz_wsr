<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script type="application/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.ajax({
            url: "${path}/user/showByMonth",
            type: "post",
            datatype: "json",
            success: function (data) {
                var series1 = [];
                //从Map中获取性别
                var sexs = data.sex;
                var months = data.month;
                var girl = data.girls;
                var boy = data.boys;
                for (var i = 0; i < sexs.length; i++) {
                    //性别
                    var d = sexs[i];
                    if (d == "girls") {
                        series1.push({
                            name: d,
                            type: 'bar',  //统计图形状
                            data: girl  //数据
                        })
                    } else {
                        series1.push({
                            name: d,
                            type: 'bar',  //统计图形状
                            data: boy  //数据
                        })
                    }
                }

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '用户信息展示',  //标题
                        link: "${path}/index.jsp",
                        show: true
                    },
                    tooltip: {},  //鼠标提示
                    legend: {
                        data: sexs  //分类
                    },
                    xAxis: {  //横坐标
                        data: months
                    },
                    yAxis: {},  //纵坐标
                    series: series1
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        })

        var series = [];
        var goEasy = new GoEasy({
            appkey: "BC-a1c18ea4094a4f95afcb0c9bbb462176"
        });
        goEasy.subscribe({
            channel: "myChannel",
            onMessage: function (message) {
                //将json字符串转为map对象
                var data = JSON.parse(message.content);
                var sexs = data.sex;
                // 指定图表的配置项和数据
                var boy = data.boys;
                var girl = data.girls;
                var month = data.month;
                for (var i = 0; i < sexs.length; i++) {
                    //性别
                    var d = sexs[i];
                    if (d == "girls") {
                        series.push({
                            name: d,
                            type: 'bar',  //统计图形状
                            data: girl  //数据
                        })
                    } else {
                        series.push({
                            name: d,
                            type: 'bar',  //统计图形状
                            data: boy  //数据
                        })
                    }
                }
                var option = {
                    title: {
                        text: '用户每月注册量展示', //标题
                        link: "${path}/main/main.jsp",
                        target: "self",
                    },
                    tooltip: {},  //鼠标的提示
                    legend: {
                        data: sexs  //选项
                    },
                    xAxis: {
                        data: month  //横坐标
                    },
                    yAxis: {},  //纵坐标   自适应
                    series: series
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });

    })
</script>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
