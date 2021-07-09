<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="col-md-9">

    <div class="data_list">
        <div class="data_list_title"><span class="glyphicon glyphicon-signal"></span>&nbsp;Report Statistic </div>
        <div class="container-fluid">
            <div class="row" style="padding-top: 20px;">
                <div class="col-md-12">
                    <!-- container for charts -->
                    <div id="monthChart" style="height: 500px"></div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- import echarts -->
<script type="text/javascript" src="statics/echarts/echarts.min.js"></script>
<script type="text/javascript">

    $.ajax({
        type: "get",
        url: "report",
        data: {
            actionName: "month"
        },
        success: function (result) {
            console.log(result);
            if (result.code == 1) {
                // get month
                var monthArray = result.result.monthArray;
                // get data
                var dataArray = result.result.dataArray;
                loadMonthChart(monthArray, dataArray)
            }
        }
    });

    function loadMonthChart(monthArray, dataArray) {
        var myChart = echarts.init(document.getElementById('monthChart'));

        var dataAxis = monthArray;
        var data = dataArray
        var yMax = 30;
        var dataShadow = [];

        for (var i = 0; i < data.length; i++) {
            dataShadow.push(yMax);
        }

        option = {
            title: {
                text: 'Monthly statistics',
                subtext: 'Note count grouped by month',
                left: 'center'
            },
            tooltip: {},
            // legend: {
            //     data: ['Month'],
            // },
            xAxis: {
                data: dataAxis,
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                }
            },
            yAxis: {
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#999'
                    }
                }
            },
            series: [
                {
                    type: 'bar',
                    data: data,
                    // name: 'Month',
                    showBackground: true,
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [
                                {offset: 0, color: '#83bff6'},
                                {offset: 0.5, color: '#188df0'},
                                {offset: 1, color: '#188df0'}
                            ]
                        )
                    },
                    emphasis: {
                        itemStyle: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#2378f7'},
                                    {offset: 0.7, color: '#2378f7'},
                                    {offset: 1, color: '#83bff6'}
                                ]
                            )
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
    }
</script>