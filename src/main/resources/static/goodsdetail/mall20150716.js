$(function(){
	var ie6=!-[1,]&&!window.XMLHttpRequest;
	if(ie6){
		$('.nav-box li').hover(function(){
			$(this).addClass('thisLi').siblings().removeClass('thisLi');
		},function(){
			$(this).removeClass('thisLi');
		})
	}
	//头部产品自动播放
	var p_index=0;
	$('.header-pro li').eq(0).addClass('active');
	function showPro(num){
		var len=$('.header-pro li').length;
		p_index+=num;
		if(p_index>len-1){
			p_index=0;
		}else if(p_index<0){
			p_index=len-1;
		}
		$('.header-pro li').eq(p_index).show().siblings().hide();
	}
	$('.arrow-right').click(function(){
		showPro(1);
	})
	$('.arrow-left').click(function(){
		showPro(-1);
	})
	function autoImg(){
		var len=$('.header-pro li').length;
		p_index++;
		if(p_index>len-1){
			p_index=0;
		}
		$('.header-pro li').eq(p_index).show().siblings().hide();
	}
	timer=setInterval(autoImg,2000);
	$('.header-pro,.arrow-item').hover(function(){
		clearInterval(timer);	
	},function(){
		timer=setInterval(autoImg,2000);	
	})
	
	
	//焦点图及以下产品自动切换
	picScroll($('.trigger'),$('.banner-list'),$('.btn-l,.btn-r'));
	picScroll($(''),$('.slide-main'),$('.button-l,.button-r'));
	function picScroll (tabbox,picBox,btns) {
		var index = 0,
		tabs = tabbox.find("a"),
		pics = picBox.find("li"),
		l = pics.length,
		timer = null,
		isPlay = false, //判断是否在执行动画
		picWidth = pics.first().outerWidth(true); //获取图片宽度
		picBox.css({width:picWidth * (l+1) + "px",left:-picWidth + "px"}).prepend("<li>" + pics.last().html() + "</li>");
	 
	  //状态显示及事件
		pics.each( function (e) {
			e == 0 ? tabbox.append('<a class="active" href="javascript:;"></a>') : tabbox.append('<a href="javascript:;"></a>') ;
		});
		tabs = tabbox.find("a");
		tabs.each(function(e) {
			var me = $(this);
			me.hover(function (){
				me.addClass("active").siblings().removeClass("active");
				index = e;
				showPic(e);
				clearInterval(timer);
			});
		});
	
		//按钮事件
		btns.each(function (e){
			$(this).click(function (){
				var type = e == 0 ? -1 : 1;
				if (!isPlay) {
				   index = type > 0 ? (index + 1) : (index - 1);
				   changeIndex(index);
				   showPic(index);
				}
			});
		});
		clearInterval(timer);
		autoPlay (); //默认播放
		picBox.parent().hover(function (){clearInterval(timer)},function () {autoPlay ()});
	
		//定时器
		function autoPlay (){
			timer = setInterval(function () {
				if (!isPlay) {
					index++;
					changeIndex(index);
					showPic(index);
				}
			},4000);
		}
	
		//索引值判断且改变css，使图片连续
		function changeIndex(i) {
			index = i;
			if (index == l) {
				picBox.css({left : "0px"});
				index = 0;
			}
		}
	
		//图片滚动动画
		function showPic (i) {
			isPlay = true;
			picBox.stop(true,true).animate({left : -picWidth * (i+1)},500,function () {
				isPlay = false;
				if (i == -1) picBox.css({left : -l * picWidth});
			});
			index = i == -1 ? l - 1 : index;
			tabs.eq(index).addClass("active").siblings().removeClass("active");
		}
	}
	
	
	
	
	//生活周刊&热门品牌
    show($('.weekly-list'),$('.weekly-num'),$('.left-btn'),$('.right-btn'));
	show($('.brand-box'),$('.page-num'),$('.arrow-l'),$('.arrow-r'));
	
	function show(box,numBox,btnL,btnR){
		var s_index=0;
		box.find('li:first').addClass('active');
		var len=box.find('li').length;
		if(len<=1){
			btnL.hide();
			btnR.hide();
			numBox.hide();
		}
		numBox.find('span').text(len);
		btnR.click(function(){
			fade(1);
		})
		btnL.click(function(){
			fade(-1);
		})
		function fade(speed){
			s_index+=speed;
			if(s_index>len-1){
				s_index=0;
			}else if(s_index<0){
				s_index=len-1;
			}
			numBox.find('b').text(s_index+1);
			box.find('li').eq(s_index).fadeIn().siblings().fadeOut();
		}
	}
	
	

})