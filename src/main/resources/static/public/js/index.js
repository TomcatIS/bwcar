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
			<!--为1级菜单绑定点击事件用于切换菜单内容-->
			<a v-if="item.type === 1" :href="'#'+item.url" v-on:click="ToggleMainSrc">
				<i v-if="item.icon != null" :class="item.icon"></i>
				<i v-else class="fa fa-circle-o"></i> 
				{{item.name}}
			</a>
		</li>
	`,
	methods: {
		ToggleMainSrc: function(event){
			// 获取绑定标签的href属性值
			var url = event.target.href;
			// 获取菜单内容对应的html的路径
			var index = url.indexOf("#");
			vm.main = url.substring(index + 1);
		}
	}
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
			/*静态获取用户菜单*/
			/*$.getJSON("/json/menu_user.json", function (data) {
				// 这里不能使用this.menuList
				vm.menuList = data.menuList;
				window.permissions = data.permissions;
			})*/
			$.getJSON("/sys/menu/user", function (data) {
				// 这里不能使用this.menuList
				vm.menuList = data.menuList;
				window.permissions = data.permissions;
			})
		},
		getUser: function(){
			/*静态获取用户信息*/
			 /*$.getJSON("/json/user_info.json", function(data){
				vm.user = data.user;
			});*/

			 /*动态获取用户信息*/
			$.getJSON("/sys/user/info?_"+$.now(), function(data){
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
	/*updated: function(){
		//路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	}*/
});

/*function routerList(router, menuList){
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
}*/

