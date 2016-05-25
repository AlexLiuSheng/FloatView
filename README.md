# FloatView
floatview on windowmanager,and you can be easy to add call show by using it

 自定义floatview，无需申明悬浮框权限，利用WindowManager TYPE_TOAST实现全站浮动式按钮,重写touch事件实现任意拖动，将view加入windowmanager层，可以使用控件实现    类似来电秀的效果。


  ![image](https://github.com/AlexLiuSheng/FloatView/blob/master/z.gif)
  
  ![image](https://github.com/AlexLiuSheng/FloatView/blob/master/z3.gif)
  
  由于将WindowManager封装到Floaview内部实现全站按钮，所以不能直接使用布局inflate，初始化Floatview:
  
             FloatView(Context context, int x, int y, View childView)
      or
             FloatView(Context context, int x, int y, int layoutres)
  同时Floatview内部暴露了几个方法和一个点击回调:
  
         updateFloatViewPosition(int x, int y)//更新位置
         setFloatViewClickListener(IFloatViewClick listener)
         addToWindow()//加入窗口
         setIsAllowTouch(boolean flag)//是否允许拖动
         removeFromWindow()//移除窗口
  由于控件显示在windowmanager上，所以控件显示的生命周期需要自己手动管理,可以在Application或者基层Activity里控制显示和隐藏。
  利用控件也可以显示在来电秀上，由于继承自linearlayout所以可以添加子view，可以初始化时将View或layout传入，也可以手动addview，在监听来电时加入此view即可,有兴趣的朋友可以参考下源码
  
           case TelephonyManager.CALL_STATE_RINGING:
           if (!viewIsShow) {
          viewIsShow = true;
          if (floatView == null)
          floatView = new FloatView(context, 0, 0, R.layout.callshow_layout);
          floatView.addToWindow();}
   


