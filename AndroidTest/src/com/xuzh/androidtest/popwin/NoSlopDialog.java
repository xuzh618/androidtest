package com.xuzh.androidtest.popwin;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * 默认情况下，如果设置了setCanceledOnTouchOutside(true);touch point在对话框外并且加上一段距离才算是outside
 * 这点距离由final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();定义
 * 判断逻辑位于{@link Dialog#onTouchEvent}
 * 
 * 这个类的目的就是判断touchPoint是否outside的时候不加slop，只要在窗口外就认为是TouchOutside
 *
 */
public class NoSlopDialog extends Dialog {

	public NoSlopDialog(Context context) {
		super(context);
	}

	public NoSlopDialog(Context context, int theme) {
		super(context, theme);
	}

	public NoSlopDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean consumed=super.onTouchEvent(event);
		
		if(!consumed && isShowing() && event.getAction() == MotionEvent.ACTION_DOWN){
			final int x = (int) event.getX();
	        final int y = (int) event.getY();
	        final View decorView = getWindow().getDecorView();
	        consumed= (x < 0) || (y < 0)
	                || (x > (decorView.getWidth()))
	                || (y > (decorView.getHeight()));
	        
			if(consumed)
				cancel();
		}
		
		return consumed;
	}
	
	

}
