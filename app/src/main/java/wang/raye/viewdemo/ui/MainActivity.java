package wang.raye.viewdemo.ui;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import wang.raye.preioc.PreIOC;
import wang.raye.preioc.annotation.BindById;
import wang.raye.preioc.annotation.OnItemClick;
import wang.raye.viewdemo.R;

/**
 * 主界面Activity
 * 
 * @author Ray
 *
 */

public class MainActivity extends Activity {

	@BindById(R.id.list)
	ListView list;

	private ArrayList<ActivityInfo> mActivities = null;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PreIOC.binder(this);

		try {

			PackageInfo pi = getPackageManager().getPackageInfo("wang.raye.viewdemo",
					PackageManager.GET_ACTIVITIES);
			// 获取所有Activity信息
			mActivities = new ArrayList<ActivityInfo>(
					Arrays.asList(pi.activities));
			//获取本类的名字
			String ourName = getClass().getName();
			//获取Activity的名字
			ArrayList<String> names = new ArrayList<String>();
			
			for(int i = mActivities.size() - 1; i > -1;i--){
				if(mActivities.get(i).name.equals(ourName)){
					mActivities.remove(i);
				}else{
					if(mActivities.get(i).nonLocalizedLabel != null){
						names.add(0, mActivities.get(i).nonLocalizedLabel.toString());
					}else{
						mActivities.remove(i);
					}
				}
			}
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
			adapter.addAll(names);
			
			list.setAdapter(adapter);
			

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	@OnItemClick({R.id.list})
	public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
		Intent intent = new Intent();
		intent.setClassName(this, mActivities.get(position).name);
		startActivity(intent);
	}

}
