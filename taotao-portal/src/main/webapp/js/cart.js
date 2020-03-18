var TTCart = {
	load : function(){ // 加载购物车数据
		
	},
	itemNumChange : function(){
		//获取class为increment,就是获取+号按钮
		$(".increment").click(function(){//＋
			//获取同级的input标签
			var _thisInput = $(this).siblings("input");
			//把input标签中的数值加1
			_thisInput.val(eval(_thisInput.val()) + 1);
			//发送请求更新数据库,请求方法:post,请求url:service/cart/update/num/{itemId}/${num}
			$.post("/service/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val(),function(data){
				//请求成功重新计算总价
				TTCart.refreshTotalPrice();
			});
		});
		$(".decrement").click(function(){//-
			//获取同级的input标签
			var _thisInput = $(this).siblings("input");
			//判断input标签中的数值是否为1,如果1则什么都不干,直接返回
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			//把input标签中的数值减1
			_thisInput.val(eval(_thisInput.val()) - 1);
			//发送请求更新数据库,请求方法:post,请求url:service/cart/update/num/{itemId}/${num}
			$.post("/service/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val(),function(data){
				//请求成功重新计算总价
				TTCart.refreshTotalPrice();
			});
		});
		$(".quantity-form .quantity-text").rnumber(1);//限制只能输入数字
		$(".quantity-form .quantity-text").change(function(){
			var _thisInput = $(this);
			$.post("/service/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val(),function(data){
				TTCart.refreshTotalPrice();
			});
		});
	},
	refreshTotalPrice : function(){ //重新计算总价
		var total = 0; 
		$(".quantity-form .quantity-text").each(function(i,e){
			var _this = $(e);
			total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		});
		$(".totalSkuPrice").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '￥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	}
};

$(function(){
	TTCart.load();
	TTCart.itemNumChange();
});