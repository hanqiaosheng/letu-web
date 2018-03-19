<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="assets/highcharts.js"></script>
<script type="text/javascript">
    var nowdays = new Date();//获取今天日期
    var year = nowdays.getFullYear();
    var month = nowdays.getMonth();
    if(month==0)
    {
        month=12;
        year=year-1;
    }
    if (month < 10) {
        month = "0" + month;
    }

    var myDate = new Date(year, month, 0);
    var lastDay = month + "-" + myDate.getDate();//上个月的最后一天

    var dateArray = [];
    var timeArray = [];

    var dateTemp;
    var dateTemp1;
    var flag = 1;
    var arr = ${arr};
    for (var i = 0; i < 7; i++) {
        if((nowdays.getDate()- i)<0){
            dateTemp1 = month + "-" + (myDate.getDate()-(7-i));
            timeArray.push(dateTemp1);
        }else if((nowdays.getDate()- i)==0){
            dateTemp = lastDay;
            dateArray.push(dateTemp);
        }else{
            dateTemp = (nowdays.getMonth()+1)+"-"+(nowdays.getDate()- i);
            dateArray.push(dateTemp);
        }
    }
    dateArray = dateArray.concat(timeArray.reverse()).reverse()
</script>
<body>
<%@include file="common/body.jsp"%>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">骑行统计</h1>
        </div>
        <!-- /.col-lg-12 -->
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <div class="col-xs-3">
                                <i class="fa fa-comments fa-5x"></i>
                                <div class="huge animate-num" data-num="">26780</div>
                                <div>骑行用户总量</div>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge animate-num" data-num="">10</div>
                                <div>新增骑行用户数</div>
                            </div>
                        </div>

                        <!-- /.row -->
                        <div class="clearfix">
                            <div class="char1"></div>
                            <!--  <div class="char2"></div> -->
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        function charts(obj,style,data){
            obj.highcharts({
                chart: {
                    type: "line",
                    style: {
                        fontSize: "14px",
                        color: "#fff"
                    },
                    backgroundColor: 'rgba(0,0,0,0)'
                },
                title: {
                    text: ""
                },
                subtitle: {
                    text: ""
                },
                legend: {
                    enabled: false
                },
                xAxis: {
                    gridLineColor: '#eee',
                    gridLineWidth: 1,
                    labels: {
                        style: {
                            color: '#7d8795'
                        }
                    },
                    tickLength:5,
                    tickmarkPlacement: 'on',
                    lineColor: '#eee',
                    tickColor: '#eee',
                    categories: data.categories
                },
                yAxis: {
                    allowDecimals: false,
                    gridLineColor: '#eee',
                    gridLineWidth: 1,
                    labels: {
                        style: {
                            color: '#7d8795'
                        }
                    },
                    lineColor: '#eee',
                    tickColor: '#eee',
                    title: {
                        text: ""
                    },
                    floor: 0

                },
                tooltip: {
                    backgroundColor: 'rgba(0,0,0,.5)',
                    borderWidth:0,
                    shadow:false,
                    style: {
                        color: '#fff',
                        padding: '4px'
                    },
                    formatter: function() {
                        console.log(this.y)
                        return "<div>"+(this.y).toFixed(0)+"人</div>"
                    },
                    shared:true
                },
                series: [{
                    type: "area",
                    data: data.series,
                    lineWidth: 1
                }],
                plotOptions: {
                    series: {
                        color: style,
                        fillOpacity: 0.5,
                        marker: {
                            radius: 4
                        },
                        states: {
                            lineWidth: 2,
                            hover: {
                                lineWidth: 1
                            }
                        }
                    },

                }
            });
        }
        charts($(".char1"),'#f89f9f',{
            categories:dateArray,
            series:arr.reverse(),
        })

        // console.log(data.tsub1AnnualizedMoneyCh,data.tAnnualizedMoneyCh)
        /* 	charts($(".char2"),'#9acae6',{
                categories:['11-14','11-15'],
                series:[2,3]
                }) */

    });

</script>
<%@include file="common/buttom.jsp"%>
</body>
</html>