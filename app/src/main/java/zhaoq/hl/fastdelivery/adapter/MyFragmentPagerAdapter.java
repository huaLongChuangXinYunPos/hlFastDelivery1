package zhaoq.hl.fastdelivery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery.adapter
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/18  15:46
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment[]  fragments;

    public MyFragmentPagerAdapter(FragmentManager fm,Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        int ret =0;
        if(fragments!=null){
            ret = fragments.length;
        }
        return ret;
    }
}
