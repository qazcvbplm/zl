const retCode = require('./../utils/retcode.js')
const com = require('../utils/common')
const model = require('../model/wxmp')
const sha1 = require('sha1')
const config = require('./../config/config')
const getRawBody = require('raw-body')
const xml2js = require('xml2js');
const wxtoken = require('./wxtoken');
const app = {
    async checkDev(ctx) {
        let form = ctx.request.query
        let token = config.WXMP_TOKEN
        let signature = form.signature
        let nonce = form.nonce
        let timestamp = form.timestamp
        let echostr = form.echostr
        let str = [token, timestamp, nonce].sort().join('')
        let sha = sha1(str)

        if (sha == signature) {
            return echostr + ''
        } else {
            return 'wong'
        }
    },



    async getText(ctx) {
        const xml = await getRawBody(ctx.req, {
            length: ctx.request.length,
            limit: '1mb',
            encoding: ctx.request.charset || 'utf-8'
        });
        let result = '';
        let data = await this.parseXML(xml)
        if (data.Content.length == 18) {
            result = await this.bindNumber(data)
        } else if (data.Content == '接受新订单提醒' || data.Content == '取消新订单提醒') {
            result = await this.setMsgNotice(data)
        } else {
            result = await this.formatText(data.FromUserName, data.ToUserName, data.CreateTime, '可以输入以下关键字:[身份证号码],接受新订单提醒,取消新订单提醒')
        }
        return result
    },
    //绑定公众号
    async bindNumber(data) {
        let result = '';
        let udata = await model.getWxuserByPhone({
            card_num: data.Content
        })
        if (udata.length && udata.length == 1) {
            let idata = await model.add({
                openid: data.FromUserName,
                wx_id: udata[0].wx_id
            })
            if (idata.errno) {
                result = await this.formatText(data.FromUserName, data.ToUserName, data.CreateTime, '公众号绑定失败')
            } else {
                result = await this.formatText(data.FromUserName, data.ToUserName, data.CreateTime, '公众号绑定成功')
            }
        } else {
            result = await this.formatText(data.FromUserName, data.ToUserName, data.CreateTime, '您还不是接单员，请前往小程序申请')
        }
        return result
    },

    //设置提醒
    async setMsgNotice(data) {
        let result = '';
        let state = 0
        if (data.Content == '接受新订单提醒') {
            state = 1
        } else if (data.Content == '取消新订单提醒') {
            state = 0
        }
        let res = await model.setOrderNotice({
            openid: data.FromUserName,
            state: state
        })
        if (res.affectedRows == 1) {
            result = await this.formatText(data.FromUserName, data.ToUserName, data.CreateTime, '设置成功')
        } else {
            result = await this.formatText(data.FromUserName, data.ToUserName, data.CreateTime, '您还没绑定公众号或不是接单员')
        }
        return result
    },

    async formatText(FromUserName, ToUserName, CreateTime, str) {
        return '<xml>' +
            '<ToUserName><![CDATA[' + FromUserName + ']]></ToUserName>' +
            '<FromUserName><![CDATA[' + ToUserName + ']]></FromUserName>  ' +
            '<CreateTime>' + CreateTime + '</CreateTime>' +
            '<MsgType><![CDATA[text]]></MsgType>' +
            '<Content><![CDATA[' + str + ']]></Content>' +
            '</xml>'
    },
    async parseXML(xml) {
        return new Promise((resolve, reject) => {
            xml2js.parseString(xml, {
                trim: true,
                explicitArray: false,
                ignoreAttrs: true
            }, function (err, result) {
                if (err) {
                    return reject(err)
                }
                resolve(result.xml)
            })
        })
    }

}

module.exports = app