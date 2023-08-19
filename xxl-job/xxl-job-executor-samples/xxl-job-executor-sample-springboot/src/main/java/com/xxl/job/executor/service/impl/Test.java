package com.xxl.job.executor.service.impl;

public class Test {
    public static void main(String[] args)  {
//        System.out.println("牛鼠鸡鼠狗猪".indexOf("鼠"));
//        List jiaList = new ArrayList();
//        jiaList.add("狗");
//        jiaList.add("猪");
//        System.out.println(jiaList.contains("狗"));
//        System.out.println("攪珠日期 : 17/08/2023(星期四)".trim().substring("攪珠日期 : 17/08/2023(星期四)".trim().indexOf(":")+1,"攪珠日期 : 17/08/2023(星期四)".trim().length()-4));
        try{
            new HongKongPositionUtils().reqPosition("https://bet.hkjc.com/marksix/index.aspx?lang=ch");
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("19/08/2023 (星期六)".substring(0,10));
//        String[] timeStr = "晚上 9:15".split(" ");
//         System.out.println(timeStr[0]+"========="+timeStr[1]);
//         try {
//             Long time = DateUtil.strToDate("10:15",DateUtil.HHMM).getTime();
//             System.out.println("--------"+time);
//                //47700000-4500000
//             //57600000 14400000
//             String str = DateUtil.longToStr(time+43200000,DateUtil.HHMM);
//             System.out.println("-----d---"+str);
//         }catch (Exception e) {
//             e.printStackTrace();
//         }
    }
    public static String getRating(String url){
//        String html = ConnectionURL.Connet(url);
//        System.out.println(html);
//        Pattern pattern = Pattern.compile("");
//        Matcher m = pattern.matcher(html);
        String score = "";
//        if(m.find()){
//            int s = m.group().indexOf("");
//            score = m.group().substring(s+1);
//        }
        return  score;
    }
}
