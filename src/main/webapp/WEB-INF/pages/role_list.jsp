<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/statics/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/statics/easyui/jquery.easyui.min.js"></script>
    <script>
        $(function (){
            $("#roledg").datagrid({
                url:'role.html?act=list',
                fitColumns:true,
                singleSelect:true,
                columns:[[
                    {field:'id',title:'编号'},
                    {field:'name',title:'名称'},
                    {field:'bz',title:'描述'},
                    {field:'remarks',title:'标记'}
                ]]
            });
        })
        function doMenuAssign(){
            var row=$("#roledg").datagrid("getSelected");
            $("#role-menu-tree").tree({
                url:'role.html?act=menu_list&roleId='+row.id,
                checkbox:true,
                onLoadSuccess:function (node,data) {
                    $("#role-menu-dialog").dialog("open");
                }
            });
        }
        function submitRoleMenu(){
            var row=$("#roledg").datagrid("getSelected");
            var roleId=row.id;
            var ids=[];
            var nodes =$('#role-menu-tree').tree('getChecked', ['checked','indeterminate']);
            $.each(nodes,function(key,val){
                ids.push(val.id);
            });
            var mIds=ids.join(',');
            $.post("role.html?act=assignMenu",{"roleId":roleId,"menuIds":mIds},function(data){
                if(data.msg=="success"){
                    $("#role-menu-dialog").dialog("close");
                }

            },"json");
        }
    </script>
</head>
<body>
    <div id="role-tool">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">修改</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="doMenuAssign()">分配权限</a>
    </div>
    <table id="roledg"></table>
    <div id="role-menu-dialog" class="easyui-dialog" title="分配权限" style="width: 400px;height: 300px" closed="true" modal="true" data-options="buttons:'#role-menu-buttons'">
        <ul id="role-menu-tree" class="easyui-tree"></ul>
    </div>
    <div id="role-menu-buttons" style="text-align: right">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitRoleMenu()">提交</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#role-menu-dialog').dialog('close')">取消</a>
    </div>
</body>
</html>
