# FloatView
floatview on windowmanager,and you can be easy to add call show by using it

自定义floatview，无需申明悬浮框权限，利用WindowManager TYPE_TOAST实现全站浮动式按钮,重写touch事件实现任意拖动，将view加入windowmanager层，可以使用控件实现类似来电秀的效果。
  
 ## MIUI使用TYPE_TOAST也会无效，所以MIUI系统中需要在manifest里面注册SYSTEM_ALERT_Winow权限,其他系统可以不用.
  
  <img src=https://github.com/AlexLiuSheng/FloatView/blob/master/floatview.gif width=300/><img src=https://github.com/AlexLiuSheng/FloatView/blob/master/z.gif width=300/><img src=https://github.com/AlexLiuSheng/FloatView/blob/master/z3.gif width=300/>
 
  
 ### 导入: 
      
`compile 'com.allenliu:floatview:1.0.1'`
            
 ### 初始化Floatview:
  
             FloatView(Context context, int x, int y, View childView)
      or
             FloatView(Context context, int x, int y, int layoutres)
 ### 其他方法:
  
```
updateFloatViewPosition(int x, int y)//更新位置

setFloatViewClickListener(IFloatViewClick listener)`

addToWindow()//加入窗口

setIsAllowTouch(boolean flag)//是否允许拖动

removeFromWindow()//移除窗口
```


  由于控件显示在windowmanager上，所以控件显示的生命周期需要自己手动管理,可以在Application或者基层Activity里控制显示和隐藏。
  
  ### 来电秀
  利用控件也可以显示在来电秀上。
  
  由于继承自linearlayout所以可以添加子view，可以初始化时将View或layout传入，也可以手动addview，在监听来电时加入view即可,有兴趣的朋友可以  
  参考下源码
  
          case TelephonyManager.CALL_STATE_RINGING:
           if (!viewIsShow) {
          viewIsShow = true;
          if (floatView == null)
          floatView = new FloatView(context, 0, 0, R.layout.callshow_layout);
          floatView.addToWindow();
          }
   


