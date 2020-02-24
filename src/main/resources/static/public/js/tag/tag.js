$(function(){
    let option = {
        url: '/sys/tag/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        striped : true,     //设置为true会有隔行变色效果
        //idField: 'menuId',
        columns: [
            {
                field: 'id',
                title: '序号',
                width: 40,
                formatter: function(value, row, index) {
                    let pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    let pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                },
                sortable: true
            },
            {checkbox:true},
            { title: 'TagID', field: 'id',sortable:true},
            { title: '标签名称', field: 'name'},
            { title: '热度', field: 'clickCount'}
        ]};
    $('#table').bootstrapTable(option);
});

let vm = new Vue({
	el:'#dtapp',
    data:{
        showList: true,
        title: null,
        tag:{}
    },
    methods:{
        del: function(){
            let rows = getSelectedRows();
            if(rows == null){
                layer.alert("请至少选择一条数据");
                return ;
            }
            let id = 'id';
            //提示确认框
            layer.confirm('您确定要删除所选数据吗？', {
                btn: ['确定', '取消'] //可以无限个按钮
            }, function(index, layero){
                let ids = new Array();
                //遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function(i, row) {
                    ids[i] = row[id];
                });

                $.ajax({
                    type: "POST",
                    url: "/sys/tag/del",
                    data: JSON.stringify(ids),
                    success : function(r) {
                        if(r.code === 200){
                            layer.alert(r.msg);
                            $('#table').bootstrapTable('refresh');
                        }else{
                            layer.alert(r.msg);
                        }
                    },
                    error : function() {
                        layer.alert('服务器没有返回数据，可能服务器忙，请重试');
                    }
                });
            });
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
        },
        update: function (event) {
            let row = getSelectedRow();
            vm.showList = false;
            vm.title = "修改";
            vm.tag = row;
        },
        saveOrUpdate: function (event) {
            let url = vm.tag.id == null ? "/sys/tag/add" : "/sys/tag/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.tag),
                success: function(r){
                    if(r.code === 200){
                        layer.alert(r.msg, function(index){
                            layer.close(index);
                            vm.reload();
                        });
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            $("#table").bootstrapTable('refresh');
        }
    }
});