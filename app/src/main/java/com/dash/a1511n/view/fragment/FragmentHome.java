package com.dash.a1511n.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dash.a1511n.R;
import com.dash.a1511n.model.bean.HomeBean;
import com.dash.a1511n.presenter.FragmentHomeP;
import com.dash.a1511n.util.ApiUtil;
import com.dash.a1511n.util.GlideImageLoader;
import com.dash.a1511n.view.Iview.InterFragmentHome;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dash on 2018/1/23.
 */
public class FragmentHome extends Fragment implements InterFragmentHome{

    private FragmentHomeP fragmentHomeP;
    private Banner banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //主要在oncreateView中做的操作是找到控件...
        View view = inflater.inflate(R.layout.fragment_home_layout,container,false);

        banner = view.findViewById(R.id.banner);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //在这里可以做其他操作....
        fragmentHomeP = new FragmentHomeP(this);

        //调用p层里面的方法....https://www.zhaoapi.cn/ad/getAd
        fragmentHomeP.getNetData(ApiUtil.HOME_URL);


        //初始化banner
        initBanner();

    }

    private void initBanner() {

        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

    }

    //此时已经获取导数据,,,,并且此时也在主线程中
    @Override
    public void onSuccess(HomeBean homeBean) {

        //设置显示bannner
        List<HomeBean.DataBean> datas = homeBean.getData();

        List<String> imageUrls = new ArrayList<>();
        for (int i = 0;i<datas.size();i++){
            imageUrls.add(datas.get(i).getIcon());
        }

        banner.setImages(imageUrls);
        banner.start();


    }
}
