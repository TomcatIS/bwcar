$(function(){
    let option = {
        url: '/sys/menu/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        cache: true, //是否使用缓存，一般需要设置这个属性
        toolbar: '#toolbar',
        striped : true,     //设置为true会有隔行变色效果
        //idField: 'menuId',
        columns: [
            {
                field: 'menuId',
                title: '序号',
                width: 40,
                /*formatter这个属性属于列参数，意思就是对当前列的数据进行格式化操作，它是一个函数，有三个参数，value，row，index,
                value:代表当前单元格中的值，
                row：代表当前行,
                index:代表当前行的下标,*/
                formatter: function(value, row, index) {
                    let pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    let pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    // 计算每个单元格的menuId的值
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {checkbox:true},
            { title: '菜单ID', field: 'menuId',sortable:true},
            {field:'name', title:'菜单名称', formatter: function(value,row){
                    if(row.type === 0){
                        return value;
                    }
                    if(row.type === 1){
                        return '<span style="margin-left: 40px;">├─ ' + value + '</span>';
                    }
                    if(row.type === 2){
                        return '<span style="margin-left: 80px;">├─ ' + value + '</span>';
                    }
                }},
            { title: '上级菜单', field: 'parentName'},
            { title: '菜单图标', field: 'icon', formatter: function(value){
                    return value == null ? '' : '<i class="'+value+' fa-lg"></i>';
                }},
            { title: '菜单URL', field: 'url'},
            { title: '授权标识', field: 'perms'},
            { title: '类型', field: 'type', formatter: function(value){
                    if(value === 0){
                        return '<span class="label label-primary">目录</span>';
                    }
                    if(value === 1){
                        return '<span class="label label-success">菜单</span>';
                    }
                    if(value === 2){
                        return '<span class="label label-warning">按钮</span>';
                    }
                }},
            { title: '排序号', field: 'orderNum'}
        ]};
    $('#table').bootstrapTable(option);
});
let ztree;

let vm = new Vue({
	el:'#dtapp',
    data:{
        showList: true,
        title: null,
        menu:{}
    },
    methods:{
        del: function(){
            let rows = getSelectedRows();
            if (rows == null){
                layer.alert("请至少选择一行数据");
                return;
            }
            layer.confirm("确定删除所选数据吗？", {
                btn: ['确定', '取消'],
                btn1: function(index, layero){
                    let ids = new Array();
                    $.each(rows, function(index, row){
                        ids[index] = row.menuId;
                    });
                    $.ajax({
                        type: 'POST',
                        url: '/sys/menu/del',
                        data: JSON.stringify(ids),
                        success: function(result){
                            if (result.code == 200){
                                layer.alert("删除成功");
                                $("table").bootstrapTable("refresh");
                            }else {
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.menu = {parentName:null,parentId:0,type:1,orderNum:0};
            vm.getMenu();
        },
        update: function (event) {
            let id = "menuId"
            let row = getSelectedRow();
            let menuId = row[id];
            vm.showList = false;
            vm.title = "修改";
            vm.menu = row;
            // 获取树形菜单
            vm.getMenu(menuId);
        },
        saveOrUpdate: function (event) {
            $.ajax({
                type: 'POST',
                url: '/sys/menu/save',
                data: JSON.stringify(vm.menu),
                success: function(result){
                    if (result.code == 200){
                        layer.alert(result.msg, function (index) {
                            layer.close(index);
                            vm.reload();
                        });
                    } else{
                        layer.alert(result.msg);
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            $("#table").bootstrapTable('refresh');
        },
        menuTree: function(){
            layer.open({
                type: 1,
                offset: '50px',//坐标：一个参数表示top坐标
                skin: 'layui-layer-molv',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0.3,//弹层外显示遮罩，有遮罩时弹窗必须先关掉才能点击弹层外的区域
                shadeClose: true,//如果shade不为0，设置为true时，表示点击弹窗外的区域弹窗会关闭
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    let node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.menu.parentId = node[0].menuId;
                    vm.menu.parentName = node[0].name;
                    layer.close(index);
                }
            });
        },
        getMenu: function(menuId){
            let setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "menuId",
                        pIdKey: "parentId",
                        rootPId: -1
                    },
                    key: {
                        url:"nourl"
                    }
                }
            };

            // 加载菜单树
            $.get("/sys/menu/generateZtree", function(r){
                // 生成ztree树
                ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
                // 表示根据menuId获取节点，获取menuId=vm.menu.parentId的节点
                let node = ztree.getNodeByParam("menuId", vm.menu.parentId);
                // 表示点击新增按钮后，默认选中下面这个节点
                ztree.selectNode(node);
                // 表示默认parentName为node.name("一级菜单")
                vm.menu.parentName = node.name;
            });

        }
    }
});