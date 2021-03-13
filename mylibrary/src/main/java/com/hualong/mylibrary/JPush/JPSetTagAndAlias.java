package com.hualong.mylibrary.JPush;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class JPSetTagAndAlias {

    /**
     * 设置标签与别名
     */
    // private void setTagAndAlias(final String uniqueDeviceId) {
    //     /**
    //      *这里设置了别名，在这里获取的用户登录的信息
    //      *并且此时已经获取了用户的userId,然后就可以用用户的userId来设置别名了
    //      **/
    //     //false状态为未设置标签与别名成功
    //     //if (UserUtils.getTagAlias(getHoldingActivity()) == false) {
    //     Set<String> tags = new HashSet<String>();
    //     //这里可以设置你要推送的人，一般是用户uid 不为空在设置进去 可同时添加多个
    //     if (!TextUtils.isEmpty(uniqueDeviceId)){
    //         tags.add(uniqueDeviceId);//设置tag
    //     }
    //
    //     //上下文、别名【Sting行】、标签【Set型】、回调
    //     JPushInterface.setAliasAndTags(this, uniqueDeviceId, tags,
    //             new TagAliasCallback() {
    //                 @Override
    //                 public void gotResult(int code, String s, Set<String> set) {
    //                     switch (code) {
    //                         case 0:
    //                             //这里可以往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
    //                             //UserUtils.saveTagAlias(getHoldingActivity(), true);
    //                             logs = "Set tag and alias success极光推送别名设置成功";
    //                             Log.e("TAG", logs);
    //                             break;
    //                         case 6002:
    //                             //极低的可能设置失败 我设置过几百回 出现3次失败 不放心的话可以失败后继续调用上面那个方面 重连3次即可 记得return 不要进入死循环了...
    //                             setTagAndAlias(uniqueDeviceId);
    //                             if(setCount == 3){
    //                                 setCount = 0;
    //                                 return;
    //                             }else
    //                                 ++setCount;
    //                             break;
    //                         default:
    //                             logs = "极光推送设置失败，Failed with errorCode = " + code;
    //                             Log.e("TAG", logs);
    //                             break;
    //                     }
    //
    //                 }
    //             });
    //     // }
    // }

    /**
     * 取消设置标签与别名
     */
    // private void cancleTagAndAlias() {
    //     //TODO 上下文、别名、标签、回调  退出后空数组与空字符串取消之前的设置
    //     Set<String> tags = new HashSet<String>();
    //     JPushInterface.setAliasAndTags(this, "", tags, new TagAliasCallback() {
    //         @Override
    //         public void gotResult(int i, String s, Set<String> set) {
    //
    //         }
    //     });
    // }
}
