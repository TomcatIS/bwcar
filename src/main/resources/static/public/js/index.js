// 注册菜单组件
Vue.component('menu-item', {
	props: ['item'],
	template: `
		<li>
			<!--type为0表示是0级菜单，内容和样式为下面这个a标签-->
			<a v-if="item.type === 0" href="javascript:;">
				<i v-if="item.icon != null" :class="item.icon"></i>
				<span>{{item.name}}</span>
				<i class="fa fa-angle-left pull-right"></i>
			</a>
			<ul v-if="item.type === 0" class="treeview-menu">
				<menu-item :item="item" v-for="item in item.list"></menu-item>
			</ul>
			<!--type为1表示是1级菜单，内容和样式为下面这个a标签-->
			<!--为href添加一个"#"号，点击a标签只会导致地址栏拼接一个href属性的值，也就是#item.url-->
			<a v-if="item.type === 1" :href="'#'+item.url">
				<i v-if="item.icon != null" :class="item.icon"></i>
				<i v-else class="fa fa-circle-o"></i> 
				{{item.name}}
			</a>
		</li>
	`
});

// iframe自适应高度
$(window).resize(function(){
	var $content = $(".content");
	// 表示$content的高度始终=窗口高度-120
	$content.height($(this).height()-120);
	$content.find("iframe").each(function(){
		// 表示iframe标签的高度=$content的高度
		$(this).height($content.height());
	});
});

// 创建vue实例
var vm = new Vue({
	el:'#dtapp',
	data:{
		user:{},
		menuList: [],
		main: "sys/main.html",
		password: '',
		newPassword: '',
        navTitle: "控制台"
	},
	// getMenuList和getUser函数是通过crated钩子创建的
	// 这两个函数改变了vm的data数据，所以会触发后面的updated钩子函数，每个函数都会触发一次
	methods: {
		getMenuList: function(){
			$.getJSON("/json/menu_user.json", function (data) {
				// 这里不能使用this.menuList
				vm.menuList = data.menuList;
				window.permissions = data.permissions;
			})
		},
		getUser: function(){
			 $.getJSON("/json/user_info.json", function(data){
				vm.user = data.user;
			});
		},
		updatePassword: function(){
			layer.open({
				title: '修改密码',
				type: 1,
				// skin为样式
				skin: 'layui-layer-molv',
				area: ['550px', '270px'],
				content: $("#passwordLayer"),
				shadeClose: false,
				btn: ['修改', '取消'],
				btn1: function(index){
					$.ajax({
						type: 'POST',
						url: '/sys/user/updatePassword',
						data: {'password': vm.password, 'newPassword': vm.newPassword},
						dataType: 'json',
						success: function(result){
							if (result.code == 200){
								layer.close(index);
								layer.alert("修改成功", function(){
									window.location.reload();
								})
							}else{
								layer.alert(result.msg);
							}
						}
					})
				}
			})
		}
	},
	created: function(){
		this.getMenuList();
		this.getUser();
	},
	/*每个菜单是一个a标签，也就是上面vue模板中的a标签。可以看到，a标签的href属性与vue进行了绑定(只不过是无参的)。所以当点击不同的
	菜单时，href属性值也会发生改变，由于与vue进行了绑定，数据发生变化会触发下面这个updated钩子函数*/
	updated: function(){
		//路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}
});

function routerList(router, menuList){
	for(var key in menuList){
		var menu = menuList[key];
		// 如果是0级菜单就递归
		if(menu.type == 0){
			routerList(router, menu.list);
		}else if(menu.type == 1){
			router.add('#'+menu.url, function() {
				// window.location.hash用于设置或返回从#开始的url
				var url = window.location.hash;
				//替换iframe的url
			    vm.main = url.replace('#', '');
			    //导航菜单展开
			    $(".treeview-menu li").removeClass("active");
			    $("a[href='"+url+"']").parents("li").addClass("active");
			    
			    vm.navTitle = $("a[href='"+url+"']").text();
			});
		}
	}
}

