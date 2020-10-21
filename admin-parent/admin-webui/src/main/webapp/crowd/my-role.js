// 执行分页，生成页面效果，任何时候调用这个函数都会重新加载页面
function generatePage() {

    // 1.获取分页数据
    var pageInfo = getPageInfoRemote();

    // 2.填充表格
    fillTableBody(pageInfo);
}

// 远程访问服务器端程序，获取pageInfo数据
function getPageInfoRemote() {

    // 调用$.ajax()函数发送请求并接收该函数的返回值
    var ajaxResult = $.ajax({
        "url" : "role/get/page/info.json",
        "type" : "post",
        "data" : {
            "pageNum" : window.pageNum,
            "pageSize" : window.pageSize,
            "keyword" : window.keyword
        },
        "dataType" : "json",
        "async" : false
    });

    // console.log(ajaxResult);
    var statusCode = ajaxResult.status;

    // 如果发生错误，显示提示消息
    if (statusCode != 200){
        layer.msg("服务器程序调用失败，状态码：" + statusCode + ",说明信息：" + ajaxResult.statusText);
        return null;
    }

    // 如果响应状态码为200，说明请求处理成功，获取pageInfo
    var resultEntity = ajaxResult.responseJSON;

    // 从resultEntity中获取result属性
    var result = resultEntity.result;

    // 判断result是否成功
    if (result == "FAILED"){
        layer.msg(resultEntity.message);
        return null;
    }

    // 确认result为成功后获取pageInfo
    var pageInfo = resultEntity.data;
    return pageInfo;

}

// 填充表格
function fillTableBody(pageInfo) {

    // 清除旧数据
    $("#rolePageBody").empty();

    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0){
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉！没有查询到数据</td></tr>")
        return;
    }

    // 使用pageInfo中的list填充body
    for (var i = 0; i < pageInfo.list.length; i++) {
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;

        var numberTd = "<td>"+ roleId +"</td>";
        var checkboxTd = "<td><input type='checkbox'></td>";
        var nameTd = "<td>" + roleName + "</td>";

        var checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button type='button' class='btn btn-primary btn-xs'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button type='button' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";
        var btnTd = "<td>"+ checkBtn + " " + pencilBtn + " " + removeBtn +"</td>"

        var tr = "<tr>"+ numberTd + checkboxTd + nameTd + btnTd+"</tr>";

        $("#rolePageBody").append(tr);
    }

    generateNavigator(pageInfo);
}

// 生成分页页码导航条
function generateNavigator(pageInfo) {
    var totalRecord = pageInfo.total;

    var properties = {
        "num_edge_entries": 3,                                // 边缘页数
        "num_display_entries": 5,                             // 主体页数
        "callback": paginationCallBack,                       // 回调函数
        "items_per_page":pageInfo.pageSize,                   // 每页显示几项
        "current_page":pageInfo.pageNum-1,                      // 当前页数
        "prev_text":"上一页",
        "next_text":"下一页"
    };

    // 调用pagination函数
    $("#Pagination").pagination(totalRecord,properties);
}

// 翻页时的回调函数
function paginationCallBack(pageIndex,jQuery) {

    window.pageNum = pageIndex + 1;

    // 调用分页函数
    generatePage();

    // 取消超链接的跳转
    return false;
}