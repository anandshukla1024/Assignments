var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "3",
        "ok": "3",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "21",
        "ok": "21",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1055",
        "ok": "1055",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "412",
        "ok": "412",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "458",
        "ok": "458",
        "ko": "-"
    },
    "percentiles1": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "percentiles2": {
        "total": "608",
        "ok": "608",
        "ko": "-"
    },
    "percentiles3": {
        "total": "966",
        "ok": "966",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1037",
        "ok": "1037",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 2,
        "percentage": 67
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1,
        "percentage": 33
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.143",
        "ok": "0.143",
        "ko": "-"
    }
},
contents: {
"req_get-all-product-3ac86": {
        type: "REQUEST",
        name: "Get all Product Details --> 1st call",
path: "Get all Product Details --> 1st call",
pathFormatted: "req_get-all-product-3ac86",
stats: {
    "name": "Get all Product Details --> 1st call",
    "numberOfRequests": {
        "total": "2",
        "ok": "2",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "21",
        "ok": "21",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1055",
        "ok": "1055",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "538",
        "ok": "538",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "517",
        "ok": "517",
        "ko": "-"
    },
    "percentiles1": {
        "total": "538",
        "ok": "538",
        "ko": "-"
    },
    "percentiles2": {
        "total": "797",
        "ok": "797",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1003",
        "ok": "1003",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1045",
        "ok": "1045",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1,
        "percentage": 50
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 1,
        "percentage": 50
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.095",
        "ok": "0.095",
        "ko": "-"
    }
}
    },"req_get-prduct-deta-08f14": {
        type: "REQUEST",
        name: "Get Prduct Details by Id",
path: "Get Prduct Details by Id",
pathFormatted: "req_get-prduct-deta-08f14",
stats: {
    "name": "Get Prduct Details by Id",
    "numberOfRequests": {
        "total": "1",
        "ok": "1",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "0",
        "ko": "-"
    },
    "percentiles1": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "percentiles2": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "percentiles3": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "percentiles4": {
        "total": "161",
        "ok": "161",
        "ko": "-"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 1,
        "percentage": 100
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 0,
        "percentage": 0
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "0.048",
        "ok": "0.048",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
