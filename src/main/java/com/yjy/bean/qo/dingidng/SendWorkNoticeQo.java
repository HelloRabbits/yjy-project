package com.yjy.bean.qo.dingidng;

import com.yjy.common.ErrorCode;
import com.yjy.common.QuestionException;
import com.yjy.entity.AppInfo;
import com.yjy.service.sdk.TokenService;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.taobao.api.internal.util.json.JSONWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjl
 * @description 发送工作提醒消息参数
 * @date 2020-05-29 17:20
 */
public class SendWorkNoticeQo {

    /**
     * 应用agentId, h5微应用和小程序有，机器人没有
     */
    private Long agentId;

    /**
     * userId集合 多个用逗号隔开 如：zhangsan,lisi
     */
    private String userIdList;

    /**
     * 部门id集合 多个逗号隔开 如：123,456
     */
    private String deptIdList;

    /**
     * 是否发送给企业全部用户
     */
    private Boolean toAllUser = Boolean.FALSE;

    /**
     * 请使用内部builder方法构建
     */
    private String msg;

    /**
     * 构建消息使用
     */
    private OapiMessageCorpconversationAsyncsendV2Request.Msg msgModel;
    /**
     * 构建OA消息使用
     * 富文本信息
     */
    private OapiMessageCorpconversationAsyncsendV2Request.Rich rich;

    /**
     * 卡片消息
     * 多个按钮list
     */
    private final List<OapiMessageCorpconversationAsyncsendV2Request.BtnJsonList> btnJsonList = new ArrayList<>(16);

    /**
     * oa消息，body内参数 form
     */
    private final List<OapiMessageCorpconversationAsyncsendV2Request.Form> form = new ArrayList<>(16);

    /**
     * 禁止new空的构造函数
     */
    private SendWorkNoticeQo() {
    }

    public SendWorkNoticeQo(Long agentId) throws QuestionException {
        if (agentId == null) {
            throw new QuestionException(ErrorCode.ERROR_11001.getCode(), "agentId不能为空");
        }
        this.agentId = agentId;
    }

    public SendWorkNoticeQo(String appKey) throws QuestionException {
        AppInfo appInfo = TokenService.getAppInfo(appKey);
        if (appInfo == null) {
            throw new QuestionException(ErrorCode.ERROR_404.getCode(), "应用信息为空，请先维护");
        }
        this.agentId = appInfo.getAgentId();
    }

