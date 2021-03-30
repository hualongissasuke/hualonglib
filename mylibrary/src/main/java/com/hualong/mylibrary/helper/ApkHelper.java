package com.hualong.mylibrary.helper;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.webkit.URLUtil;

import com.blankj.utilcode.util.AppUtils;
import com.hualong.mylibrary.util.Console;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class ApkHelper {
    private static ApkHelper instance;
    private Context mContext;
    private BroadcastReceiver broadcastReceiver;
    // public String uri = "http://wms.gdhxgf.com/wms/upload/50241616578085041.apk";

    private ApkHelper(Context context){
        this.mContext = context;
    }

    public static ApkHelper getInstance(Context context){
        if(instance == null){
            synchronized (ApkHelper.class){
                if(instance == null){
                    instance = new ApkHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 调用系统下载器进行下载
     * @param url 文件下载地址
     * @param contentDisposition 文件名
     */
    public void downloadBySystem(String url, String contentDisposition) {
        // 指定下载地址
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // 允许媒体扫描，根据下载的文件类型被加入相册、音乐等媒体库
        request.allowScanningByMediaScanner();
        // 设置通知的显示类型，下载进行时和完成后显示通知
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // 允许在计费流量下下载
//        request.setAllowedOverMetered(false);
        // 允许该记录在下载管理界面可见
        request.setVisibleInDownloadsUi(false);
        // 允许漫游时下载
        request.setAllowedOverRoaming(true);
        // 允许下载的网路类型
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        // 设置下载文件保存的路径和文件名
        String mimeType = "application/vnd.android.package-archive";
        String fileName  = URLUtil.guessFileName(url, contentDisposition, mimeType);
        Console.logd("fileName:{}" + fileName);
        // if (fileName != null && fileName.endsWith(ConstantUtil.FILE_EXTENSION_NAME) && SPECIAL_MIME_TYPE.equals(mimeType)) {
        //     mimeType = "application/vnd.android.package-archive";
        // }
        try{
            File apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName);
            if(apkFile.exists() && apkFile.delete()){
                Console.logd("删除APK成功");
            }
            Console.loge("apkFile",apkFile.getAbsolutePath());
        }catch (Exception e){}


        request.setMimeType(mimeType);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

//        另外可选一下方法，自定义下载路径
//        request.setDestinationUri()
//        request.setDestinationInExternalFilesDir(ContextUtil.getAppContext(), ContextUtil.getAppContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), fileName);
        final DownloadManager downloadManager = (DownloadManager)mContext.getSystemService(DOWNLOAD_SERVICE);
        // 添加一个下载任务
        long downloadId = downloadManager.enqueue(request);
        // Console.logd("downloadId:{}" + downloadId,
        //         downloadManager.getMimeTypeForDownloadedFile(downloadId),
        //         downloadManager.getUriForDownloadedFile(downloadId));
        listener(downloadId,fileName);
    }

    /** 监听是否下载完后 */
    private void listener(final long Id, final String fileName) {
        final File base = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Console.loge(base.getAbsolutePath()+File.separator+fileName,Uri.withAppendedPath(Uri.fromFile(base), fileName));
        // 注册广播监听系统的下载完成事件。
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (ID == Id) {
                    // Toast.makeText(getApplicationContext(), "任务:" + Id + " 下载完成!", Toast.LENGTH_LONG).show();
                    AppUtils.installApp(base.getAbsolutePath()+File.separator+fileName);
                }
            }
        };

        mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    public void unregisterDownloadReceiver(){
        try{
            if(broadcastReceiver != null)
                mContext.unregisterReceiver(broadcastReceiver);
        }catch (Exception e){}
    }
}
