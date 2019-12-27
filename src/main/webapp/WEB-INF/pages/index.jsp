<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/statics/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/statics/easyui/jquery.easyui.min.js"></script>
    <script>
        $(function () {
            $("#treeMenu").tree({
                url:'userMenus',
                onClick:function (node) {
                    addTabs(node.text,node.attributes);
                }
            });
        })
        function addTabs(title,attributes){
            //如果已经存在选项卡，则选择已经存在选项卡，而不新产生一个
            if($("#tabs").tabs('exists',title)){
                $("#tabs").tabs('select',title);
            }else{
                //否则新产生一个新的选项卡
                $("#tabs").tabs('add',{
                    title:title,
                    closable:true,
                    content:'<iframe src="'+'${pageContext.request.contextPath}/'+attributes.url+'" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
                    //href:url
                });
            }
        }
    </script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',title:'Nor Title',split:true" style="height:100px;"></div>
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'West',split:true" style="width:150px;">
        <ul id="treeMenu" class="easyui-tree"></ul>
    </div>
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
        <div class="easyui-tabs" style="fit:true; border: false" id="tabs">
    </div>
</body>
</html>
