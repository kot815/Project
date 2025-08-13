package com.example.myfirstapp.entity;

import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

public class DataService {


    public static List<ProductInfo> getListData(int position) {
        List<ProductInfo> list=new ArrayList<ProductInfo>();
        if (position == 0) {
         list.add(new ProductInfo(1001,R.mipmap.lt1,"玉峰 霸王丝爆辣辣条 20g/袋","霸王丝爆辣辣条 —— 辣到极致，欲罢不能！\n" +
                 "当那一抹鲜艳的红色映入眼帘，当那股浓烈的辣味扑鼻而来，你就知道，这就是传说中的霸王丝爆辣辣条！\n" +
                 "精选原料，品质保障：我们坚持选用优质小麦粉，每一粒小麦都经过精心筛选，确保其天然纯净、麦香浓郁。搭配独特的香辛料配方，从源头把控品质，只为给您带来最纯正、最美味的辣条体验。\n" +
                 "爆辣口感，挑战味蕾：霸王丝的辣，绝非一般辣条可比！它采用多种辣椒精心调配，辣味层次丰富且浓郁。入口瞬间，舌尖被热辣包裹，仿佛舌尖上的一场热舞，辣得酣畅淋漓，让您的味蕾沉浸在这火爆的刺激之中，越吃越上瘾，是无辣不欢者的终极挑战！\n" +
                 "独特工艺，丝丝入味：采用先进的挤压膨化技术，使辣条呈现出丝丝分明的诱人外形，每一根辣条都充分吸收了香料的精华，从外到内，辣味均匀渗透，口感紧实有嚼劲，咬下去的每一口都充满了浓郁的香味和辣味，让人回味无穷。\n"
                ,2));
            list.add(new ProductInfo(1002,R.mipmap.lt2,"文君 斑马六辣条牛筋儿时怀旧辣片面筋玛法豆干网红零食","斑马六网红零食，儿时辣味齐聚\n" +
                    "精选牛筋面、豆干等，怀旧辣味来袭！\n",4));
            list.add(new ProductInfo(1003,R.mipmap.lt3,"周在得 香辣味油条148g/袋","周在得香辣味油条，香酥热辣新体验\n" +
                    "精选食材，匠心工艺，每一口都饱含浓郁香辣味。外酥里嫩，口感绝佳，开启美味早餐新选择！\n" ,3));
            list.add(new ProductInfo(1004,R.mipmap.lt4,"馋嘴小丝 香辣霸王丝14g/袋","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",4));
            list.add(new ProductInfo(1005,R.mipmap.lt5,"缺牙齿 香辣素牛肚 1袋","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",2));

        } else if (position == 1) {

            list.add(new ProductInfo(1006,R.mipmap.yl1,"元气上森林 柠檬冰茶 900ml/瓶","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",5));
            list.add(new ProductInfo(1007,R.mipmap.yl2,"雪花勇闯天涯8°啤酒 500ml/听*3","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",20));
            list.add(new ProductInfo(1008,R.mipmap.yl3,"大窑AYAYAO原味荔枝味","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",7));
        } else if (position == 2) {
            list.add(new ProductInfo(1009,R.mipmap.fz1,"防水棉拖鞋女士秋冬季2023新款室内家具踩屎感...","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",15));

            list.add(new ProductInfo(1010,R.mipmap.fz2,"居家凉拖鞋镂空浴室男女寝室情侣室内家用洗澡速干...","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",22));
        } else if (position == 3) {

            list.add(new ProductInfo(1011,R.mipmap.dq1,"惠选 新款时尚折叠便携节能学生宿舍电吹风护发速干...","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",80));
            list.add(new ProductInfo(1012,R.mipmap.dq2,"惠选 格力通用款空调遥控器/个","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",50));
            list.add(new ProductInfo(1013,R.mipmap.dq3,"惠选 抽水器 约300g/个","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",25));
        } else if (position == 4) {
            list.add(new ProductInfo(1014,R.mipmap.rc1,"【一次性剃须刀】 冰叶一次性剃须刀剃须膏套装浴场洗...","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",100));
            list.add(new ProductInfo(1015,R.mipmap.rc2,"【强力粘蝇板】 名雀 苍蝇贴强力粘蝇纸","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",10));

            list.add(new ProductInfo(1016,R.mipmap.rc3,"小礼品韩版可爱卡通小镜子 化妆镜 随身化妆镜","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",6));

        } else {
            list.add(new ProductInfo(1017,R.mipmap.hz2,"伊芝莲自粘发膜带夹刘海蓬松卷发简空气刘海多型号","非常的安逸非常的舒服\n" +
                    "确实很不错的一个房子蛋糕/n"+
                    "折扣价八折",12));
        }
           return list;
    }
}

