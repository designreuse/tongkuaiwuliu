/**
 * Created by wuhaibin on 15/8/22.
 */
var input;//总利润
var uuu;
var output;
var outputOfSalary;
var outputOfOil;
var profit;
var inputOfTask;
var outputOfGoodsBuy;
var profit;
var inputOfCarry;
var month;
var year;
$(document).ready(function () {
    var submitBtn = $("#submit");

    submitBtn.click(function () {

        //通过传参数来获取具体年月的数据  返回到饼图中；未实现.
        getChartData();
    });

    var myDate = new Date();
    var c_year = myDate.getFullYear();
    var c_month = myDate.getMonth();

    $("#Year").val(c_year);
    $("#Month").val(c_month + 1);

    getChartData();

    function getChartData() {
        month = $("#Month").val();
        year = $("#Year").val();
        $.ajax({
            url: contextPath + "/report/month",
            type: "get",
            data: {
                "month": month, "year": year
            },
            success: function (resp) {
                console.log(resp);
                uuu = 20;
                input = resp.input;
                output = resp.output;
                outputOfOil = resp.outputOfOil;
                outputOfGoodsBuy = resp.outputOfGoodsBuy;
                outputOfSalary = resp.outputOfSalary;
                profit = resp.profit;
                inputOfCarry = resp.inputOfCarry;
                inputOfTask = resp.inputOfTask;
                console.log(output);
                drawChart_T();
                drawChart_O();
                drawChart_B();
            }
        })
    }

    function drawChart_T() {
        var chart = iChart.create({
            render: "ichart-render-T",
            width: 800,
            height: 400,
            background_color: "#fefefe",
            gradient: false,
            color_factor: 0.2,
            border: {
                color: "BCBCBC",
                width: 1
            },
            align: "center",
            offsetx: 0,
            offsety: 0,
            sub_option: {
                border: {
                    color: "#BCBCBC",
                    width: 1
                },
                label: {
                    fontweight: 500,
                    fontsize: 11,
                    color: "#4572a7",
                    sign: "square",
                    sign_size: 12,
                    border: {
                        color: "#BCBCBC",
                        width: 1
                    },
                    background_color: "#fefefe"
                }
            },
            shadow: true,
            shadow_color: "#666666",
            shadow_blur: 2,
            showpercent: false,
            column_width: "70%",
            bar_height: "70%",
            radius: "90%",
            title: {
                text: year + "年 " + "痛快 " + month + "月 各部分收入一览表",
                color: "#111111",
                fontsize: 20,
                font: "微软雅黑",
                textAlign: "center",
                height: 30,
                offsetx: 0,
                offsety: 0
            },
            subtitle: {
                text: "",
                color: "#111111",
                fontsize: 16,
                font: "微软雅黑",
                textAlign: "center",
                height: 20,
                offsetx: 0,
                offsety: 0
            },
            footnote: {
                text: "",
                color: "#111111",
                fontsize: 12,
                font: "微软雅黑",
                textAlign: "right",
                height: 20,
                offsetx: 0,
                offsety: 0
            },
            legend: {
                enable: false,
                background_color: "#fefefe",
                color: "#333333",
                fontsize: 12,
                border: {
                    color: "#BCBCBC",
                    width: 1
                },
                column: 1,
                align: "right",
                valign: "center",
                offsetx: 0,
                offsety: 0
            },
            coordinate: {
                width: "80%",
                height: "84%",
                background_color: "#ffffff",
                axis: {
                    color: "#a5acb8",
                    width: [1, "", 1, ""]
                },
                grid_color: "#d9d9d9",
                label: {
                    fontweight: 500,
                    color: "#666666",
                    fontsize: 11
                }
            },
            label: {
                fontweight: 500,
                color: "#666666",
                fontsize: 11
            },
            type: "pie2d",
            data: [
                {
                    name: "公司运营收入",
                    value: inputOfTask,
                    color: "#4572a7"
                }, {
                    name: "公司外包承运收入",
                    value: inputOfCarry,
                    color: "#aa4643"
                }
            ]
        });

        chart.draw();
    }

    function drawChart_O() {
        var chart = iChart.create({
            render: "ichart-render-O",
            width: 800,
            height: 400,
            background_color: "#fefefe",
            gradient: false,
            color_factor: 0.2,
            border: {
                color: "BCBCBC",
                width: 1
            },
            align: "center",
            offsetx: 0,
            offsety: 0,
            sub_option: {
                border: {
                    color: "#BCBCBC",
                    width: 1
                },
                label: {
                    fontweight: 500,
                    fontsize: 11,
                    color: "#4572a7",
                    sign: "square",
                    sign_size: 12,
                    border: {
                        color: "#BCBCBC",
                        width: 1
                    },
                    background_color: "#fefefe"
                }
            },
            shadow: true,
            shadow_color: "#666666",
            shadow_blur: 2,
            showpercent: false,
            column_width: "70%",
            bar_height: "70%",
            radius: "90%",
            title: {
                text: year + "年" + "痛快" + month + "月各部分支出一览表",
                color: "#111111",
                fontsize: 20,
                font: "微软雅黑",
                textAlign: "center",
                height: 30,
                offsetx: 0,
                offsety: 0
            },
            subtitle: {
                text: "",
                color: "#111111",
                fontsize: 16,
                font: "微软雅黑",
                textAlign: "center",
                height: 20,
                offsetx: 0,
                offsety: 0
            },
            footnote: {
                text: "",
                color: "#111111",
                fontsize: 12,
                font: "微软雅黑",
                textAlign: "right",
                height: 20,
                offsetx: 0,
                offsety: 0
            },
            legend: {
                enable: false,
                background_color: "#fefefe",
                color: "#333333",
                fontsize: 12,
                border: {
                    color: "#BCBCBC",
                    width: 1
                },
                column: 1,
                align: "right",
                valign: "center",
                offsetx: 0,
                offsety: 0
            },
            coordinate: {
                width: "80%",
                height: "84%",
                background_color: "#ffffff",
                axis: {
                    color: "#a5acb8",
                    width: [1, "", 1, ""]
                },
                grid_color: "#d9d9d9",
                label: {
                    fontweight: 500,
                    color: "#666666",
                    fontsize: 11
                }
            },
            label: {
                fontweight: 500,
                color: "#666666",
                fontsize: 11
            },
            type: "pie2d",
            data: [
                {
                    name: "物资购买支出",
                    value: outputOfGoodsBuy,
                    color: "#aa4643"
                }, {
                    name: "公司员工支出",
                    value: outputOfSalary,
                    color: "#89a54e"
                }, {
                    name: "燃油支出",
                    value: outputOfOil,
                    color: "#28b39e"
                }
            ]
        });

        chart.draw();
    }

    function drawChart_B() {
        var chart = iChart.create({
            render: "ichart-render-B",
            width: 800,
            height: 400,
            background_color: "#2e3b4e",
            gradient: false,
            color_factor: 0.2,
            border: {
                color: "#404c5d",
                width: 1
            },
            align: "center",
            offsetx: 0,
            offsety: -20,
            sub_option: {
                border: {
                    color: "#fefefe",
                    width: 1
                },
                label: {
                    fontweight: 600,
                    fontsize: 20,
                    color: "#f5f5f5",
                    sign: "square",
                    sign_size: 12,
                    border: {
                        color: "#BCBCBC",
                        width: 1
                    },
                    background_color: "#fefefe"
                }
            },
            shadow: true,
            shadow_color: "#fafafa",
            shadow_blur: 10,
            showpercent: false,
            column_width: "70%",
            bar_height: "70%",
            radius: "90%",
            title: {
                text: year + "年" + month + "月痛快",
                color: "#f5f5f5",
                fontsize: 24,
                font: "Verdana",
                textAlign: "left",
                height: 30,
                offsetx: 36,
                offsety: 0
            },
            legend: {
                enable: true,
                background_color: "rgba(254,254,254,0.2)",
                color: "#c1cdde",
                fontsize: 13,
                border: {
                    color: "#85898f",
                    width: 0
                },
                column: 5,
                align: "right",
                valign: "top",
                offsetx: -32,
                offsety: -40
            },
            coordinate: {
                width: "92%",
                height: "80%",
                background_color: "rgba(246,246,246,0.05)",
                axis: {
                    color: "#bfbfc3",
                    width: ["", "", 6, ""]
                },
                grid_color: "#c0c0c0",
                label: {
                    fontweight: 500,
                    color: "#f5f5f5",
                    fontsize: 0
                }
            },
            label: {
                fontweight: 600,
                color: "#f5f5f5",
                fontsize: 18
            },
            type: "column2d",
            data: [
                {
                    name: "总收入",
                    value: input,
                    color: "rgba(131,166,213,0.9)"
                }, {
                    name: "总支出.",
                    value: output,
                    color: "rgba(243,125,178,0.9)"
                }, {
                    name: "总利润",
                    value: profit,
                    color: "rgba(237,236,238,0.9)"
                }
            ]
        });

        chart.draw();
    }
});

