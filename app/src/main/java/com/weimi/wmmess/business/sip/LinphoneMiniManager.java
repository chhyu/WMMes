package com.weimi.wmmess.business.sip;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.sip.tools.LinphoneMiniUtils;

import org.linphone.core.AVPFMode;
import org.linphone.core.Address;
import org.linphone.core.AuthInfo;
import org.linphone.core.AuthMethod;
import org.linphone.core.Call;
import org.linphone.core.CallLog;
import org.linphone.core.CallParams;
import org.linphone.core.CallStats;
import org.linphone.core.ChatMessage;
import org.linphone.core.ChatRoom;
import org.linphone.core.ConfiguringState;
import org.linphone.core.Content;
import org.linphone.core.Core;
import org.linphone.core.CoreException;
import org.linphone.core.CoreListener;
import org.linphone.core.EcCalibratorStatus;
import org.linphone.core.Event;
import org.linphone.core.Factory;
import org.linphone.core.Friend;
import org.linphone.core.FriendList;
import org.linphone.core.GlobalState;
import org.linphone.core.InfoMessage;
import org.linphone.core.NatPolicy;
import org.linphone.core.PayloadType;
import org.linphone.core.PresenceModel;
import org.linphone.core.ProxyConfig;
import org.linphone.core.PublishState;
import org.linphone.core.RegistrationState;
import org.linphone.core.SubscriptionState;
import org.linphone.core.TransportType;
import org.linphone.core.Transports;
import org.linphone.core.VersionUpdateCheckResult;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class LinphoneMiniManager extends Service implements CoreListener {

    private static LinphoneMiniManager mInstance;
    private Context mContext;
    private Core mLinphoneCore;
    private Timer mTimer;
    private Factory lcFactory;
    private AudioManager mAudioManager;
    public static boolean isReady() {
        return mInstance != null;
    }
    public static LinphoneMiniManager getInstance() {
        return mInstance;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        lcFactory = Factory.instance();
        lcFactory.setDebugMode(true, "lilinaini 34>>");
        try {
            String basePath = mContext.getFilesDir().getAbsolutePath();
            copyAssetsFromPackage(basePath);

            mLinphoneCore = lcFactory.createCore( basePath + "/.linphonerc", basePath + "/linphonerc",  mContext);
            mLinphoneCore.addListener(this);

            startIterate();

            setUserAgent();

            mInstance = this;
            mLinphoneCore.setNetworkReachable(true); // Let's assume it's true

            mAudioManager = ((AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE));
            String[] dnsServer = new String[]{"8.8.8.8"};
            mLinphoneCore.setDnsServers(dnsServer);
            mLinphoneCore. enableAdaptiveRateControl(false);
            mLinphoneCore.start();
            setSipPort(-1);


            Intent intent1 = new Intent(SipCallActivity.RECEIVE_MAIN_ACTIVITY);
            intent1.putExtra("action", "show_version");intent1.putExtra("data",mLinphoneCore.getVersion() );
            sendBroadcast( intent1);

            /**
            NatPolicy nat = getOrCreateNatPolicy();
            nat.setStunServer("118.190.151.162:3478" );
            nat.enableIce(true);
            nat.enableTurn(true);
            mLinphoneCore.setNatPolicy(nat);
            setTurnUsernamePwd("lilin","lilinaini");
             **/
            Log.e("ddd",">>>>>>>>>>>>1116");
        } catch (Exception e) {
            Log.e("创建","错误");
        }
    }
    public void setSipPort(int port) {
        Transports transports = getLC().getTransports();
        transports.setUdpPort(port);
        transports.setTcpPort(port);
        transports.setTlsPort(-1);
        getLC().setTransports(transports);
    }
    public void setTurnUsernamePwd(String username,String password) {
        if (getLC() == null) return;
        NatPolicy nat = getOrCreateNatPolicy();
        AuthInfo authInfo = getLC().findAuthInfo(null, nat.getStunServerUsername(), null);

        if (authInfo != null) {
            AuthInfo cloneAuthInfo = authInfo.clone();
            getLC().removeAuthInfo(authInfo);
            cloneAuthInfo.setUsername(username);
            cloneAuthInfo.setUserid(username);
            cloneAuthInfo.setPassword(password);
            getLC().addAuthInfo(cloneAuthInfo);
        } else {
            authInfo = Factory.instance().createAuthInfo(username, username, password, null, null, null);
            getLC().addAuthInfo(authInfo);
        }
        nat.setStunServerUsername(username);
        getLC().setNatPolicy(nat);
    }



    private NatPolicy getOrCreateNatPolicy() {
        if ( mLinphoneCore == null) return null;
        NatPolicy nat = mLinphoneCore.getNatPolicy();
        if (nat == null) {
            nat = mLinphoneCore.createNatPolicy();
        }
        return nat;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    private void copyAssetsFromPackage(String basePath) throws IOException {
        LinphoneMiniUtils.copyIfNotExist(mContext, R.raw.linphonerc_default, basePath + "/.linphonerc");
        LinphoneMiniUtils.copyFromPackage(mContext, R.raw.linphonerc_factory, new File(basePath + "/linphonerc").getName());
        LinphoneMiniUtils.copyIfNotExist(mContext, R.raw.lpconfig, basePath + "/lpconfig.xsd");
        LinphoneMiniUtils.copyFromPackage(mContext,R.raw.assistant_create, new File(basePath + "/assistant_create.rc").getName());
    }


    private void setUserAgent() {
        try {
            String versionName = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            if (versionName == null) {
                versionName = String.valueOf(mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode);
            }
            mLinphoneCore.setUserAgent("LinphoneMiniAndroid", versionName);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    /**
     *   private void setFrontCamAsDefault() {
     int camId = 0;
     AndroidCameraConfiguration.AndroidCamera[] cameras = AndroidCameraConfiguration.retrieveCameras();
     for (AndroidCameraConfiguration.AndroidCamera androidCamera : cameras) {
     if (androidCamera.frontFacing == true)
     camId = androidCamera.id;
     }
     String[] devices = getLC().getVideoDevicesList();
     String newDevice = devices[camId];
     getLC().setVideoDevice(newDevice);
     }
     */

    private void startIterate() {
        TimerTask lTask = new TimerTask() {
            @Override
            public void run() {
                //Log.e(">>a",mLinphoneCore.getDefaultProxyConfig().getState().toString() +">>"+mLinphoneCore.getDefaultProxyConfig().getState().toInt() );

                if(mLinphoneCore!=null) mLinphoneCore.iterate();
            }
        };
        /*use schedule instead of scheduleAtFixedRate to avoid iterate from being call in burst after cpu wake up*/
        mTimer = new Timer("LinphoneMini scheduler");
        mTimer.schedule(lTask, 0, 20);
    }


    @Override
    public void onGlobalStateChanged(Core core, GlobalState globalState, String s) {
        Log.e(">onGlobalStateChanged",s);
    }

    @Override
    public void onRegistrationStateChanged(Core core, ProxyConfig proxyConfig, RegistrationState registrationState, String smessage) {
        Intent intent = new Intent(SipCallActivity.RECEIVE_MAIN_ACTIVITY);
        intent.putExtra("action", "reg_state");intent.putExtra("data",smessage );
        sendBroadcast( intent);

        if( smessage.indexOf("successful")!=-1){
            String str="音频:\n";
            PayloadType[] audioCodecs = mLinphoneCore.getAudioPayloadTypes();
            for (PayloadType payloadType : audioCodecs) {
                str+=payloadType.getMimeType()+"-"+payloadType.getNormalBitrate()+"-"+payloadType.enabled()+"\n";
            }
            str+="\n视频:";

            for (PayloadType payloadType1 : mLinphoneCore.getVideoPayloadTypes()){
              // if(payloadType1.getMimeType().equalsIgnoreCase("VP8")) {payloadType1.enable(false);}
                str+=payloadType1.getMimeType()+""+payloadType1.enabled()+"\n";
            }
            Intent intent1 = new Intent(SipCallActivity.RECEIVE_MAIN_ACTIVITY);
            intent1.putExtra("action", "show_code");intent1.putExtra("data",str );
            sendBroadcast( intent1);
        }
    }

    @Override
    public void onCallStateChanged(Core core, Call call, Call.State state, String s) {
        Intent intent1 = new Intent(SipCallActivity.RECEIVE_MAIN_ACTIVITY);
        intent1.putExtra("action", "show_callinfo");intent1.putExtra("data",s );
        sendBroadcast( intent1);
        Log.e(">onCallStateChanged", call.getRemoteAddress().getUsername()+">"+call.getRemoteAddressAsString()+">"+s);
    }

    @Override
    public void onNotifyPresenceReceived(Core core, Friend friend) {

    }

    @Override
    public void onNotifyPresenceReceivedForUriOrTel(Core core, Friend friend, String s, PresenceModel presenceModel) {

    }

    @Override
    public void onNewSubscriptionRequested(Core core, Friend friend, String s) {

    }

    @Override
    public void onAuthenticationRequested(Core core, AuthInfo authInfo, AuthMethod authMethod) {

    }

    @Override
    public void onCallLogUpdated(Core core, CallLog callLog) {

    }

    @Override
    public void onMessageReceived(Core core, ChatRoom chatRoom, ChatMessage chatMessage) {

    }

    @Override
    public void onMessageReceivedUnableDecrypt(Core core, ChatRoom chatRoom, ChatMessage chatMessage) {

    }

    @Override
    public void onIsComposingReceived(Core core, ChatRoom chatRoom) {
        Log.e(">onIsComposingReceived",chatRoom.getSubject());
    }

    @Override
    public void onDtmfReceived(Core core, Call call, int i) {
        Log.e(">onDtmfReceived",   i+"收到");
    }

    @Override
    public void onReferReceived(Core core, String s) {
        Log.e(">onReferReceived",s);
    }

    @Override
    public void onCallEncryptionChanged(Core core, Call call, boolean b, String s) {

    }

    @Override
    public void onTransferStateChanged(Core core, Call call, Call.State state) {

    }

    @Override
    public void onBuddyInfoUpdated(Core core, Friend friend) {

    }

    @Override
    public void onCallStatsUpdated(Core core, Call call, CallStats callStats) {
        //Log.e(">onCallStatsUpdated",callStats.toString());
    }

    @Override
    public void onInfoReceived(Core core, Call call, InfoMessage infoMessage) {
        Log.e(">onInfoReceived",infoMessage.getContent().toString());
    }

    @Override
    public void onSubscriptionStateChanged(Core core, Event event, SubscriptionState subscriptionState) {

    }

    @Override
    public void onNotifyReceived(Core core, Event event, String s, Content content) {

    }

    @Override
    public void onSubscribeReceived(Core core, Event event, String s, Content content) {

    }

    @Override
    public void onPublishStateChanged(Core core, Event event, PublishState publishState) {
        Log.e(">onInfoReceived",publishState+"");
    }

    @Override
    public void onConfiguringStatus(Core core, ConfiguringState configuringState, String s) {
       // Log.e(">onConfiguringStatus",s);
    }

    @Override
    public void onNetworkReachable(Core core, boolean b) {

    }

    @Override
    public void onLogCollectionUploadStateChanged(Core core, Core.LogCollectionUploadState logCollectionUploadState, String s) {

    }

    @Override
    public void onLogCollectionUploadProgressIndication(Core core, int i, int i1) {

    }

    @Override
    public void onFriendListCreated(Core core, FriendList friendList) {

    }

    @Override
    public void onFriendListRemoved(Core core, FriendList friendList) {

    }

    @Override
    public void onCallCreated(Core core, Call call) {
        Log.e(">onCallCreated",call+""+ call.getRemoteAddressAsString() );
    }

    @Override
    public void onVersionUpdateCheckResultReceived(Core core, VersionUpdateCheckResult versionUpdateCheckResult, String s, String s1) {

    }

    @Override
    public void onChatRoomStateChanged(Core core, ChatRoom chatRoom, ChatRoom.State state) {

    }

    @Override
    public void onQrcodeFound(Core core, String s) {

    }

    @Override
    public void onEcCalibrationResult(Core core, EcCalibratorStatus ecCalibratorStatus, int i) {

    }

    @Override
    public void onEcCalibrationAudioInit(Core core) {

    }

    @Override
    public void onEcCalibrationAudioUninit(Core core) {

    }

    //我的
    public void lilin_reg(String domain,String username,String password ,String port,  TransportType transport) throws Exception{
        if(mLinphoneCore==null){return;} 
        for (ProxyConfig linphoneProxyConfig : mLinphoneCore.getProxyConfigList()) {
            mLinphoneCore.removeProxyConfig( linphoneProxyConfig);
        }

		for( AuthInfo a: mLinphoneCore.getAuthInfoList()){
			mLinphoneCore.removeAuthInfo( a);
		}

        AccountBuilder builder = new AccountBuilder(mLinphoneCore)
                .setUsername(username)
                .setDomain(domain+":"+port)
                .setHa1(null)
                .setUserid(username)
                .setDisplayName("")//显示名
                .setPassword(password);
        String prefix=null;

        builder.setAvpfEnabled(false);


        if (prefix != null) {
            builder.setPrefix(prefix);
        }

        String forcedProxy =  "";//"39.108.0.211:11889";
        if (!TextUtils.isEmpty(forcedProxy)) {
            builder.setServerAddr(forcedProxy)  .setOutboundProxyEnabled(true)  ;// .setAvpfRrInterval(5);
        }

        if (transport != null) {
            builder.setTransport(transport);
        }

        try {
            builder.saveNewAccount();

        } catch (CoreException e) {
            Log.e("tishi",">>>>"+e.getMessage());
        }


    }

    public void lilin_call(String username,String host,boolean isVideoCall)   {
        if(mLinphoneCore==null){return;}
        Address address = mLinphoneCore.interpretUrl(username+ "@"+host );

        address.setDisplayName(username);
        CallParams params=mLinphoneCore.createCallParams(null);
        //params.enableLowBandwidth(true);
     //   params.setAudioBandwidthLimit(0);//
        params.enableVideo( isVideoCall );
        Call call = mLinphoneCore.inviteAddressWithParams(address, params);
        if (call == null) {
            Log.e("lilin error", "Could not place call to " + username);
            return;
        }
    }

    public void hangUp() {
        if(mLinphoneCore==null){return;}
        mLinphoneCore.terminateAllCalls();
    }
    public void lilin_enkuo( ){
        if(mAudioManager==null){return;}

         mAudioManager.setSpeakerphoneOn(mAudioManager.isSpeakerphoneOn() ?false:true);
    }
    public boolean getVideoEnble(){


        return false;
    }

    public void lilin_jie() {
        if(mLinphoneCore==null){return;}
          
        Call currentCall = mLinphoneCore.getCurrentCall();
        if (currentCall != null) {
            CallParams params = mLinphoneCore.createCallParams(currentCall);
            if(currentCall.getRemoteParams().videoEnabled()){params.enableVideo(true);    }
            currentCall.acceptWithParams( params );
        }
    }

    public Core getLC(){
        return mLinphoneCore;
    }

    public static class AccountBuilder {
        private Core lc;
        private String tempUsername;
        private String tempDisplayName;
        private String tempUserId;
        private String tempPassword;
        private String tempHa1;
        private String tempDomain;
        private String tempProxy;
        private String tempRealm;
        private String tempPrefix;
        private boolean tempOutboundProxy;
        private String tempContactsParams;
        private String tempExpire;
        private TransportType tempTransport;
        private boolean tempAvpfEnabled = false;
        private int tempAvpfRRInterval = 0;
        private String tempQualityReportingCollector;
        private boolean tempQualityReportingEnabled = false;
        private int tempQualityReportingInterval = 0;
        private boolean tempEnabled = true;
        private boolean tempNoDefault = false;


        public AccountBuilder(Core lc) {
            this.lc = lc;
        }

        public AccountBuilder setTransport(TransportType transport) {
            tempTransport = transport;
            return this;
        }

        public AccountBuilder setUsername(String username) {
            tempUsername = username;
            return this;
        }

        public AccountBuilder setDisplayName(String displayName) {
            tempDisplayName = displayName;
            return this;
        }

        public AccountBuilder setPassword(String password) {
            tempPassword = password;
            return this;
        }

        public AccountBuilder setHa1(String ha1) {
            tempHa1 = ha1;
            return this;
        }

        public AccountBuilder setDomain(String domain) {
            tempDomain = domain;
            return this;
        }

        public AccountBuilder setServerAddr(String proxy) {
            tempProxy = proxy;
            return this;
        }

        public AccountBuilder setOutboundProxyEnabled(boolean enabled) {
            tempOutboundProxy = enabled;
            return this;
        }

        public AccountBuilder setContactParameters(String contactParams) {
            tempContactsParams = contactParams;
            return this;
        }

        public AccountBuilder setExpires(String expire) {
            tempExpire = expire;
            return this;
        }

        public AccountBuilder setUserid(String userId) {
            tempUserId = userId;
            return this;
        }

        public AccountBuilder setAvpfEnabled(boolean enable) {
            tempAvpfEnabled = enable;
            return this;
        }

        public AccountBuilder setAvpfRrInterval(int interval) {
            tempAvpfRRInterval = interval;
            return this;
        }

        public AccountBuilder setRealm(String realm) {
            tempRealm = realm;
            return this;
        }

        public AccountBuilder setQualityReportingCollector(String collector) {
            tempQualityReportingCollector = collector;
            return this;
        }

        public AccountBuilder setPrefix(String prefix) {
            tempPrefix = prefix;
            return this;
        }

        public AccountBuilder setQualityReportingEnabled(boolean enable) {
            tempQualityReportingEnabled = enable;
            return this;
        }

        public AccountBuilder setQualityReportingInterval(int interval) {
            tempQualityReportingInterval = interval;
            return this;
        }

        public AccountBuilder setEnabled(boolean enable) {
            tempEnabled = enable;
            return this;
        }

        public AccountBuilder setNoDefault(boolean yesno) {
            tempNoDefault = yesno;
            return this;
        }

        /**
         * Creates a new account
         *
         * @throws CoreException
         */
        public void saveNewAccount() throws CoreException {

            if (tempUsername == null || tempUsername.length() < 1 || tempDomain == null || tempDomain.length() < 1) {
                Log.e("===","Skipping account save: username or domain not provided");
                return;
            }

            String identity = "sip:" + tempUsername + "@" + tempDomain;
            String proxy = "sip:";
            if (tempProxy == null) {
                proxy += tempDomain;
            } else {
                if (!tempProxy.startsWith("sip:") && !tempProxy.startsWith("<sip:")
                        && !tempProxy.startsWith("sips:") && !tempProxy.startsWith("<sips:")) {
                    proxy += tempProxy;
                } else {
                    proxy = tempProxy;
                }
            }

            Address proxyAddr = Factory.instance().createAddress(proxy);
            Address identityAddr = Factory.instance().createAddress(identity);
            if (proxyAddr == null || identityAddr == null) {
                throw new CoreException("Proxy or Identity address is null !");
            }

            if (tempDisplayName != null) {
                identityAddr.setDisplayName(tempDisplayName);
            }

            if (tempTransport != null) {
                proxyAddr.setTransport(tempTransport);
            }

            String route = tempOutboundProxy ? proxyAddr.asStringUriOnly() : null;

            ProxyConfig prxCfg = lc.createProxyConfig();
            prxCfg.setIdentityAddress(identityAddr);
            prxCfg.setServerAddr(proxyAddr.asStringUriOnly());
            prxCfg.setRoute(route);
            prxCfg.enableRegister(tempEnabled);

            if (tempContactsParams != null)
                prxCfg.setContactUriParameters(tempContactsParams);
            if (tempExpire != null) {
                prxCfg.setExpires(Integer.parseInt(tempExpire));
            }

			prxCfg.setAvpfMode(  tempAvpfEnabled ? AVPFMode.Enabled : AVPFMode.Disabled );
            prxCfg.setAvpfRrInterval(tempAvpfRRInterval);
            prxCfg.enableQualityReporting(tempQualityReportingEnabled);
            prxCfg.setQualityReportingCollector(tempQualityReportingCollector);
            prxCfg.setQualityReportingInterval(tempQualityReportingInterval);



            if (tempPrefix != null) {
                prxCfg.setDialPrefix(tempPrefix);
            }
           

            if (tempRealm != null)
                prxCfg.setRealm(tempRealm);

            AuthInfo authInfo = Factory.instance().createAuthInfo(tempUsername, tempUserId, tempPassword, tempHa1, tempRealm, tempDomain);


            lc.addProxyConfig(prxCfg);
            lc.addAuthInfo(authInfo);

            if (!tempNoDefault)
                lc.setDefaultProxyConfig(prxCfg);
        }
    }
}
