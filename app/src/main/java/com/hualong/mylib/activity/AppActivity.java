package com.hualong.mylib.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.hualong.mylib.R;
import com.hualong.mylibrary.bind.BindViewHolder;
import com.hualong.mylibrary.constant.PermissionsConstant;
import com.hualong.mylibrary.helper.ApkHelper;
import com.hualong.mylibrary.helper.PermissionHelper;
import com.hualong.mylibrary.util.Console;

import java.io.File;

/**
 * 更新APP
 */
public class AppActivity extends BaseActivity {
    private BroadcastReceiver broadcastReceiver;
    public String uri = "http://wms.gdhxgf.com/wms/upload/50241616578085041.apk";
    private  DownloadManager.Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        // AppUtils.installApp("/storage/emulated/0/tywms/tywms1.0.1.apk");
        if(PermissionHelper.getInstance().checkWriteStoragePermission(this))
            ApkHelper.getInstance(this).downloadBySystem(uri,"");
            // downloadBySystem(uri,"tywms1.0.1.apk");
        // Intent intent = IntentUtils.getInstallAppIntent(Uri.parse(uri));
        // AppActivity.this.startActivity(intent);
        // AppUtils.installApp(Uri.parse(uri));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PermissionsConstant.REQUEST_EXTERNAL_WRITE:
                ApkHelper.getInstance(this).downloadBySystem(uri,"");
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 调用系统下载器进行下载
     *
     * @param url 文件下载地址
     * @param contentDisposition 文件名
     */
    private void downloadBySystem(String url, String contentDisposition) {
        // 指定下载地址
        request = new DownloadManager.Request(Uri.parse(url));
        // 允许媒体扫描，根据下载的文件类型被加入相册、音乐等媒体库
        request.allowScanningByMediaScanner();
        // 设置通知的显示类型，下载进行时和完成后显示通知
        // request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
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
        final DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        // 添加一个下载任务
        long downloadId = downloadManager.enqueue(request);
        // Console.logd("downloadId:{}" + downloadId,
        //         downloadManager.getMimeTypeForDownloadedFile(downloadId),
        //         downloadManager.getUriForDownloadedFile(downloadId));
        listener(downloadId,fileName);
        getBytesAndStatus(downloadId,downloadManager);
    }

    //监听下载进度
    public int[] getBytesAndStatus(long downloadId,DownloadManager downloadManager) {
        int[] bytesAndStatus = new int[] { -1, -1, 0 };
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = null;
        try {
            cursor = downloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                if (cursor != null && cursor.moveToFirst()) {
                    //已经下载文件大小
                    bytesAndStatus[0] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    //下载文件的总大小
                    bytesAndStatus[1] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    //下载状态
                    bytesAndStatus[2] = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bytesAndStatus;
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
                    Toast.makeText(getApplicationContext(), "任务:" + Id + " 下载完成!", Toast.LENGTH_LONG).show();
                    AppUtils.installApp(base.getAbsolutePath()+File.separator+fileName);
                }
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ApkHelper.getInstance(this).unregisterDownloadReceiver();
    }

    @Override
    protected void requestSuccess(String res, int actionCode) {

    }

    @Override
    protected void requestFail(String error, int actionCode) {

    }
}