    /**
     * 构建消息对象
     *
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo build() {
        this.msgModel = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        return this;
    }

    /**
     * 构建文本消息
     *
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildTxtMsg(String content) {
        msgModel.setMsgtype("text");
        msgModel.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        msgModel.getText()
                .setContent(content);
        this.msg = getString(msgModel);
        return this;
    }

    /**
     * 图片消息
     *
     * @param mediaId 素材id
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildImgMsg(String mediaId) {
        msgModel.setMsgtype("image");
        msgModel.setImage(new OapiMessageCorpconversationAsyncsendV2Request.Image());
        //素材id需要通过 文件存储接口上传到钉钉
        msgModel.getImage().setMediaId(mediaId);
        this.msg = getString(msgModel);
        return this;
    }

    /**
     * 文件消息
     *
     * @param mediaId 素材id
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildFileMsg(String mediaId) {
        msgModel.setMsgtype("file");
        msgModel.setImage(new OapiMessageCorpconversationAsyncsendV2Request.Image());
        //素材id需要通过 文件存储接口上传到钉钉
        msgModel.getImage().setMediaId(mediaId);
        this.msg = getString(msgModel);
        return this;
    }

    /**
     * 链接消息
     *
     * @param title  标题
     * @param text   消息描述
     * @param msgUrl 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接
     * @param picUrl 图片地址。可以通过媒体文件接口上传图片获取。（素材id，与图片消息中的mediaId相同）
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildLinkMsg(String title, String text, String msgUrl, String picUrl) {
        msgModel.setMsgtype("link");
        msgModel.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        msgModel.getLink().setTitle(title);
        msgModel.getLink().setText(text);
        msgModel.getLink().setMessageUrl(msgUrl);
        msgModel.getLink().setPicUrl(picUrl);
        this.msg = getString(msgModel);
        return this;
    }

    /**
     * 构建OA消息_头部
     *
     * @param head      消息头部
     * @param headColor 头部字体颜色（颜色编码）
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildOAMsgHead(String head, String headColor) {
        msgModel.setMsgtype("oa");
        msgModel.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
        msgModel.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
        msgModel.getOa().getHead().setText(head);
        msgModel.getOa().getHead().setBgcolor(headColor);
        return this;
    }

    /**
     * 构建OA消息_body
     *
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildOAMsgBody() {
        msgModel.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());
        msgModel.getOa().getBody().setForm(form);
        msgModel.getOa().getBody().setRich(rich);
        return this;
    }

    /**
     * 构建oa消息_form
     *
     * @param key   key
     * @param value value
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildOaMsgBodyForm(String key, String value) {
        OapiMessageCorpconversationAsyncsendV2Request.Form form = new OapiMessageCorpconversationAsyncsendV2Request.Form();
        form.setKey(key);
        form.setValue(value);
        this.form.add(form);
        return this;
    }

    /**
     * 构建Oa消息 富文本内容
     *
     * @param num  num
     * @param unit unit
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildOaMsgBodyRich(String num, String unit) {
        OapiMessageCorpconversationAsyncsendV2Request.Rich rich = new OapiMessageCorpconversationAsyncsendV2Request.Rich();
        rich.setNum(num);
        rich.setUnit(unit);
        this.rich = rich;
        return this;
    }

    /**
     * 构建OA消息
     * 必须按顺序依次构建
     * <p>
     * build
     * buildOAMsgHead
     * buildOaMsgBodyForm
     * buildOaMsgBodyRich
     * buildOAMsgBody
     * buildOaMsg
     * </p>
     *
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildOaMsg() {
        this.msg = getString(msgModel);
        return this;
    }


    /**
     * 构建卡片消息_展示
     *
     * @param title    透出到会话列表和通知的文案，最长64个字符
     * @param markDown 消息内容，支持markdown，语法参考标准markdown语法。建议1000个字符以内
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildCardMsgHead(String title, String markDown) {
        msgModel.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
        msgModel.getActionCard().setTitle(title);
        msgModel.getActionCard().setMarkdown(markDown);
        msgModel.setMsgtype("action_card");
        return this;
    }

    /**
     * 卡片消息——单个跳转按钮
     *
     * @param singleTitle 使用整体跳转ActionCard样式时的标题，必须与single_url同时设置，最长20个字符
     * @param singleUrl   消息点击链接地址，当发送消息为小程序时支持小程序跳转链接，最长500个字符
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildCardMsgSingle(String singleTitle, String singleUrl) {
        msgModel.getActionCard().setSingleTitle(singleTitle);
        msgModel.getActionCard().setSingleUrl(singleUrl);
        this.msg = getString(msgModel);
        return this;
    }

    /**
     * 卡片消息 多个按钮跳转构建
     *
     * @return
     */
    public SendWorkNoticeQo buildCardMsgBtnList() {
        msgModel.getActionCard().setBtnJsonList(btnJsonList);
        this.msg = getString(msgModel);
        return this;
    }

    /**
     * 卡片消息——多个跳转按钮 btn构建
     *
     * @param title     标题
     * @param actionUrl 跳转url
     * @return SendWorkNoticeQo
     */
    public SendWorkNoticeQo buildCardMsgBodyBtn(String title, String actionUrl) {
        OapiMessageCorpconversationAsyncsendV2Request.BtnJsonList btn = new OapiMessageCorpconversationAsyncsendV2Request.BtnJsonList();
        btn.setActionUrl(actionUrl);
        btn.setTitle(title);
        this.btnJsonList.add(btn);
        return this;
    }


    /**
     * sdk中的json转换工具
     *
     * @param msg 消息
     * @return String
     */
    public static String getString(OapiMessageCorpconversationAsyncsendV2Request.Msg msg) {
        return (new JSONWriter(false, false, true)).write(msg);
    }

    public OapiMessageCorpconversationAsyncsendV2Request.Msg getMsgModel() {
        return msgModel;
    }

    public SendWorkNoticeQo setMsgModel(OapiMessageCorpconversationAsyncsendV2Request.Msg msgModel) {
        this.msgModel = msgModel;
        return this;
    }

    public Long getAgentId() {
        return agentId;
    }

    public SendWorkNoticeQo setAgentId(Long agentId) {
        this.agentId = agentId;
        return this;
    }

    public String getUserIdList() {
        return userIdList;
    }

    public SendWorkNoticeQo setUserIdList(String userIdList) {
        this.userIdList = userIdList;
        return this;
    }

    public String getDeptIdList() {
        return deptIdList;
    }

    public SendWorkNoticeQo setDeptIdList(String deptIdList) {
        this.deptIdList = deptIdList;
        return this;
    }

    public Boolean getToAllUser() {
        return toAllUser;
    }

    public SendWorkNoticeQo setToAllUser(Boolean toAllUser) {
        this.toAllUser = toAllUser;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public SendWorkNoticeQo setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
